package minijava.symbol;

//Identifier, can be a variable
public class MIdentifier extends MType{
	protected MIdentifier parent = null; // marks the parent
	protected String name = "";
	//PIGLET
	protected int temp = 0;
	protected int offset;

	public MIdentifier(){}
	public MIdentifier(String _name, int _row, int _col){
		super(null, _row, _col);
		this.name = _name;
	}
	public MIdentifier(String _name, String _type, int _row, int _col){
		super(_type, _row, _col);
		this.name = _name;
	}

	//query
	public String getName(){
		return this.name;
	}
	public MIdentifier getParent(){
		return this.parent;
	}
	//change
	public void setName(String _name){
		this.name = _name;
	}
	public void setParent(MIdentifier _parent){this.parent = _parent;}
	
	//PIGLET
	public int getTemp(){return temp;}
	public void setTemp(int _temp){this.temp = _temp;}
	public int getOffset(){return offset;}
	public void setOffset(int _offset){this.offset = _offset;}
	public boolean isTemp(){return temp > 0;}
}