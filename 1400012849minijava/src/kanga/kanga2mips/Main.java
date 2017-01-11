package kanga.kanga2mips;

import java.util.*;

public class Main{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
    		String code = "";
    		while (sc.hasNext())
    			code += sc.nextLine() + "\n";
    		code = Kanga2Mips.translate(code);
    		System.out.println(code);
    		sc.close();
    }
}