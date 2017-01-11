package spiglet.spiglet2kanga;

import java.util.*;
public class Main{
	public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	String code = "";
    	while (sc.hasNext())
    		code += sc.nextLine() + "\n";
    	code = Spiglet2Kanga.translate(code);
    	System.out.println(code);
    	sc.close();
    }
}