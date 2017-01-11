package minijava.minijava2piglet;
import java.io.*;
import minijava.symbol.*;
import minijava.visitor.*;
import minijava.syntaxtree.*;
import minijava.*;

public class Minijava2Piglet{
	public static String translate(String raw){
		InputStream in = new ByteArrayInputStream(raw.getBytes());
		try{
			Node root = new MiniJavaParser(in).Goal();
			MClassList allClassList = new MClassList();
			//build symbol table
			root.accept(new BuildSymbolTableVisitor(), allClassList);
			Minijava2PigletVisitor newvis = new Minijava2PigletVisitor();
			allClassList.updateVarAndMethodTable();
			newvis.setAllClass(allClassList);
			newvis.setCurrentTemp(allClassList.alloc(20));
			
			MPiglet smackdown = root.accept(newvis, new MIdentifier()); //WWE meme... nevermind
			return smackdown.toString();
		}catch (ParseException e){
			e.printStackTrace();
		}
		return "fuck you";
	}
}