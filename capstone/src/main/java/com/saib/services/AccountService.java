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
import com.saib.repository.AccountRepository;
import com.saib.util.Results;

import io.sentry.Sentry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> getAllAccounts()
	{
		List<Account> list=accountRepository.findAll();
		return list;
	
		
	}
	
	public Account getAccountByAccountNumber(long accountNumber)
	{
		Optional<Account> optional=accountRepository.findById(accountNumber);
		if(optional.isPresent()) { 
			return optional.get(); } 
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number: "+accountNumber+" doesn't exist");
		}
		
	}
	
	public List<Account> getAccountByGender(String gender)
	{
		List<Account> accounts=accountRepository.findByGender(gender);
		if(!(accounts.isEmpty())) {
			return accounts;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number: "+gender+" doesn't exist");
		}
		
	}
	
	public List<Account> getAllAccounts(Integer pageNo, Integer pageSize,String sortBy){
		
		Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		
		Page<Account> pagedResult=accountRepository.findAll(paging);
		int total=pagedResult.getTotalPages();
		int elementsNo=pagedResult.getNumberOfElements();
		System.out.println("Total No. of Pages are: "+total+" and the number of elements are: "+elementsNo);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}
		else {
			return new ArrayList<Account>();	
			}
		
		
		
	}
	
	public String deleteAccountByAccountNumber(long accountNumber)
	{
		String result= "";
	
		try {
			accountRepository.deleteById(accountNumber);
			return result=Results.SUCCESS;
		}
		catch(Exception e) {
			Sentry.captureException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number: "+accountNumber+" doesn't exist");
		}
		
	}
//	public String updateAccount(Account account, long accountNumber, String Name, String email, String phone)
//	{
//		String result ="";
//		account=accountRepository.getById(accountNumber);
//		account.setName(name);
//		account.setName(email);
//		account.setName(phone);
//		account.setName(address);
//		Account updatedAccount=accountRepository.save(account);
//		
//		
//		if(updatedAccount!=null) {
//			return result=Results.SUCCESS; 
//		}
//		else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number: "+accountNumber+" doesn't exist");
//		}
//		
//	}
	
	public String updateAccount(Account account, long accountNumber)
	{
		String result ="";
		account.setAccountNumber(accountNumber);
		Account updatedAccount=accountRepository.save(account);
		
		
		if(updatedAccount!=null) {
			return result=Results.SUCCESS; 
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number: "+accountNumber+" doesn't exist");
		}
		
	}
	
	public List<Account> getAccountByAccountType(String accountType)
	{
		List<Account> accounts=accountRepository.findByAccountType(accountType);
		
		if(!(accounts.isEmpty())) {
			return accounts;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Type: "+accountType+" doesn't exist");
		}
	}
	
	public List<Account> getAccountByStatus(String status)
	{
		List<Account> accounts=accountRepository.findByStatus(status);
		
		if(!(accounts.isEmpty())) {
			return accounts;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Status: "+status+" doesn't exist");
		}
	}

	
	
	
	public String addAccount(Account account)
	{
		String result="";
		Account storedAccount=accountRepository.save(account);
		if(storedAccount!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not created");
		}
		
		return result;
	}

}