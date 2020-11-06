package com.anhtt.eTutor.service.serviceImpl;

import com.anhtt.eTutor.convert.SlotMapper;
import com.anhtt.eTutor.convert.UserTransactionMapper;
import com.anhtt.eTutor.dto.HistoryUserTransactionDTO;
import com.anhtt.eTutor.dto.ReceiveMoneyObject;
import com.anhtt.eTutor.dto.SlotInsertDTO;
import com.anhtt.eTutor.dto.UserTransactionDTO;
import com.anhtt.eTutor.model.*;
import com.anhtt.eTutor.repository.*;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.UserTransactionService;
import com.anhtt.eTutor.utils.Constants;
import com.anhtt.eTutor.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserTransactionMapper userTransactionMapper;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProvider jwtProvider;

    private String getEmailByToken() {
        String[] header = request.getHeader("Authorization").split(" ");
        String token = header[header.length - 1];
        String email = jwtProvider.getEmailFromJwtToken(token);
        return email;
    }

    @Override
    public List<UserTransactionDTO> getAll() {
        return null;
    }

    @Override
    public List<HistoryUserTransactionDTO> getTransactionInHistory() {
        String email = getEmailByToken();
        Account account = accountRepository.findAccountByEmail(email);
        List<UserTransaction> userTransactionList = userTransactionRepository.findAllByAccount(account);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        userTransactionList = userTransactionList.stream().sorted(Comparator.comparing(UserTransaction::getCreateAt).reversed()).collect(Collectors.toList());
        List<HistoryUserTransactionDTO> result = userTransactionMapper.toHistoryUserTransactionDtoList(userTransactionList);
        result = result.stream().map(historyUserTransactionDTO -> {
            String s = formatter.format(historyUserTransactionDTO.getCreateAt());
            historyUserTransactionDTO.setCreateTimeString(s);
            return historyUserTransactionDTO;
        }).collect(Collectors.toList());
//        result = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return result;
    }

    /**
     * SEND MONEY from sign-in account to another account
     *
     * @param receiveMoneyObject: contains receive account and amount
     */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String sendMoney(ReceiveMoneyObject receiveMoneyObject) {
        String result = Constants.TRANSACTION_START;
        long amount = receiveMoneyObject.getAmount();
        Account receivedAccount = accountRepository.findAccountByEmail(receiveMoneyObject.getEmail());
        if (receivedAccount == null) {
            result = Constants.TRANSACTION_FAIL;
            return result;
        }
        Account currentAccount = accountRepository.findAccountByEmail(getEmailByToken());
        long availableBalance = currentAccount.getBalance() - Constants.MIN_BALANCE;
        if (availableBalance <= 0) {
            result = Constants.TRANSACTION_NOT_AVAILABLE_BALANCE;
        } else {
            long refundMoney = availableBalance - amount;
            if (refundMoney < 0) {
                result = Constants.TRANSACTION_NOT_ENOUGH_BALANCE;
            } else {
                //SEND transaction
                TransactionType sendType = transactionTypeRepository.findByName(Constants.SEND);
                UserTransaction sendTransaction = new UserTransaction();

                sendTransaction.setAccount(currentAccount);
                sendTransaction.setTransactionType(sendType);
                sendTransaction.setAmount(amount);
                sendTransaction.setCreateAt(new Timestamp(System.currentTimeMillis()));

                sendTransaction = userTransactionRepository.save(sendTransaction);

                //RECEIVE transaction
                TransactionType receiveType = transactionTypeRepository.findByName(Constants.RECEIVE);
                UserTransaction receiveTransaction = new UserTransaction();

                receiveTransaction.setAccount(receivedAccount);
                receiveTransaction.setTransactionType(receiveType);
                receiveTransaction.setAmount(amount);
                receiveTransaction.setCreateAt(new Timestamp(System.currentTimeMillis()));

                receiveTransaction = userTransactionRepository.save(receiveTransaction);

                currentAccount.setBalance(currentAccount.getBalance() - amount);
                receivedAccount.setBalance(receivedAccount.getBalance() + amount);
                currentAccount = accountRepository.save(currentAccount);
                receivedAccount = accountRepository.save(receivedAccount);

                if (currentAccount != null && receivedAccount != null && sendTransaction != null && receiveTransaction != null) {
                    result = Constants.TRANSACTION_SUCCESS;
                } else {
                    result = Constants.TRANSACTION_FAIL;
                }
            }
        }
        return result;
    }

    /**
     * Student pay for selected course
     * Tutor receive course price
     * @param registrationId : for get course (get tutor and course price)
     * @return message of transaction
     * */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String payment(UUID registrationId) throws Exception {
        String result = Constants.TRANSACTION_START;

        Registration registration = registrationRepository.findById(registrationId).get();
        Course course = registration.getCourse();
        Account studentAccount = accountRepository.findAccountByEmail(getEmailByToken());

        long balance = studentAccount.getBalance();
        long availableBalance = balance - Constants.MIN_BALANCE;
        long coursePrice = course.getPrice();

        if (availableBalance <= 0) {
            result = Constants.TRANSACTION_NOT_AVAILABLE_BALANCE;
            return result;
        } else {
            if (availableBalance < coursePrice) {
                result = Constants.TRANSACTION_NOT_ENOUGH_BALANCE;
                return result;
            } else {
                //PAYMENT TRANSACTION
                UserTransaction paymentTransaction = new UserTransaction();

                TransactionType transactionType = transactionTypeRepository.findByName(Constants.PAYMENT);

                Timestamp current = new Timestamp(System.currentTimeMillis());

                paymentTransaction.setAccount(studentAccount);
                paymentTransaction.setCourse(course);
                paymentTransaction.setAmount(new Long(coursePrice));
                paymentTransaction.setCreateAt(current);
                paymentTransaction.setTransactionType(transactionType);

                studentAccount.setBalance(balance - coursePrice);
                //RECEIVE TRANSACTION
                Tutor tutor = course.getTutor();
                Account tutorAccount = accountRepository.findAccountByTutor_Id(tutor.getId());

                UserTransaction receiveTransaction = new UserTransaction();
                transactionType = transactionTypeRepository.findByName(Constants.RECEIVE);

                current = new Timestamp(System.currentTimeMillis());

                receiveTransaction.setAccount(tutorAccount);
                receiveTransaction.setCourse(course);
                receiveTransaction.setAmount(new Long(coursePrice));
                receiveTransaction.setCreateAt(current);
                receiveTransaction.setTransactionType(transactionType);

                tutorAccount.setBalance(tutorAccount.getBalance() + coursePrice);
                //SAVE TO DB
                studentAccount = accountRepository.save(studentAccount);
                tutorAccount = accountRepository.save(tutorAccount);
                paymentTransaction = userTransactionRepository.save(paymentTransaction);
                receiveTransaction = userTransactionRepository.save(receiveTransaction);

                if (studentAccount != null && tutorAccount != null && paymentTransaction != null && receiveTransaction != null) {
                    result = Constants.TRANSACTION_SUCCESS;

//                    Student student = studentRepository.findByStudentAccount(studentAccount);
//                    Registration registration = registrationRepository.findRegistrationByCourseAndStudent(course, student);
//                    List<ClassHour> classHourList = classHourRepository.findClassHoursByRegistration(registration);
//                    List<Slot> slotList = classHourList.stream().map(classHourRepository -> {
//                        return classHourRepository.getSlot();
//                    }).collect(Collectors.toList());
//                    List<SlotInsertDTO> slotInsertDTOList = slotMapper.toSlotInsertDtoList(slotList);

                    emailSender.sendEmail(studentAccount.getEmail());
                    emailSender.sendEmail(tutorAccount.getEmail());

                    return result;
                } else {
                    result = Constants.TRANSACTION_FAIL;
                    return result;
                }

            }
        }
    }

    /**
     * RECEIVE MONEY to send money from eTutor account to specified account
     *
     * */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String receiveMoney(ReceiveMoneyObject receiveMoneyObject) {
        Account specifiedAccount = accountRepository.findAccountByEmail(receiveMoneyObject.getEmail());
        specifiedAccount.setBalance(specifiedAccount.getBalance() + receiveMoneyObject.getAmount());

        TransactionType transactionType = transactionTypeRepository.findByName(Constants.RECEIVE);

        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setAccount(specifiedAccount);
        userTransaction.setTransactionType(transactionType);
        userTransaction.setAmount(receiveMoneyObject.getAmount());
        userTransaction.setCreateAt(new Timestamp(System.currentTimeMillis()));

        Account eTutor = accountRepository.findAccountByEmail(Constants.ETUTOR_EMAIL);
        eTutor.setBalance(eTutor.getBalance() - receiveMoneyObject.getAmount());

        specifiedAccount = accountRepository.save(specifiedAccount);
        userTransaction = userTransactionRepository.save(userTransaction);
        eTutor = accountRepository.save(eTutor);

        if(specifiedAccount != null && userTransaction != null && eTutor != null) {
            return Constants.TRANSACTION_SUCCESS;
        } else {
            return Constants.TRANSACTION_FAIL;
        }
    }

    /**
     * TOPUP to recharge money into sign-in account
     *
     * @param amount:
     */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String topup(long amount) {
        String result = Constants.TRANSACTION_START;
        Account currentAccount = accountRepository.findAccountByEmail(getEmailByToken());

        currentAccount.setBalance(currentAccount.getBalance() + amount);

        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setAccount(currentAccount);
        userTransaction.setAmount(amount);
        userTransaction.setCreateAt(new Timestamp(System.currentTimeMillis()));
        TransactionType transactionType = transactionTypeRepository.findByName(Constants.TOPUP);
        userTransaction.setTransactionType(transactionType);

        userTransaction = userTransactionRepository.save(userTransaction);
        currentAccount = accountRepository.save(currentAccount);

        if(userTransaction != null && currentAccount != null) {
            result = Constants.TRANSACTION_SUCCESS;
        } else {
            result = Constants.TRANSACTION_FAIL;
        }

        return result;
    }

    /**
     * WITHDRAWAL to get out money from sign-in account
     */
    @Override
    @Transactional
    public String withdrawal(long amount) {
        String result = Constants.TRANSACTION_START;
//        Account currentAccount = accountRepository.findAccountByEmail(getEmailByToken());
//        long availableBalance = currentAccount.getBalance() - Constants.MIN_BALANCE;
//        if (availableBalance <= 0) {
//            result = Constants.TRANSACTION_NOT_AVAILABLE_BALANCE;
//        } else {
//            long refundMoney = availableBalance - amount;
//            if (refundMoney < 0) {
//                result = Constants.TRANSACTION_NOT_ENOUGH_BALANCE;
//            } else {
//                TransactionType transactionType = transactionTypeRepository.findByName(Constants.WWITHDRAWAL);
//                UserTransaction userTransaction = new UserTransaction();
//
//                userTransaction.setAccount(currentAccount);
//                userTransaction.setTransactionType(transactionType);
//                userTransaction.setAmount(amount);
//
//                userTransaction = userTransactionRepository.save(userTransaction);
//
//                currentAccount.setBalance(currentAccount.getBalance() - amount);
//                currentAccount = accountRepository.save(currentAccount);
//
//                if (currentAccount != null && userTransaction != null) {
//                    result = Constants.TRANSACTION_SUCCESS;
//                } else {
//                    result = Constants.TRANSACTION_FAIL;
//                }
//            }
//        }
        String email = getEmailByToken();
        Account account = accountRepository.findAccountByEmail(email);
        long fee = 0;
        if (account.getRoles().contains(RoleName.ROLE_TUTOR)) {
            List<Registration> registrationList = registrationRepository.findRegistrationsByCourse_TutorTutorAccount(account);
            for (Registration registration:registrationList) {
                if (registration.getStatus().equals(Constants.REGISTRATION_CREATED)) {
                    long coursePrice = registration.getCourse().getPrice();
                    long courseChargeFee = (long) (coursePrice*0.1);
                    long noAttendance = registration.getCourse().getMinSlot() * 5000;
                    fee = fee + courseChargeFee + noAttendance;
                }
                
            }
//            long availableBalance = account.getBalance() - fee;
//            if (availableBalance < amount) {
//                result = Constants.TRANSACTION_NOT_AVAILABLE_BALANCE;
//            } else {
//                account.setBalance(account.getBalance() - amount);
//
//                UserTransaction userTransaction = new UserTransaction();
//                TransactionType transactionType = transactionTypeRepository.findByName(Constants.WWITHDRAWAL);
//                userTransaction.setTransactionType(transactionType);
//                userTransaction.setAccount(account);
//                userTransaction.setAmount(amount);
//                userTransaction.setCreateAt(new Timestamp(System.currentTimeMillis()));
//
//                userTransaction = userTransactionRepository.save(userTransaction);
//                account = accountRepository.save(account);
//                if (userTransaction != null && account != null) {
//                    result = Constants.TRANSACTION_SUCCESS;
//                } else {
//                    result = Constants.TRANSACTION_FAIL;
//                }
//            }

        }
        if (account.getRoles().contains(RoleName.ROLE_STUDENT)) {
            List<Registration> registrationList = registrationRepository.findRegistrationsByStudent_StudentAccount(account);
            for (Registration registration:registrationList) {
                long noAttendance = registration.getCourse().getMinSlot()*5000;
                fee = fee + noAttendance;
            }

        }
        long availableBalance = account.getBalance() - fee;
        if (availableBalance < amount) {
            result = Constants.TRANSACTION_NOT_AVAILABLE_BALANCE;
        } else {
            account.setBalance(account.getBalance() - amount);

            UserTransaction userTransaction = new UserTransaction();
            TransactionType transactionType = transactionTypeRepository.findByName(Constants.WWITHDRAWAL);
            userTransaction.setTransactionType(transactionType);
            userTransaction.setAccount(account);
            userTransaction.setAmount(amount);
            userTransaction.setCreateAt(new Timestamp(System.currentTimeMillis()));

            userTransaction = userTransactionRepository.save(userTransaction);
            account = accountRepository.save(account);
            if (userTransaction != null && account != null) {
                result = Constants.TRANSACTION_SUCCESS;
            } else {
                result = Constants.TRANSACTION_FAIL;
            }
        }
        return result;
    }

}
