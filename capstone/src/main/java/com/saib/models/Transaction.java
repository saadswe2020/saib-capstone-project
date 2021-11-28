package com.saib.models;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@Column(name="transaction_id")
	private long transactionId;
	
	@Column(name="from_account")
	private long fromAccount;
	
	@Column(name="to_account")
	private long toAccount;
	
	@Column(name="same_bank_transaction")
	private boolean sameBankTransaction;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="amount")
	private double amount;
	
//	@Temporal (TemporalType.DATE)
	@Column(name="date")
	private LocalDateTime date;
	
	@Column(name="time")
	private LocalDateTime time;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="status")
	private String status;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long transactionId, long fromAccount, long toAccount, boolean sameBankTransaction,
			String bankName, double amount, LocalDateTime date, LocalDateTime time, String transactionType,
			String status) {
		super();
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.sameBankTransaction = sameBankTransaction;
		this.bankName = bankName;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.transactionType = transactionType;
		this.status = status;
	}

	@Override
	public String toString() {
		return "transaction [transactionId=" + transactionId + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", sameBankTransaction=" + sameBankTransaction + ", bankName=" + bankName + ", amount="
				+ amount + ", date=" + date + ", time=" + time + ", transactionType=" + transactionType + ", status="
				+ status + "]";
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getToAccount() {
		return toAccount;
	}

	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}

	public boolean isSameBankTransaction() {
		return sameBankTransaction;
	}

	public void setSameBankTransaction(boolean sameBankTransaction) {
		this.sameBankTransaction = sameBankTransaction;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
