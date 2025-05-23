class BankAccount{
	private int accountNumber;
	private String accountHolder;
	private double balance;
	
	BankAccount(int accountNumber, String accountHolder,double balance){
		this.accountNumber=accountNumber;
		this.accountHolder=accountHolder;
		this.balance=balance;
	}
	
	int getAccountNumber(){
		return accountNumber;
	}
	String getAccountHolder(){
		return accountHolder;
	}
	double getBalance(){
		return balance;
	}
	
	 void withdraw(double withdrawmoney)throws IllegalArgumentException{
		if (this.balance > withdrawmoney){
			System.out.println(" transcation is successful!");
			System.out.println("Your current blance is "+(this.balance-withdrawmoney));
		}
		else{
			throw new IllegalArgumentException ("Insufficient balance");
		}
	}
}

class Bank{
	 private BankAccount[] bankarray=new BankAccount[5];
	 private int bankacc=0;

	void addnewbankacc(BankAccount ban){
		if(bankacc < bankarray.length){
			bankarray[bankacc]=ban;
			bankacc++;
		}
		else{
			System.out.println("System is full; can't add more accounts");
		}
	}
	 void withdrawmoney(int accountNumber, double amount) {
        for (int i = 0; i < bankacc; i++) {
            if (bankarray[i].getAccountNumber() == accountNumber) {
                try {
                    bankarray[i].withdraw(amount);
                   System.out.println("Withdrawal successful from Account " + accountNumber + ". New balance: " +(bankarray[i].getBalance()-amount));
                    return;
                } catch (IllegalArgumentException e) {
                    System.out.println("Withdrawal failed for Account " + accountNumber + ": " + e.getMessage());
                    return;
                }
            }
        }
	}
	
	void display(){
		 for(int i=0;i<bankacc;i++){
			 System.out.println("Account: "+bankarray[i].getAccountNumber()+" Holder: "+bankarray[i].getAccountHolder()+" Balance: "+bankarray[i].getBalance());
		 }
		 if(bankacc==0){
			 System.out.println("System is empty!");
		 }
	}
}

class BankSystem{
	public static void main(String[]args){
	Bank b1=new Bank();
	
	b1.addnewbankacc(new BankAccount(1001,"Alice",5000.0));
	b1.addnewbankacc(new BankAccount(1002,"Bob",3000.0));
	
	b1.withdrawmoney(1001,6000.0);
	b1.withdrawmoney(1002,1000.0);
	
	b1.display();
	}
}