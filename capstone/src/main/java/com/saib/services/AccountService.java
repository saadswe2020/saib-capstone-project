package com.saib.services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saib.models.Account;

@Service
public class AccountService {
	
	public List<Account> getAllAccount()
	{
		List<Account> list=new ArrayList<>();
		
		Account account1=new Account(1000100,"Zartab", "Male", "zartab@codewithz.com","7715012345", "Some Address", "Savings", 10000.00, 100000.00, LocalDateTime.now(), LocalDateTime.now(), "Active");
		Account account2=new Account(1000100,"Sohail", "Male", "sohail@codewithz.com","7715012345", "Some Address", "Savings", 10000.00, 100000.00, LocalDateTime.now(), LocalDateTime.now(), "Active");
		
		list.add(account1);
		list.add(account2);
		
		return list;
		
	}

}