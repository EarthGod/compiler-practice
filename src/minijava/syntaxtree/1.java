class MyClass {
	boolean myBoolean;
	// REFERENCE of undefined class
	MyClass2 myClass;
	// REDEFINITION of variable
	boolean myBoolean;
	public int getMyInt () {
		// REFERENCE of undefined variable
        return myInt2;
    }
    // REDEFINITION of method
	public int getMyInt () {
        // REFERENCE of undefined method
        return this.getMyInt2 ()
	}
}
