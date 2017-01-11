package minijava.typecheck;

import java.util.Vector;
import java.io.*;
// To save and print all error messages. As the homework requirement suggested.

public class ErrorPrinter{
	private static Vector<String> errStack = new Vector<String>();
	public static void print(String msg, int row, int col){
		String info = "Line " + row + "Column " + col + ": " + msg;
		errStack.addElement(msg);
	}
	public static void printAll(){
		int len = errStack.size();
		for (int i = 0; i < len; ++i){
			System.out.println(errStack.elementAt(i));
		}
	}
	public static int getsize(){
		return errStack.size();
	}
}