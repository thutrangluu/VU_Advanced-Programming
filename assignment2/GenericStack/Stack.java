package GenericStack;

class Stack<E extends Copy<E>> implements StackInterface<E> {

    private static final int INITIAL_AMOUNT_OF_ELEMENTS = 10;

    private Object[] stackArray;
    private int amountOfElements;

    public Stack () { // constructor
	stackArray = new Object[INITIAL_AMOUNT_OF_ELEMENTS]; // array of type object not E therefore we have to cast it to E if we want to return
	amountOfElements = 0;
    }

    private void copyElements (Object[] dest, Object[] src, int amount) {
	for (int i = 0; i < amount; i++) {
	    dest[i] = ((E)src[i]).copy();
	}
    }

    public Stack<E> copy () { // method return a stack of type E
        Stack<E> result = new Stack<E>();
	result.stackArray = new Object[stackArray.length];
	result.amountOfElements = amountOfElements;
	copyElements(result.stackArray, stackArray, amountOfElements);
        return result;
    }

    public Stack<E> init () {
	amountOfElements = 0;
        return this;
    }


    private void increaseStackSize () {
	Object[] result = new Object[2 * stackArray.length];
	copyElements(result, stackArray, amountOfElements);
        stackArray = result;
    }


    public Stack<E> push (E element) {
	if (amountOfElements == stackArray.length) {
	    increaseStackSize();
	}

	stackArray[amountOfElements] = element.copy();
	amountOfElements += 1;
        return this;
    }

    public E pop () {
	amountOfElements -= 1;
	return ((E)stackArray[amountOfElements]).copy();
    }

    public E top () {
	return (E)stackArray[amountOfElements - 1];
    }

    public boolean isEmpty () {
	return amountOfElements == 0;
    }

    public int size () {
	return amountOfElements;
    }

    public Stack<E> concat (StackInterface<E> rhs) {
        Stack<E> result = copy();
        for (int i = 0; i < rhs.size(); i++) {
            result.push((E)stackArray[i]);
        }
        return result;
    }

}
