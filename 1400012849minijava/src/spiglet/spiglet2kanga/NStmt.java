package spiglet.spiglet2kanga;

import java.util.ArrayList;
import java.util.HashSet;

public class NStmt {
	protected ArrayList<Integer> usedTempList;
	protected HashSet<Integer> outSet;
	
	public String type, entryLabel, jumpLabel;
	public int genTemp;
	public boolean isUnconditionJump = false;
	public NStmt nextStmt1, nextStmt2;
	
	public NStmt(String _type){
		this.type = _type;
		genTemp = -1;
		usedTempList = new ArrayList<Integer>();
		outSet = new HashSet<Integer>();
	}
	public void setType(String _type){this.type = _type;}
	public void addUsedTemp(int t){usedTempList.add(t);}
	public void addUsedTemp(String t){usedTempList.add(Integer.valueOf(t));}
}
