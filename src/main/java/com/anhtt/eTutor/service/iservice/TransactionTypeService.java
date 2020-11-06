package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.UUID;

import com.anhtt.eTutor.dto.TransactionTypeDTO;

public interface TransactionTypeService {
	List<TransactionTypeDTO> getAll();
	TransactionTypeDTO saveTrans(TransactionTypeDTO trans);
	TransactionTypeDTO updateTrans(TransactionTypeDTO trans);
	void deleteTrans(UUID id);

}
