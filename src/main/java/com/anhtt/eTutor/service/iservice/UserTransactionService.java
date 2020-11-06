package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.HistoryUserTransactionDTO;
import com.anhtt.eTutor.dto.ReceiveMoneyObject;
import com.anhtt.eTutor.dto.UserTransactionDTO;

import java.util.List;
import java.util.UUID;

public interface UserTransactionService {
    List<UserTransactionDTO> getAll();
    List<HistoryUserTransactionDTO> getTransactionInHistory();
    String sendMoney(ReceiveMoneyObject receiveMoneyObject);
    String payment(UUID registrationId) throws Exception;
    String receiveMoney(ReceiveMoneyObject receiveMoneyObject);
    String topup(long amount);
    String withdrawal(long amount);
}
