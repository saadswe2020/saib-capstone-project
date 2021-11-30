package com.saib.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saib.models.Account;
import com.saib.models.Transaction;
import com.saib.repository.TransactionRepository;
import com.saib.util.Results;

import io.sentry.Sentry;

@Service
public class TransactionService {

	
	@Autowired
	TransactionRepository transactionRepository;
	
	public List<Transaction> getAllTransactions()
	{
		List<Transaction> list=transactionRepository.findAll();
		return list;
	}
	
	public List<Transaction> getAllTransactions(Integer pageNumber, Integer pageSize,String sortBy){
		
		Pageable paging = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
		
		Page<Transaction> pagedResult=transactionRepository.findAll(paging);
		int total=pagedResult.getTotalPages();
		int elementsNo=pagedResult.getNumberOfElements();
		System.out.println("Total No. of Pages are: "+total+" and the number of elements are: "+elementsNo);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}
		else {
			return new ArrayList<Transaction>();	
			}
		
		
		
	}
	
	public List<Transaction> getAllTransactionsByDate(LocalDateTime date)
	{
		List<Transaction> list=transactionRepository.findByDate(date);
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with the passed date do not exist.");
		}
		return list;
	}
	
	public List<Transaction> getAllTransactionsByDateAndType(LocalDateTime date, String transactionType)
	{
		List<Transaction> list=transactionRepository.findTransactionByDateAndTransactionType(date,transactionType);
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with the passed date or type do not exist.");
		}
		return list;
	}

	public Transaction getTransactionByTransactionId(long transactionId)
	{

		Optional<Transaction> optional=transactionRepository.findById(transactionId);
		if(optional.isPresent()) { 
			return optional.get(); } 
		else { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Id: "+transactionId+" doesn't exist"); 
			}
	}
	
	public String deleteTransactionByTransactionId(long transactionId)
	{
		String result ="";

		try {
			transactionRepository.deleteById(transactionId);
			return result=Results.SUCCESS;
		}
		catch(Exception e) {
			Sentry.captureException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Id: "+transactionId+" doesn't exist");
		}
		
	}
	
//	public String updateTransaction(Transaction transaction, long transactionId, String transactionType, double amount)
//	{
//		String result ="";
//     // transaction=transactionRepository.getById(transactionId);
//		transaction.setTransactionType(transactionType);
//		transaction.setAmount(amount);
//		Transaction updatedTransaction=transactionRepository.save(transaction);
//		
//		
//		if(updatedTransaction!=null) {
//			return result=Results.SUCCESS; 
//		}
//		else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Id: "+transactionId+" doesn't exist");
//		}
//		
//	}
	
	public String updateTransaction(Transaction transaction, long transactionNumber)
	{
		String result="";
		
		transaction.setTransactionId(transactionNumber);
		Transaction updatedTransaction=transactionRepository.save(transaction);
		
		if(updatedTransaction!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;
	}
	
	public List<Transaction> getTransactionByTransactionType(String transactionType)
	{

		List<Transaction> transactions=transactionRepository.findByTransactionType(transactionType);
		if(!(transactions.isEmpty())) {
			return transactions;
		}
		else { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Type: "+transactionType+" doesn't exist"); 
			}
	}
	
	public String makeTransaction(Transaction transaction)
	{
		String result="";
		Transaction storedTransaction=transactionRepository.save(transaction);
		if(storedTransaction!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction not made");
		}
		return result;
	}
}
