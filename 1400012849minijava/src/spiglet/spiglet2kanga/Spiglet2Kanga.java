package spiglet.spiglet2kanga;

import java.io.*;
import spiglet.*;
import spiglet.syntaxtree.*;
import spiglet.visitor.*;

public class Spiglet2Kanga {
	public static String translate(String str) {
		InputStream is = new ByteArrayInputStream(str.getBytes());
		try{
			Node root = new SpigletParser(is).Goal();
			LivenessVisitor v1 = new LivenessVisitor();
			Spiglet2KangaVisitor v2 = new Spiglet2KangaVisitor();
			Env env = new Env();
			root.accept(v1, env);
			env.alloc();
			root.accept(v2, env);
			
			return env.KangaCode.toString();
		}catch (ParseException e){
			e.printStackTrace();
		}
		return "fuck you";
	}
}
