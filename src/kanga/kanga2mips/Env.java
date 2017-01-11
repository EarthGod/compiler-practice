package kanga.kanga2mips;

public class Env {
	protected StringBuilder mipsCode;
	public boolean isInStmt = true;
	
	public Env(){mipsCode = new StringBuilder();}
	public void append(String str){mipsCode.append(str+"\n");}
}
