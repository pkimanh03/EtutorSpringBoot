package com.anhtt.eTutor.service.serviceImpl;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.TransactionTypeMapper;
import com.anhtt.eTutor.dto.TransactionTypeDTO;
import com.anhtt.eTutor.model.TransactionType;
import com.anhtt.eTutor.repository.TransactionTypeRepository;
import com.anhtt.eTutor.service.iservice.TransactionTypeService;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService{
	@Autowired
	TransactionTypeRepository tranRepo;
	
	@Autowired
	TransactionTypeMapper tranMapper;

	@Override
	public List<TransactionTypeDTO> getAll() {
		List<TransactionType> result = tranRepo.findByisDeletedFalse();
		return tranMapper.toDtos(result);
	}

	@Override
	public TransactionTypeDTO saveTrans(TransactionTypeDTO trans) {
		TransactionType result = tranMapper.toTransactionType(trans);
		tranRepo.save(result);
		return tranMapper.toDto(result);
	}

	@Override
	public TransactionTypeDTO updateTrans(TransactionTypeDTO trans) {
		Optional<TransactionType> result = tranRepo.findById(trans.getId());
		if(result !=null) {
			
			TransactionType type = tranMapper.toTransactionType(trans);
			tranRepo.save(type);
			return trans;
		}
		return null;
	}

	@Override
	public void deleteTrans(UUID id) {
		tranRepo.deleteById(id);
		
	}
	
	
	
}
