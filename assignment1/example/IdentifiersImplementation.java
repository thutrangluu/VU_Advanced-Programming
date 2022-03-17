package example;

class IdentifiersImplementation implements IdentifiersInterface {

	private StringBuffer identifier;

	public IdentifiersImplementation() {
		identifier = new StringBuffer();
	}
	
	/*private void copyIdentifiers(StringBuffer identifier, StringBuffer src) {
        this.identifier = src;
    }
	
	public IdentifiersImplementation(Identifiers src) {
		StringBuffer identifier = new StringBuffer();
		copyIdentifiers(identifier, src.identifier);
	}*/
	
	public void init (char c) {
		identifier.append(c);
	}
	
	public void add (char c) {
		identifier.append(c);
	}

	public char charAt​(int index) {
		char result = identifier.charAt(index);
		return result;
	}

	public int length() {
		int result = identifier.length();
		return result;
	}

	public boolean isIdentical(Identifiers identifier) { // compare the content of 2 identifiers
		/* PRE - 
		 * POST - true:  identifier has the same sequence of char
			      false: identifier does not have the same sequence of char
		 */
		if (this.identifier.length() == identifier.length()) {
			for (int i = 0; i <= this.identifier.length() - 1; i++) {	//for all char of 2 identifiers
				if (this.identifier.charAt(i) == identifier.charAt(i)) {	//each char is the same
					return true;
				} else return false;
			}
		} return false;
	}	

}
