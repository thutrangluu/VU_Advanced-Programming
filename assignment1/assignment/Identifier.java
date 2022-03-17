package assignment;

public class Identifier implements IdentifierInterface {

	public static final String DEFAULT_NAME = "a";

	private StringBuffer name;

	public Identifier() {
		name = new StringBuffer(DEFAULT_NAME);
	}

	public Identifier(Identifier identifier) {
		this.name = identifier.name;
	}

	@Override
	public Identifier init(char c) {
		name = new StringBuffer();
		name.append(c);
		return this;
	}

	@Override
	public String getName() {
		return name.toString();
	}

	@Override
	public void setName(String name) {
		this.name = new StringBuffer(name);
	}

	@Override
	public void addChar(char c) {
		name.append(c);
	}

	@Override
	public char getChar(int position) {
		return name.charAt(position);
	}

	@Override
	public int length() {
		return name.length();
	}

	@Override
	public boolean equals(Object o) {
		return o != null && o.getClass() == this.getClass() && this.name.toString().equals(((Identifier) o).name.toString());
	}

}