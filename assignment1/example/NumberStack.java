package example;

public class NumberStack {

	
	class NumberStack implements NumberStackInterface { // all upto me

	    private static final int INITIAL_AMOUNT_OF_ELEMENTS = 10;

	    private Number[] stackArray;
	    private int amountOfElements;

	    public NumberStack () {
		stackArray = new Number[INITIAL_AMOUNT_OF_ELEMENTS];
		amountOfElements = 0;
	    }

	    private void copyElements (Number[] dest, Number[] src, int amount) {
		for (int i = 0; i < amount; i++) {
		    dest[i] = new Number(src[i]);
		}
	    }

	    public NumberStack (NumberStack src) {
		stackArray = new Number[src.stackArray.length];
		amountOfElements = src.amountOfElements;
		copyElements(stackArray, src.stackArray, amountOfElements);
	    }

	    public void init () {
		amountOfElements = 0;
	    }

	    private void increaseStackSize () {
		Number[] result = new Number[2 * stackArray.length];
		copyElements(result, stackArray, amountOfElements);
	        stackArray = result;
	    }

	    public void push (Number element) {
		if (amountOfElements == stackArray.length) {
		    increaseStackSize();
		}

		stackArray[amountOfElements] = new Number(element);
		amountOfElements += 1;
	    }

	    public Number pop () {
		amountOfElements -= 1;
		return stackArray[amountOfElements];
	    }

	    public Number top () {
		return new Number(stackArray[amountOfElements - 1]);
	    }

	    public boolean isEmpty () {
		return amountOfElements == 0;
	    }

	    public int size () {
		return amountOfElements;
	    }
	   
	} 

}
