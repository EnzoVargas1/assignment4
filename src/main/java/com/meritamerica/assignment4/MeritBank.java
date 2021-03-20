package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MeritBank {
	private static ArrayList<AccountHolder> accHolderList = null;
	private static int nextAccNumber;
	private static CDOffering[] offerings;
	
	public static void addAccountHolder(AccountHolder accountHolder) {
		accHolderList.add(accountHolder);
	}
	public static AccountHolder[] getAccountHolders() {
		AccountHolder[] accHolderArr = accHolderList.toArray(new AccountHolder[0]);
		return accHolderArr;
	}
	public static CDOffering[] getCDOfferings() {
		return offerings;
	}
	
	public static void setCDOfferings(CDOffering[] offerings) {
		MeritBank.offerings=offerings;                          //cannot access static variable with this keyword
		
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		//CDOffering[] offering= getCDOfferings();
		CDOffering bestCDOffer = offerings[0];
		for(int i=1; i<offerings.length;i++) {
			double futureVal=futureValue(depositAmount,offerings[i].getInterestRate(),offerings[i].getTerm());
			double bestFutureVal = futureValue(depositAmount,bestCDOffer.getInterestRate(),bestCDOffer.getTerm());
			if(futureVal>bestFutureVal) {
				bestFutureVal=futureVal;
				bestCDOffer=offerings[i];
			}
		}
		return bestCDOffer;
	}
	
	
   public static int getNextAccountNumber() {
		
		return nextAccNumber++;
	}
   
   public static void setNextAccountNumber(int nextAccountNumber) {
		nextAccNumber = nextAccountNumber;
	}	

   
   public static double futureValue(double presentValue, double interestRate, int term) {
	double futureVal = presentValue * Math.pow((1+interestRate),term);
	return futureVal;
	
   }
   
   public static boolean readFromFile(String fileName)  {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			String line = rd.readLine(); 											//reads the first line
			setNextAccountNumber(Integer.parseInt(line));
			 line = rd.readLine();													//reads the 2nd line
			 int cdLength = Integer.parseInt(line);
			 CDOffering[] cd = new CDOffering[cdLength];							//makes an array for CDOffering
			 for(int i=0; i<cdLength;i++) {											//length of array was read from 2nd line
				 line=rd.readLine();
				 cd[i]=CDOffering.readFromString(line);									//stores the CDOffering with (term and rate) in each index
			 }
			 setCDOfferings(cd);		//requirement:"when reading from file, the data should overwrite the MeritBank data such that previous data no longer exists, only the data read from the file should exist"
			 line=rd.readLine();
			 int accHolderLength=Integer.parseInt(line);
			 accHolderList = new ArrayList<AccountHolder>();//the accHolderList now points to the newly created arrayList of accounts which is read from the file.//automatically the pointer has now changed from previous lists to the new arrayList.
			 for (int i = 0; i < accHolderLength; i++) {
					line = rd.readLine();
					//System.out.println("AH Read"+line);
					AccountHolder a = AccountHolder.readFromString(line);
					
					accHolderList.add(a);	
					line = rd.readLine();
					int numOfChecking = Integer.parseInt(line);
					if (numOfChecking != 0) {
						for (int j = 0; j < numOfChecking; j++) {
							
							line = rd.readLine();
							//System.out.println("CA Read"+line);
							CheckingAccount ch =CheckingAccount.readFromString(line);
							a.addCheckingAccount(ch);
						}
					}
					line = rd.readLine();
					int numOfSavings = Integer.parseInt(line);
					if (numOfSavings != 0) {
						for (int j = 0; j < numOfSavings; j++) {
							
							line = rd.readLine();
							//System.out.println("SA Read"+line);
							SavingsAccount sv = SavingsAccount.readFromString(line);
							a.addSavingsAccount(sv);
						}
					}
					line = rd.readLine();
					int numOfCD = Integer.parseInt(line);
					if (numOfCD != 0) {
						for (int j = 0; j < numOfCD; j++) {
							
							line = rd.readLine();
							CDAccount cdAcc = CDAccount.readFromString(line);
							a.addCDAccount(cdAcc);

							//a.addCDAccount(offerings[j], balance);
						}
					}
			}
			rd.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
