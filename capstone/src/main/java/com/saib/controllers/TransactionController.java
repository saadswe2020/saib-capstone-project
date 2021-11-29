package com.saib.controllers;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSuccessPayload;
import com.saib.models.Account;
import com.saib.models.Transaction;
import com.saib.services.TransactionService;
import com.saib.util.Results;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	
	
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions()
	{
		List<Transaction> list=transactionService.getAllTransactions();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/transactions/all/sorted")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions(@RequestParam int pageNumber, @RequestParam int pageSize, 
			@RequestParam String sortBy)
	{
		List<Transaction> list=transactionService.getAllTransactions(pageNumber,pageSize,sortBy);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/transactions/transactionDate/{date}")
	public ResponseEntity<ApiSuccessPayload> getAllTransactionsByDate(@PathVariable @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")LocalDateTime date)
	{
		List<Transaction> list=transactionService.getAllTransactionsByDate(date);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/transactions/{transactionType}/{date}")
	public ResponseEntity<ApiSuccessPayload> getAllTransactionsByDateAndType(@PathVariable @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")LocalDateTime date, String transactionType)
	{
		List<Transaction> list=transactionService.getAllTransactionsByDateAndType(date, transactionType);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	
	@GetMapping("/transactions/transactionId/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> getTransactionById(@PathVariable long transactionId)
	{
		Transaction transaction=transactionService.getTransactionByTransactionId(transactionId);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/transactionType/{transactionType}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionType(@PathVariable String transactionType)
	{
		List<Transaction> transactions=transactionService.getTransactionByTransactionType(transactionType);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transactions, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/transactions/transactionId/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> deleteAccountbyAccountNumber(@PathVariable long transactionId)
	{
		String transaction=transactionService.deleteTransactionByTransactionId(transactionId);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> makeTransaction(@RequestBody Transaction transaction)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(transaction);
		String result=transactionService.makeTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Transaction made successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
//	@PutMapping("/delete/{transactionId}")
//	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transactionId)
//	{
//		ResponseEntity<ApiSuccessPayload> response=null;
//		System.out.println(transaction);
//		String update=transactionService.updateTransaction(transaction, transactionId, transaction.setTransactionType(null), transactionId);
//		if(update.equalsIgnoreCase(Results.SUCCESS))
//		{
//			ApiSuccessPayload payload=ApiSuccessPayload.build(update, "Account updated successfully", HttpStatus.CREATED);
//			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
//		}
//		return response;
//		
//	}
	@PutMapping("/delete/transactionId/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transactionId )
	{
		String result=transactionService.updateTransaction(transaction, transactionId);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}

}
