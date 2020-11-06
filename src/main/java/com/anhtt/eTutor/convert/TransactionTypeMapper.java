package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.TransactionTypeDTO;
import com.anhtt.eTutor.model.TransactionType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionTypeMapper {
    TransactionTypeDTO toDto(TransactionType transactionType);
    List<TransactionTypeDTO> toDtos(List<TransactionType> transactionTypes);
    TransactionType toTransactionType(TransactionTypeDTO transactionTypeDTO);
}
