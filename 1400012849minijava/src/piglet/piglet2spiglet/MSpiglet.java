package piglet.piglet2spiglet;

import java.util.ArrayList;

public class MSpiglet{
	protected StringBuilder genCode;
	protected String temp, simpleExp, op;
	protected int inden = -1;
	protected ArrayList<String> tempList = new ArrayList<String>();
	
	public MSpiglet() {}
	public MSpiglet(String str, int nCurrentTab){
		this.inden = nCurrentTab;
		String tab = "	";
		for (int i = 0; i < nCurrentTab; ++i)
			str = tab + str;
		this.genCode = new StringBuilder(str);
	}
	public void append(String str){
		if (this.genCode == null) 
			this.genCode = new StringBuilder(str);
		else
			this.genCode.append(str);
	}
	public void append(MSpiglet sp){
		if (sp == null || sp.genCode == null)
			return;
		if (this.genCode == null) 
			this.genCode = new StringBuilder("");	
		if (sp.inden == -1)
			this.genCode.append(" ");		
		else
			this.genCode.append("\n");
		this.genCode.append(sp.genCode);
	}
	
	public void addTemp(String _temp){tempList.add(_temp);}
	public ArrayList<String> getTempList(){return tempList;}
	public boolean isSimpleExp() {return (simpleExp != null);}

	@Override
	public String toString() {return this.genCode.toString();}
	
	//query api
	public String getTemp() {return this.temp;}
	public void setTemp(String _temp){this.temp = _temp;}
	public String getSimpleExp(){return this.simpleExp;}
	public void setSimpleExp(String _simpleExp){this.simpleExp = _simpleExp;}
	public String getOp(){return this.op;}
	public void setOp(String _op){this.op = _op;}	
	

}
