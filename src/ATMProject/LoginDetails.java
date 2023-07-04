package ATMProject;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.lang.*;

public class LoginDetails  {
	
	static String username;
	static int password;
	static long amount;
	static long opin,npin;
	static boolean pwcheck = false;
	static String sun;
	static Map<String,Long> ld = new HashMap<String,Long>();
	static Map<String,Long> ld1= new HashMap<String,Long>(); 
	
	
	public void LogDet() {
		
		ld.put("ABCD",(long) 1234);
		ld.put("EFGH",(long) 4321);
		ld.put("IJKL",(long) 5678);
		ld.put("MNOP",(long) 8765);
		
		ld1.put("ABCD", (long) 0);
		ld1.put("EFGH", (long) 15000);
		ld1.put("IJKL", (long) 20000);
		ld1.put("MNOP", (long) 25000);
	
	}
	
	
	public void getInput(){
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the user name:");
			username = sc.next();
			boolean x = checkUN(username);
			if(x==false) {
				System.out.println("Enter correct Username");
				getInput();
			}
				System.out.println("Enter the password:");
				password = sc.nextInt();
				checkPW(username,password);
			if(pwcheck==true) {
				sun = username;
				mainMenu();
			}
			else {
				System.out.println("Incorrect data.check again");
				getInput();
			}
		}
		catch(InputMismatchException im){
		
			System.out.println(im);
			getInput();
		}		
	}	
	
	
	boolean checkUN(String s) {
		
		if(ld.containsKey(s)) {
			return true;
		}else
			return false;
	}
	
	
	public void checkPW(String s,int i) {
		
		for(Entry<String, Long> m : ld.entrySet()){ 
			String s1 = m.getKey();
			long i1 = m.getValue();
			if(s1.equals(s)&&(i1==i)) {
				pwcheck = true;
			}
		}
	}
	
		
	public void mainMenu() {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("FOR MAIN MENU Press 1: To EXIT press 0");
				int a= sc.nextInt();
				if(a==1){
					mMenu();
				}
				else if(a==0) {
					System.exit(0);
				}
				else {
					System.out.println("Wrong Entry. Try again");
					mainMenu();
				}
			}catch(InputMismatchException im){
			
				System.out.println(im);
				mainMenu();
			}
	}
	
	
	public void mMenu() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your option "
					+ "1. Check Balance "
					+ "2. Withdraw "
					+ "3. Deposit "
					+ "4. Pin Change");
			int c=sc.nextInt();
				
			switch(c) {
			
			case 1:
				
				System.out.println("This is your balance");
				Balance(sun);
				
			break;
			case 2:
				
				Scanner sc1 = new Scanner(System.in);
				System.out.println("Enter the amount to withdraw");
				amount = sc.nextLong();
				Withdraw(sun,amount);
				
			break;
			case 3:
				
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Enter the amount to deposit");
				amount=sc.nextLong();
				Deposit(sun,amount);
				
			break;	
			case 4:
				
				Scanner sc3 = new Scanner(System.in);
				System.out.println("Enter the old pin");
				opin = sc3.nextLong();
				System.out.println("Enter the new pin");
			//	npin = sc3.nextLong();
				String op = String.format("%04d", sc3.nextLong());
				System.out.println(op);
				long npin1 = Long.parseLong(op);
				
				pinChange(sun,opin,npin1);
				
			break;
			default:
				System.out.println("Invalid data");
			}
		}catch(InputMismatchException im){
				
				System.out.println(im);
				mMenu();
			}
		
	}
	
	public void Balance(String s) {
		
		for(Entry<String, Long> m1 : ld1.entrySet()){ 
			String s1 = m1.getKey();
			if(s1.contains(s)) {
				if(s1.equals(s)) {
					long i1 = m1.getValue();
					if(i1<=0) {
						System.out.println("Sorry No Balance");
						mainMenu();
					}else {
					System.out.println(i1);
					mainMenu();
					}
				}	
			}
				
		}
	}
	
	public void Withdraw(String s,long a) {
		
		long amount = a;
		for(Entry<String, Long> m1 : ld1.entrySet()){ 
			String s1 = m1.getKey();
			if(s1.equals(s)){
					long i1 = m1.getValue();
					if(i1<a) {
						System.out.println("Sorry no Balance");
						mainMenu();
					}else {
						long i2 = i1-amount; 
						m1.setValue(i2);
						System.out.println("Your balance is "+i2);
						mainMenu();
					}
			}
		}
	}
	
	public void Deposit(String s,long a) {
		
		long amount = a;
		for(Entry<String, Long> m1 : ld1.entrySet()){ 
			String s1 = m1.getKey();
			if(s1.equals(s)){
					long i1 = m1.getValue();
					long i2 = i1+amount; 
					m1.setValue(i2);
					System.out.println("Your balance is "+i2);
					mainMenu();
				}
		}
	}
	
	public void pinChange(String s,long a,long b) {
		
			long op = a;
			long np = b;
			String un = s;
			if(op==np) {
				System.out.println("The new pin cannot be same as old pin");
				mMenu();
			}else {
				
				checkPinLength(np);
						
			}
			for(Entry<String, Long> m1 : ld.entrySet()){ 
				String s1 = m1.getKey();
				if(s1.equals(un)){
					long i3 = m1.getValue();
					if(op==i3){
						m1.setValue(np);
						System.out.println("Your pin is updated");
						mainMenu();
					}
					else {
						System.out.println("Enter the valid details");
						mMenu();
				
				}
				}
			}
			}
	public void checkPinLength(long a) {
				
		String str = Long.toString(a);  
		int size = str.length();  
		if(size!=4) {
			System.out.println("The pin should be 4 digits and not all 0s.please try again");
			mMenu();
		}
	}
	
		
	public static void main(String args[]){
		
		LoginDetails login = new LoginDetails();
		login.LogDet();
		login.getInput();
										  
	}
}


