package example;

interface NumberStackInterface {

		/*
		 *
		 * Elements: numbers of the type Number (char)
		 * Structure: lineair (linear)
		 * Domain: all rows of numbers (all possible rows of char start with a letter)
		 *
		 * constructors
		 *
		 * NumberStack();
		 *   PRE  - 
		 *   POST -A new NumberStack-object has been made and contains the empty stack.
		 *
		 * NumberStack (NumberStack src);
		 *   PRE  - 
		 *   POST - A new NumberStack-object has been made and contains a copy of src.
		 *
		 */

		void init ();
		/* PRE  - (whats true b4)
		   POST - The stack is empty. (whats true after)
		*/

		void push (Number element);
		/* PRE  - 
		   POST - A copy of element is now on top of the stack.
		*/

		Number pop ();
		/* PRE  - The stack is not empty
		   POST - The top of the stack-PRE is returned and deleted.
		*/

		Number top ();
		/* PRE  - The stack is not empty
		   POST - A copy of the top of the stack has been returned.
		*/

		boolean isEmpty ();
		/* PRE  - 
		   POST - true:  The amount of elements of the stack equals 0.
		          false: the amount of elements of the stack is greater than 0.
		*/

		int size ();
		/* PRE  - 
		   POST - The amount of elements of the stack has been returned.
		*/

		length
		charAt
		add


		interfaces start with identifiers


		}


}
