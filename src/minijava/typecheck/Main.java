package minijava.typecheck;

import java.io.*;
import minijava.ParseException;
import minijava.TokenMgrError;
import minijava.MiniJavaParser;
import minijava.syntaxtree.Node;
import minijava.symbol.*;
import minijava.visitor.*;

/*
	Thanks to "http://compilers.cs.ucla.edu/jtb/jtb-2003/docs.html"
	It really helps!
*/
public class Main{
	public static void main(String args[]){
		try{
			//InputStream in = new FileInputStream("/Users/liyanhao/Desktop/course/compilerprac/1400012849minijava/test/typecheck.java");
			//Node root = new MiniJavaParser(in).Goal();
			Node root = new MiniJavaParser(System.in).Goal();
			MType allClassList = new MClassList();
			//build
			root.accept(new BuildSymbolTableVisitor(), allClassList);
			//typecheck
			root.accept(new TypeCheckVisitor(), allClassList);
			if (ErrorPrinter.getsize() == 0){
				System.out.println("Program type checked successfully");
			}
			else{
				System.out.println("Type error");
			}
			ErrorPrinter.printAll();
			
		}catch (ParseException e){
			//Parse error occurs.
			e.printStackTrace();
		}catch (TokenMgrError e){
			//Lexical error occurs.
			e.printStackTrace();
		}catch (Exception e){
			//just in case.
			e.printStackTrace();
		}
	}
}