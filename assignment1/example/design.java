package example;

import java.util.Scanner;
import java.io.PrintStream;


public class design {

	public design() {
		// TODO Auto-generated constructor stub
	}

	
	/* As explained during the lecture your program for assignment 1 will 
	   have 4 nested iterations. The following structured design shows 
	   how you could deal with the first 2 of these 4 iterations.
	*/

	    static final int MAX_NUMBER_OF_ELEMENTS = 10;

	    PrintStream out;

	    boolean askSet (Scanner input, String question, Set set) {
	        do {
	            out.printf("%s", question);
	            if (! input.hasNextLine()) {
	                out.printf("\n"); // otherwise line with ^D will be overwritten
	                return false;
	            }
	        } while (! inputContainsCorrectSet(input, set));
	        return true;
	    }


	    boolean askBothSets (Scanner input, Set set1, Set set2) {
	        return askSet(input, "Give first set : ", set1) &&
	               askSet(input, "Give second set : ", set2);
	    }


	    void start () {
	        Scanner in = new Scanner(System.in);
	        Set set1 = new Set(),
	            set2 = new Set();

	        while (askBothSets(in, set1, set2)) {
	            calculateAndGiveOutput(set1, set2);
	        }
	    }

	/* The method inputContainsCorrectSet(Scanner input, Set set) should, while
	   reading the input (the answer), check whether the input is correct.

	   If the input is not correct this method should (1) give an error explaining
	   what was wrong with the input, (2) skip the remaining characters of the input
	   and (3) return false.

	   If the input is correct this method should (1) assign to the parameter set
	   the value of the set of identifiers on the input and (2) return true.

	   N.B. Do not try to give too intelligent errors. This is not an exercise in
	        artificial intelligence.
	        For instance, if the input is "{abc def} gh" instead of "{abc def gh}",
	        an (relatively easy) error like "no input allowed after '}' is fine. You
	        don't have to write a program that seems to understand what you were 
	        trying to do and would give an error like "by accident you put the '}'
	        before the last identifier instead of after it. Please correct this."
	*/
	 

}
