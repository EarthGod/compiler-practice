package minijava.symbol;

import java.util.*;
import minijava.symbol.*;

// All class list
public class MClassList extends MType{
	public ArrayList<MClass> classList = new ArrayList<MClass>(); // Store Classes
	
	public String InsertClass(MClass _class){ 
		String class_name = _class.getName();
		if (Repeated(class_name))
			return "Redundant Class Declaration: \"" + class_name + "\"";
		classList.add(_class);
		return null;
	}
	public boolean Repeated(String class_name){
		int len = classList.size();
		for (int i = 0; i < len; ++i){
			String c_name = ((MClass) (classList.get(i))).getName();
			if (c_name.equals(class_name))
				return true;
		}
		return false;
	}
	//QUERY INTERFACES
	public MClass getClass(String name){
		for (MClass mclass : classList)
			if (mclass.getName().equals(name))
				return mclass;
		return null;
	}
	public boolean containClass(String name) {
		for (MClass mclass : classList)
			if (mclass.getName().equals(name))
				return true;
		return false;
	}
	// derived or equal
	public boolean classEqualsOrDerives(String name, String target){
		if (name == null && target == null)
			return true;
		if (name == null || target == null)
			return false;
		while (true){ // recursively judge if it is derived from target
			if (name.equals(target)){
				return true;
			} // name equal
			MClass curClass = this.getClass(name);
			if (curClass != null)
				name = curClass.getBaseClassName();
			else break;
			if (name == null)
				break;
		}
		return false;
	}
	public void updateVarAndMethodTable(){
		for (MClass mclass : classList)
			mclass.updateVarAndMethodList();
	}
	public void updateBaseClass(){
		for (MClass mclass : classList){
			MClass baseClass = this.getClass(mclass.baseClassName);
			mclass.setBaseClass(baseClass);
		}
	}
	
	//MINIJAVA2PIGLET
	public int alloc(int currentTemp){
		HashSet<String> pigletNameTable = new HashSet<String>();
		for (MClass mclass : classList)
			currentTemp = mclass.alloc(currentTemp, pigletNameTable);
		return currentTemp;
	}
}

