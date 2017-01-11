package minijava.symbol;
public class MVar extends MIdentifier{
	public MVar(String _name, String _type, MIdentifier _parent, int _row, int _col){
		super(_name, _type, _row, _col);
		this.setParent(_parent);
	}
	public String toString(){return this.getType() + " " + this.getName();}
}
