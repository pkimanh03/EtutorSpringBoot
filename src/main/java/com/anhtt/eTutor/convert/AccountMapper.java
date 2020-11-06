package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.AccountDTO;
import com.anhtt.eTutor.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDto(Account account);
    List<AccountDTO> toDtos(List<Account> accountList);
    Account toAccount(AccountDTO accountDTO);
}
