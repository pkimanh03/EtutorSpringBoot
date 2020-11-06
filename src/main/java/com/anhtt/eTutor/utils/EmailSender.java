package com.anhtt.eTutor.utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

/**
 * EmailSender
 */
@Repository
public class EmailSender {

    @Value("${spring.mail.username}")
    private String sender;
    @Value("${anhtt.app.mail.header}")
    private String header;
    @Value("${anhtt.app.mail.footer}")
    private String footer;
    @Value("${anhtt.app.mail.register}")
    private String register;
    @Value("${anhtt.app.mail.successTransactionStudent}")
    private String successTransactionStudent;
    @Value("${anhtt.app.mail.successTransactionTutor}")
    private String successTransactionTutor;
    @Value("${anhtt.app.mail.slotNoti}")
    private String slotNoti;
    @Value("${anhtt.app.mail.attendNoti}")
    private String attendNoti;

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(String email) throws AddressException, MessagingException, IOException {
        sendmail(email);
        return "Email sent successfully";
    }

    public String sendEmailSuccessTransactionStu(String stuEmail, String courseName, String[] slots, String tutorEmail)
            throws AddressException, MessagingException, IOException {
        sendSuccessTransactionMailStudent(stuEmail, courseName, slots, tutorEmail);
        return "Email sent successfully";
    }

    public String sendEmailSuccessTransactionTut(String tutorEmail, String tutorBalance, String transaction)
            throws AddressException, MessagingException, IOException {
        sendSuccessTransactionMailTutor(tutorEmail, transaction, tutorBalance);
        return "Email sent successfully";
    }

    public String sendSlotNotification(String email, String[] slots)
            throws AddressException, MessagingException, IOException {
        sendSlotNoti(email, slots);
        return "Email sent successfully";
    }

    public String sendAttendNotification(String email) throws AddressException, MessagingException, IOException {
        sendAttendNoti(email);
        return "Email sent successfully";
    }

    // Ulti

    private void sendmail(String email) throws AddressException, MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(sender);
        helper.setTo(email);
        helper.setSubject("ETutor - Đăng ký tài khoản thành công");
        msg.setContent(header + MessageFormat.format(register, email, email) + footer, "text/html");
        javaMailSender.send(msg);
    }

    /**
     * slots: mảng các tiết học vd: T4 11/10 17:30
     */
    private void sendSuccessTransactionMailStudent(String stuEmail, String courseName, String[] slots,
            String tutorEmail) throws AddressException, MessagingException, IOException {
        String slotHtml = "";
        for (String slot : slots) {
            slotHtml += "<li>" + slot + "</li>";
        }
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(sender);
        helper.setTo(stuEmail);
        helper.setSubject("ETutor - Thanh toán thành công");
        msg.setContent(header
                + MessageFormat.format(successTransactionStudent, stuEmail, courseName, tutorEmail, slotHtml) + footer,
                "text/html");
        javaMailSender.send(msg);
    }

    /**
     * tutorBalance: Account balance, transaction: Số tiền chuyển khoản
     */
    private void sendSuccessTransactionMailTutor(String tutorEmail, String tutorBalance, String transaction)
            throws AddressException, MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(sender);
        helper.setTo(tutorEmail);
        helper.setSubject("ETutor - Thanh toán thành công");
        msg.setContent(
                header + MessageFormat.format(successTransactionTutor, tutorEmail, transaction, tutorBalance) + footer,
                "text/html");
        javaMailSender.send(msg);
    }

    /**
     * slots: mảng các tiết học vd: T4 11/10 17:30
     */
    private void sendSlotNoti(String email, String[] slots) throws AddressException, MessagingException, IOException {
        String slotHtml = "";
        for (String slot : slots) {
            slotHtml += "<li>" + slot + "</li>";
        }
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(sender);
        helper.setTo(email);
        helper.setSubject("ETutor - Nhắc nhở");
        msg.setContent(header + MessageFormat.format(slotNoti, email, slotHtml) + footer, "text/html");
        javaMailSender.send(msg);
    }

    private void sendAttendNoti(String email) throws AddressException, MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(sender);
        helper.setTo(email);
        helper.setSubject("ETutor - Nhắc nhở");
        msg.setContent(header + MessageFormat.format(attendNoti, email) + footer, "text/html");
        javaMailSender.send(msg);
    }
}