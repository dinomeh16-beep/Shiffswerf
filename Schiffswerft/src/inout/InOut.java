package inout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InOut {
	
	public static void printString(String s) {
		System.out.println(s);
	}
	
	public static String readString(String prompt) throws InOutException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String output = null;
		
		System.out.print(prompt + " ");
	    try {
	         output = br.readLine();
	         return output;
	    } catch (IOException ioe) {
	         System.out.println("Failed; please try again");
 	 		 System.out.print(prompt + " ");
	         try {
				output = br.readLine();
				return output;
			} catch (IOException e) {
				throw new InOutException();
			}
      }
	}

	public static int readInt(String prompt) throws InOutException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String rs = null;
		
		System.out.print(prompt + " ");
	    try {
	         rs = br.readLine();
	         return Integer.parseInt(rs);
	    } catch (Exception ioe) {
	         System.out.println("Failed; please try again");
 	 		 System.out.print(prompt + " ");
	         try {
				rs = br.readLine();
		         return Integer.parseInt(rs);
			} catch (Exception e) {
				throw new InOutException();
			}
      }
	}

	public static int readIntBetween(String prompt, int lower, int upper) throws InOutException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String rs = null;
	    int res;
		
		System.out.print(prompt + " ");
	    try {
	         rs = br.readLine();
	         res = Integer.parseInt(rs);
	         if ( res < lower || res > upper ) throw new InOutException();
	         return res;
	    } catch (Exception ioe) {
	         System.out.println("Failed; please try again");
 	 		 System.out.print(prompt + " ");
	         try {
				rs = br.readLine();
		         res = Integer.parseInt(rs);
		         if ( res < lower || res > upper ) throw new InOutException();
		         return res;
			} catch (Exception e) {
				throw new InOutException();
			}
      }
	}

	public static double readDouble(String prompt) throws InOutException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String rs = null;
		
		System.out.print(prompt + " ");
	    try {
	         rs = br.readLine();
	         return Double.parseDouble(rs);
	    } catch (Exception ioe) {
	         System.out.println("Failed; please try again");
 	 		 System.out.print(prompt + " ");
	         try {
				rs = br.readLine();
		         return Double.parseDouble(rs);
			} catch (Exception e) {
				throw new InOutException();
			}
      }
	}
	
	public static int readMenu(String prompt, String menu) throws InOutException {
		String[] menuItems = menu.split("@"); 
		System.out.println(prompt);
		printMenu(menuItems);
		int ret = readIntBetween("Please choose:",1,menuItems.length);
		return ret;
	}

	private static void printMenu(String[] menuItems) {
		int nbr=1;
		for (String menuItem: menuItems) {
			System.out.println(nbr+" "+menuItem);
			nbr++;
		}
	}
}
