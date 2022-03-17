package assignment;

public class Collection implements CollectionInterface {

	public static final String EXCEEDED_MAX_LIMIT_EXCEPTION = "The collection has exceeded its maximum limit of 20 elements.";
	public static final String SPACE = " ";

	public static final int MAX_AMOUNT_OF_ELEMENTS = 20;

	private Identifier[] identifiers;
	private int amountOfElements;

	public Collection() {
		identifiers = new Identifier[MAX_AMOUNT_OF_ELEMENTS];
		amountOfElements = 0;
	}

	public Collection(Collection collection) {
		this.identifiers = new Identifier[MAX_AMOUNT_OF_ELEMENTS];

		for (int i = 0; i < collection.amountOfElements; i++) {
			this.identifiers[i] = new Identifier(collection.identifiers[i]);
		}

		this.amountOfElements = collection.amountOfElements;
	}

	@Override
	public Collection init() {
		amountOfElements = 0;
		return this;
	}

	@Override
	public void add(Identifier identifier) {
		if (this.contains(identifier)) {
			return;
		}

		identifiers[amountOfElements] = new Identifier(identifier);
		amountOfElements += 1;
	}

	@Override
	public Identifier get() {
		amountOfElements -= 1;
		return identifiers[amountOfElements];
	}

	@Override
	public boolean isEmpty() {
		return amountOfElements == 0;
	}

	@Override
	public int size() {
		return amountOfElements;
	}

	@Override
	public boolean contains(Identifier identifier) {
		for (int i = 0; i < amountOfElements; i++) {
			if (identifier.equals(identifiers[i])) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		if (amountOfElements == 0) {
			return "";
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < amountOfElements; i++) {
			if (i == amountOfElements - 1) {
				result.append(identifiers[i].getName());
				continue;
			}
			result.append(identifiers[i].getName()).append(SPACE);
		}

		return result.toString();
	}

	public Collection calculateDifference(Collection collection) {
		Collection result = new Collection();

		for (int i = 0; i < this.amountOfElements; i++) {
			if (!collection.contains(this.identifiers[i])) {
				result.add(this.identifiers[i]);
			}
		}

		return result;
	}

	public Collection calculateIntersection(Collection collection) {
		Collection result = new Collection();

		for (int i = 0; i < this.amountOfElements; i++) {
			if (collection.contains(this.identifiers[i])) {
				result.add(this.identifiers[i]);
			}
		}

		return result;
	}

	public Collection calculateUnion(Collection collection) throws Exception {
		Collection result = new Collection(this);

		for (int i = 0; i < collection.amountOfElements; i++) {
			if (!this.contains(collection.identifiers[i])) {
				result.add(collection.identifiers[i]);

				if (result.size() > 20) {
					throw new Exception(EXCEEDED_MAX_LIMIT_EXCEPTION);
				}
			}
		}

		return result;
	}

	public Collection calculateSymDiff(Collection collection) {
		Collection result = new Collection();

		for (int i = 0; i < this.amountOfElements; i++) {
			if (!collection.contains(this.identifiers[i])) {
				result.add(this.identifiers[i]);
			}
		}

		for (int i = 0; i < collection.amountOfElements; i++) {
			if (!this.contains(collection.identifiers[i])) {
				result.add(collection.identifiers[i]);
			}
		}

		return result;
	}

}