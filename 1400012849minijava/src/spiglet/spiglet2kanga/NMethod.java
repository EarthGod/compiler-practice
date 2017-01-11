package spiglet.spiglet2kanga;

import java.util.*;

public class NMethod {
	protected String name;
	protected ArrayList<NStmt> stmtList;
	public int nParaSize, nSpilledPara, nSpilledSize; //[nParaSize][nSpilledPara][nSpilledSize]
	
	public NMethod(String _name){
		this.name = _name;
		stmtList = new ArrayList<NStmt>();
	}
	
	public void addStmt(NStmt _stmt) {stmtList.add(_stmt);}
	
	//REG ALLOC
	
	class Pair implements Comparable<Pair>{
		public int t1, t2;
		public Pair(int _t1, int _t2){
			this.t1 = _t1;
			this.t2 = _t2;
		}
		@Override
		public int compareTo(Pair o){
			if (this.t1 < o.t1 || this.t2 < o.t2) return -1;
			if (this.t1 > o.t1 || this.t2 > o.t2) return 1;
			return 0;
		}
	}
	
	protected HashSet<Integer> tempSet;
	protected TreeMap<Pair, Boolean> haveEdge;
	protected TreeMap<Integer, Integer> regForTemp;
	
	public boolean update(NStmt st1, NStmt st2){
		boolean _ret = false;
		if (st2 == null) return _ret;
		
		for (int temp : st2.usedTempList)
			if (!st1.outSet.contains(temp)){
				_ret = true;
				st1.outSet.add(temp);
			}
		for (int temp : st2.outSet)
			if (temp != st1.genTemp && !st1.outSet.contains(temp)){
				_ret = true;
				st1.outSet.add(temp);
			}
		
		return _ret;
	}
	
	private void buildGraph(){
		HashMap<String, NStmt> labelStmt = new HashMap<String, NStmt>();
		for (NStmt stmt : stmtList) {
			String label = stmt.entryLabel;
			if (label != null) labelStmt.put(label, stmt);
		}
		
		for (int i = 0; i < stmtList.size(); ++i){
			NStmt stmt = stmtList.get(i);
			if (!stmt.isUnconditionJump && i+1 < stmtList.size())
				stmt.nextStmt1 = stmtList.get(i+1);
			
			if (stmt.jumpLabel != null)
				stmt.nextStmt2 = labelStmt.get(stmt.jumpLabel);
		}
			
		while (true){
			boolean flag = false;
			for (NStmt stmt : stmtList){
				flag |= update(stmt, stmt.nextStmt1);
				flag |= update(stmt, stmt.nextStmt2);
			}
			if (!flag) break; 
		}
		
		tempSet = new HashSet<Integer>();
		for (NStmt stmt : stmtList)
			tempSet.addAll(stmt.outSet);
	}
	
	public void allocReg(){
		buildGraph();	
		nSpilledPara = Math.max(0, nParaSize-4);
		regForTemp = new TreeMap<Integer, Integer>();
		nSpilledSize = 0;
		for (int temp1 : tempSet)
			regForTemp.put(temp1, nSpilledSize++);
	}
	
	public int getReg(int t){
		if (tempSet.contains(t) == false) return -1;
		return regForTemp.get(t);
	}
}
