package minijava.symbol;

import java.util.*;

import minijava.symbol.*;
import minijava.typecheck.ErrorPrinter;

//Class Table that contains all symbol in a class
public class MClass extends MIdentifier implements VarContainer{
	protected boolean declared = false; // whether it is declared -- for inside-symboltable check

	// For inheritance check
	protected String baseClassName;
	protected MClass baseClass;

	// All Variables and Methods are sorted within a hashtable
	protected HashMap<String, MMethod> methodList = new HashMap<String, MMethod>();
	protected HashMap<String, MVar> varList = new HashMap<String, MVar>();

	public MClass(String _name, int _row, int _col){
		super(_name, "class", _row, _col); // type: "class"
	}
		
	@Override // implements interface
	public String insertVar(MVar newVar){
		if (this.varList.containsKey(newVar.getName())){
			// in table; report error
			return "Redundant Variable Declaration: \'" + newVar.getName() + "\'";
		}
		else{
			this.varList.put(newVar.getName(), newVar);
			return null;
		}
	}

	public String insertMethod(MMethod newMethod){
		if (this.methodList.containsKey(newMethod.getName())){
			// in table; report error
			return "Redundant Variable Declaration: \'" + newMethod.getName() + "\'";
		}
		else{
			this.methodList.put(newMethod.getName(), newMethod);
			return null;
		}
	}
	void updateVarAndMethodList(){
		if (this.baseClass == null){
			return;
		}
		this.baseClass.updateVarAndMethodList(); // recursive update
		//update the varlist from baseclass
		for (String varName : this.baseClass.varList.keySet()){
			if (this.varList.containsKey(varName))
				continue;
			this.varList.put(varName, this.baseClass.varList.get(varName));
		}
		//update the methodlist from baseclass
		for (String methodName : this.baseClass.methodList.keySet()){
			if (this.methodList.containsKey(methodName))
				continue;
			this.methodList.put(methodName, this.baseClass.methodList.get(methodName));
		}
	}
	//QUERY
	public int getVarSize() {
		return this.varList.size() * 4;
	}
	public int getMethodSize() {
		return this.methodList.size() * 4;
	}
	public MMethod getMethod(String methodName) {
		MMethod res =  this.methodList.get(methodName);
		if (res == null && this.baseClass != null)
			res = this.baseClass.getMethod(methodName);
		return res;
	}
	public boolean isDeclared(){return this.declared;}
	public String getBaseClassName(){return this.baseClassName;}
	public Collection<MMethod> getMethodSet(){return this.methodList.values();}
	public Collection<MVar> getVarSet(){return this.varList.values();}
	public MClass getBaseClass(){return this.baseClass;}
	@Override
	public MVar getVar(String name){
		MVar res = this.varList.get(name);
		if (res == null && this.baseClass != null)
			res = this.baseClass.getVar(name);
		return res;
	}
	// CHANGE
	public void setDeclared(boolean _declared){this.declared = _declared;}
	public void setBaseClassName(String _baseClassName) {this.baseClassName = _baseClassName;}
	public void setBaseClass(MClass _baseClass) {this.baseClass = _baseClass;}
	
	// MINIJAVA 2 PIGLET
	public int alloc(int currentReg, HashSet<String> pigletNameSet) {
		int currentOffset = 4; // save 4 for method table pointer
		//every var + 4
		for (MVar mvar : varList.values()){
			mvar.setOffset(currentOffset);
			currentOffset += 4;
		}
		currentOffset = 0;
		for (MMethod mmethod : methodList.values()){
			mmethod.setOffset(currentOffset);
			for (int i = 0;;++i){
				String name = this.getName() + "_" + mmethod.getName();
				if (i >= 2) name = name + "_" + i; // repeat name
				if (!pigletNameSet.contains(name)){
					pigletNameSet.add(name);
					mmethod.setPigletName(name);
					break;
				}
			}
			currentOffset += 4;
			currentReg = mmethod.alloc(currentReg);
		}
		return currentReg;
	}
}

