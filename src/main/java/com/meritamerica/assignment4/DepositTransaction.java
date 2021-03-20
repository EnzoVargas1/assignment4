package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction{
	
	DepositTransaction(BankAccount targetAccount, double amount){
		this.setAmount(amount);
		this.setSourceAccount(targetAccount);
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}



}
