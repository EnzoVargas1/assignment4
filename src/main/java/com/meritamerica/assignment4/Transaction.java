package com.meritamerica.assignment4;

public abstract class Transaction {
	
	private double amount;
	private BankAccount account;
	private java.util.Date date;
	private String rejectionreason;
	
	
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	public BankAccount getSourceAccount() {
		return account;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.account = sourceAccount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public java.util.Date getTransactionDate(){
		
	}
	
	public void setTransactionDate(java.util.Date date) {
		
	}
	
	public String writeToString() {
		return "";
	}
	
	public static Transaction readFromString(String transactionDataString) {
		
	}
	
	public boolean isProcessedByFraudTeam() {
		
	}
	
	public void setProcessedByFraudTeam(boolean isProcessed) {
		
	}
	
	public String getRejectionReason() {
		
	}
	
	public void setRejectionReason(String reason) {
		
	}




	
	

   
	
	








}
