package minijava.symbol;

// interface for convenience
public interface VarContainer{
	public String insertVar(MVar newVar); // insert a var 
	public MVar getVar(String name); // get the var
}