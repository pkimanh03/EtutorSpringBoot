package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.HistoryUserTransactionDTO;
import com.anhtt.eTutor.dto.ReceiveMoneyObject;
import com.anhtt.eTutor.service.iservice.UserTransactionService;
import com.anhtt.eTutor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/user-transaction")
public class UserTransactionController {

    @Autowired
    private UserTransactionService userTransactionService;

    @GetMapping("/history")
    public ResponseEntity<List<HistoryUserTransactionDTO>> getHistoryTransaction() {
        List<HistoryUserTransactionDTO> result = userTransactionService.getTransactionInHistory();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/pay-registration/{registrationId}")
    public ResponseEntity<String> payCourse(@PathVariable UUID registrationId) {
        try {
            String transactionResult = userTransactionService.payment(registrationId);
            return ResponseEntity.ok(transactionResult);
        } catch (Exception e) {
            return new ResponseEntity<>(Constants.TRANSACTION_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping("/top-up")
    public ResponseEntity<String> rechargeMoney(@RequestBody long amount) {
        String result = userTransactionService.topup(amount);
        if(result.equals(Constants.TRANSACTION_SUCCESS))
            return ResponseEntity.ok(result);
        else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/send-money")
    public ResponseEntity<String> sendMoney(@RequestBody ReceiveMoneyObject receiveMoneyObject) {
        String result = userTransactionService.sendMoney(receiveMoneyObject);
        if(result.equals(Constants.TRANSACTION_SUCCESS))
            return ResponseEntity.ok(result);
        else return new ResponseEntity<>(result, HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/receive-money")
    public ResponseEntity<String> receiveMoney(@RequestBody ReceiveMoneyObject receiveMoneyObject) {
        String result = userTransactionService.receiveMoney(receiveMoneyObject);
        if(result.equals(Constants.TRANSACTION_SUCCESS))
            return ResponseEntity.ok(result);
        else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/withdraw-money")
    public ResponseEntity<String> withdrawMoney(@RequestBody long amount) {
        String result = userTransactionService.withdrawal(amount);
        if(result.equals(Constants.TRANSACTION_SUCCESS))
            return ResponseEntity.ok(result);
        else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
