package all;

import minijava.minijava2piglet.*;
import piglet.piglet2spiglet.*;
import spiglet.spiglet2kanga.*;
import kanga.kanga2mips.*;
import java.util.*;

public class Main{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String str = new String("");
		while(sc.hasNext())
			str += sc.nextLine() + "\n";
		str = Minijava2Piglet.translate(str);
		str = Piglet2Spiglet.translate(str);
		str = Spiglet2Kanga.translate(str);
		str = Kanga2Mips.translate(str);
		System.out.println(str);
	}
}