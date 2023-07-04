package ATMProject;

import java.util.HashMap;
import java.util.Map;

public abstract class ATMSecondAbstract {

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
	
	public abstract void getInput();
	abstract boolean checkUN(String s);
	public abstract void checkPW(String s,int i);
	
	
	
}
