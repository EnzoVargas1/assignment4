package com.meritamerica.assignment4;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public abstract class BankAccount {
	
	private int numAccount;
	private double balance;
	private double interestRate;
	private long accountNumber;
	private java.util.Date date;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public BankAccount(double balance, double interestRate) {
		this.balance=balance;
		this.interestRate = interestRate;
	}
	public BankAccount(double balance,double interestRate,java.util.Date accountOpenedOn) {
		this.balance=balance;
		this.interestRate = interestRate;
		this.date = accountOpenedOn;
	}
	public BankAccount(long accountNumber, double balance, double interestRate,java.util.Date accountOpenedOn) {
		this.accountNumber= accountNumber;
		this.balance=balance;
		this.interestRate = interestRate;
		this.date = accountOpenedOn;
	}
	public BankAccount(int numAccount, double balance, double rate, Date date) {
		// TODO Auto-generated constructor stub
		this.numAccount = numAccount;
		this.balance=balance;
		this.interestRate = rate;
		this.date = date;
		
	}
	
	public  java.util.Date getOpenedOn(){
		return this.date;
	}	
	public int getAccountNumber() {
		return this.numAccount;
	}
	public double getBalance() {
		return this.balance;
	}
	public double getInterestRate() {
		return this.interestRate;
	}
	public boolean withdraw(double amount) {
		
		if( (balance-amount)>=0)
		{
			balance-= amount;
			return true;
		}else {
			return false;
			
		}
	}
	public boolean deposit (double amount) {
		if(amount>0) {
			this.balance += amount;
			return true;
		} else {
			return false;
		}
		
	}
	
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	public ArrayList<Transaction> getTransactions(){
		return transactions;
	}

	
	
	public double futureValue(int years) {
		double futureVal = balance * Math.pow((1+this.interestRate),years);
		return futureVal;
	}
	/**
	public static BankAccount readFromString(String accountData) throws java.lang.NumberFormatException{
		StringTokenizer token = new StringTokenizer(accountData, ",");
		int numAccount = Integer.parseInt(token.nextToken());
		double balance = Double.parseDouble(token.nextToken());
		double rate = Double.parseDouble(token.nextToken());
		@SuppressWarnings("deprecation")
		Date date = new Date(token.nextToken());//
		BankAccount bank = new BankAccount(numAccount, balance, rate, date);
		return bank;
	}
	**/
	
	
	public String writeToString() {
		String accountString = getAccountNumber()+","+getBalance()+","+getInterestRate()+","+getOpenedOn(); 
		return accountString;
	}
	


}
