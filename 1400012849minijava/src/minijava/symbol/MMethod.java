package minijava.symbol;

import java.util.*;
import minijava.symbol.*;

//Method contains the symbol with in a method
public class MMethod extends MIdentifier implements VarContainer{
	protected String returnType;
	protected HashMap<String, MVar> varList = new HashMap<String, MVar>();
	protected ArrayList<MVar> paramList = new ArrayList<MVar>();
	
	protected String pigletName;
	
	public MMethod(String _name, String _returnType, MIdentifier _parent, int _row, int _col) {
		super(_name, "method", _row, _col); //"type: method"
		this.setParent(_parent);
		this.setReturnType(_returnType);
	}

	@Override
	public String insertVar(MVar newVar){
		if (varList.containsKey(newVar.getName()))
			return "Redundant Variable Declaration: \'" + newVar.getName() + "\'";
		else{
			varList.put(newVar.getName(), newVar);
			return null;
		}
	}
	
	public String insertParam(MVar newParam){
		this.paramList.add(newParam);
		return insertVar(newParam);
	}
	public MVar getParam(int th){
		if (paramList.size() <= th)
			return null;
		else
			return paramList.get(th);
	}

	@Override
	public MVar getVar(String name){
		if (varList.containsKey(name))
			return varList.get(name);
		return ((MClass)(this.parent)).getVar(name);
	}

	public int getParamSize(){return paramList.size();}
	public String getReturnType() {return this.returnType;}
	public void setReturnType(String _returnType) {this.returnType = _returnType;}
	
	//PIGLET
	public int alloc(int currentTemp){
		int num = 0;
		for (MVar mvar : paramList)
			mvar.setTemp(++num);
		for (MVar mvar : varList.values()){
			boolean flag = true;
			for (MVar mvar2 : paramList)
				if (mvar2.getName().equals(mvar.getName())) 
					flag = false;
			if (flag)
				mvar.setTemp(currentTemp++);
		}
		return currentTemp;
	}
	
	public String getPigletName(){return pigletName;}
	public String getPigletDefineName(){return this.pigletName + " [ " + (paramList.size()+1) + " ]";}
	public void setPigletName(String _pigletName){this.pigletName = _pigletName;}
}
