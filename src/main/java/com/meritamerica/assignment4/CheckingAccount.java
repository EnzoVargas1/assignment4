package com.meritamerica.assignment4;

import java.util.Date;
import java.util.StringTokenizer;

public class CheckingAccount extends BankAccount {

	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
		// TODO Auto-generated constructor stub
	}
	
	public CheckingAccount(double balance) {
		super(balance,CHECKING_INTERESTRATE);
	}
	
	public CheckingAccount(int numAccount, double balance, double rate, Date date) {
		// TODO Auto-generated constructor stub
		super(numAccount, rate, rate, date);
	}

	public static CheckingAccount readFromString(String accountData) throws java.lang.NumberFormatException{
		StringTokenizer token = new StringTokenizer(accountData, ",");
		int numAccount = Integer.parseInt(token.nextToken());
		double balance = Double.parseDouble(token.nextToken());
		double rate = Double.parseDouble(token.nextToken());
		Date date = new Date(token.nextToken());
		CheckingAccount checking = new CheckingAccount(numAccount, balance, rate, date);
		return checking;
	}
	
	public static final double CHECKING_INTERESTRATE= 0.0001;
	
	

}
