package com.saib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSuccessPayload;
import com.saib.models.Account;
import com.saib.services.AccountService;

@RestController
public class AccountController {
	
	/*
	 *  GET - /accounts - Get me all details 
	 *  GET - /accounts/id - Get me details for a single account 
	 *  POST - /accounts - Creating a new account 
	 *  PUT - /accounts/id - Updating an existing account 
	 *  DELETE -/accounts/id - for deleting an account from db
	 *  
	 *  
	 */
	@Autowired
	AccountService accountService;
	
	
	@GetMapping("/accounts")
	public ResponseEntity<ApiSuccessPayload> getAllAccounts()
	{
		List<Account> list=accountService.getAllAccount();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	

}