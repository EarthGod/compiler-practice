package minijava.symbol;

//Father of all symbol table's subclass
public class MType{
	// Symbol info:
	protected String type = "";
	protected int row = 0, col = 0;
	//Constructor
	public MType(){}
	public MType(String _type){ this.type = _type; }
	public MType(String _type, int _row, int _col){
		this.type = _type;
		this.row = _row;
		this.col = _col;
	}
	//Interface to query
	public int getRow(){
		return this.row;
	}
	public int getCol(){
		return this.col;
	}
	public String getType(){
		return this.type;
	}
	//Interface to change
	public void setRow(int _row){
		this.row = _row;
	}
	public void setCol(int _col){
		this.col = _col;
	}
	public void setType(String _type){
		this.type = _type;
	}
}
