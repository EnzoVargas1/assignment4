package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.StringTokenizer;



public class AccountHolder {
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	
	private ArrayList <CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();;
	private ArrayList <SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();;
	private ArrayList<CDAccount>cdAccounts = new ArrayList<CDAccount>();;
	
	public  AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		 this.firstName = firstName;
		 this.middleName = middleName;
		 this.lastName = lastName;
		 this.ssn = ssn;
		 
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSSN() {
		return ssn;
	}
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException
 {
		CheckingAccount checking=null;
		if((getSavingsBalance()+getCheckingBalance()+openingBalance)<250000) {
			checking = new CheckingAccount(openingBalance);
			this.checkingAccounts.add(checking);
		}else {
			throw new ExceedsCombinedBalanceLimitException("Combined balance exceeded");
		}
		return checking;
	}
	
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		if((getSavingsBalance()+getCheckingBalance()+checkingAccount.getBalance())<250000) {
			this.checkingAccounts.add(checkingAccount);
			return checkingAccount;
		} else {
			return null;
		}
	}
	public CheckingAccount[] getCheckingAccounts() {
		CheckingAccount[] checking = checkingAccounts.toArray(new CheckingAccount[0]);
		return checking;
	}
	
	//directly the size of arraylist is calculated.
	public int getNumberOfCheckingAccounts() {
		int numberOfCheckingAccounts = checkingAccounts.size();
		return numberOfCheckingAccounts;
	}
	
	//adds each checking account of the account holder from the array of checking accounts
	//to get the checking account array we call the already defined function getCheckingAccounts()
	public double getCheckingBalance() {
		CheckingAccount[] checkingArr = getCheckingAccounts();
		double checkingTotal = 0;
		for(int i=0;i<checkingArr.length;i++) {
			checkingTotal+=checkingArr[i].getBalance();
		}
		
		return checkingTotal;
	}
	
	public SavingsAccount addSavingsAccount(double openingBalance){
		SavingsAccount savings = null;
		if((getSavingsBalance()+getCheckingBalance()+openingBalance)<250000) {
			savings=new SavingsAccount(openingBalance);
			this.savingsAccounts.add(savings);
		}
		return savings;
	}
	
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount)  {
		if((getSavingsBalance()+getCheckingBalance()+savingsAccount.getBalance())<250000) {
			this.savingsAccounts.add(savingsAccount);
			return savingsAccount;						                          //returning savingsAccount as the return type expected is object. 
		} else {
			return null;
		}
	}
	
	public SavingsAccount[] getSavingsAccounts() {
		SavingsAccount[] savings = savingsAccounts.toArray(new SavingsAccount[0]); //converting to array since, return type, an array is expected.
		return savings;
	}
	
	//calculates the length of the savings account array, getSavingsAccounts() AND returns the array of savings account when invoked
	public int getNumberOfSavingsAccounts() {
		int numberOfSavingsAccount= (getSavingsAccounts()).length;
		return numberOfSavingsAccount;
	}
	
	public double getSavingsBalance() {
		SavingsAccount[] savingArr=getSavingsAccounts();
		double savingsTotal=0;
		for(int i=0;i<savingArr.length;i++) {
			savingsTotal+= (savingArr[i].getBalance());
		}
		
		return savingsTotal;
	}
	
 public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		
		CDAccount cd=new CDAccount(offering,openingBalance);
		this.cdAccounts.add(cd);
		return cd;
	}
 
 public CDAccount addCDAccount(CDAccount cdAccount) {
		this.cdAccounts.add(cdAccount);
		return cdAccount;
	}
	
	public CDAccount[] getCDAccounts() {
		CDAccount[] cd = cdAccounts.toArray(new CDAccount[0]);
		return cd;
	}
	
	public int getNumberOfCDAccounts() {
		int numberOfCDAccounts= cdAccounts.size();
		return numberOfCDAccounts;
	}
	
	public double getCDBalance() {
		double cdTotal=0;
		CDAccount[] cdArr= getCDAccounts();
		for(int i=0;i<cdArr.length;i++) {
			cdTotal+= cdArr[i].getBalance();
		}
		System.out.println("cd:"+cdTotal);
		return cdTotal;
	}
	
	public double getCombinedBalance() {
		return (getSavingsBalance()+getCheckingBalance()+getCDBalance());
	}
	
	public int compareTo(AccountHolder ac) {
		if(this.getCombinedBalance()>ac.getCombinedBalance()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public static AccountHolder readFromString(String accountHolderData) throws java.lang.Exception{
		StringTokenizer tokenizer = new StringTokenizer(accountHolderData, ",");

		String firstName = tokenizer.nextToken();
		String middleName = "";
		if (tokenizer.countTokens() == 4) {
			middleName = tokenizer.nextToken();
		}
		String lastName = tokenizer.nextToken();
		String ssn = tokenizer.nextToken();

		AccountHolder account = new AccountHolder(firstName, middleName, lastName, ssn);
		return account;
	}
	
	public String writeToString() {
		String acString = getFirstName()+","+getMiddleName()+","+getLastName()+","+getSSN();
		return acString;
	}

}
