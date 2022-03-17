package example;

class Identifiers implements IdentifiersInterface {

	private StringBuffer sb;

	public Identifiers() {
		sb = new StringBuffer();
	}
		
	public Identifiers(Identifiers src) {
		sb = new StringBuffer(src.sb.toString());
	}
	
	@Override
	public void init (char c) {
		sb.append(c);
	}
	
	@Override
	public void add (char c) {
		sb.append(c);
	}

	@Override
	public char getChar​(int index){
		char result = sb.charAt(index);
		return result;
	}

	@Override
	public int length() {
		int result = sb.length();
		return result;
	}

	@Override
	public boolean isIdentical(Identifiers identifier) { // compare the content of 2 identifiers
		/* PRE - 
		 * POST - true:  identifier has the same sequence of char
			      false: identifier does not have the same sequence of char
		 */
		if (this.sb.length() == identifier.length()) {
			for (int i = 0; i < this.sb.length(); i++) {	//for all char of 2 identifiers
				if (this.sb.charAt(i) == identifier.charAt(i)) {	//each char is the same
					return true;
				}
				return false;
			}
		} return false;
	}

}
