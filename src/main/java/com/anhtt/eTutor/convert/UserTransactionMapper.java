package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.HistoryUserTransactionDTO;
import com.anhtt.eTutor.dto.UserTransactionDTO;
import com.anhtt.eTutor.model.UserTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTransactionMapper {
    UserTransactionDTO toDto(UserTransaction userTransaction);
    List<UserTransactionDTO> toDtos(List<UserTransaction> userTransactions);
    UserTransaction toUserTransaction(UserTransactionDTO userTransactionDTO);

    @Mappings({
            @Mapping(source = "transactionType.name", target = "transactionType"),
            @Mapping(source = "course.name", target = "courseName")
    })
    HistoryUserTransactionDTO toHistoryUserTransactionDto(UserTransaction userTransaction);
    List<HistoryUserTransactionDTO> toHistoryUserTransactionDtoList(List<UserTransaction> userTransactionList);
}
