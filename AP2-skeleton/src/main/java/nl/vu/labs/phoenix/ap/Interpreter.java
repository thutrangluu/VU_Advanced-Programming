package nl.vu.labs.phoenix.ap;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A set interpreter for sets of elements of type T
 */
public class Interpreter<T extends SetInterface<BigInteger>> implements InterpreterInterface<T> {

	private static int count;
	private HashMap<Identifier, T> map;
	private static final String SPACE = " ";
	private static final String EMPTY_STRING = "";

	private static final char PRINT_STATEMENT = '?';
	private static final char COMMENT = '/';		
	private static final char OPEN_SET = '{';
	private static final char CLOSE_SET = '}';
	private static final char OPEN_COMPLEX_FACTOR = '(';
	private static final char CLOSE_COMPLEX_FACTOR = ')';
	private static final char COMMA = ',';
	private static final char UNION = '+';
	private static final char INTERSECTION = '*';
	private static final char DIFFERENCE = '-';
	private static final char SYMDIFF = '|';
	private static final char ASSIGN = '=';
	private static final char ZERO = '0';
	
	public Interpreter () {
		map = new HashMap<Identifier, T>();
	}

	@Override
	public T getMemory(String v) {
		Scanner keyScanner = new Scanner(v).useDelimiter(EMPTY_STRING);
		Identifier key = null;
		try {
			key = identifier(keyScanner);
		} catch(APException e) {
			System.out.println(e);
		}
		return map.get(key);
	}	

	@Override
	public T eval(String s) {
		Scanner stringScanner = new Scanner(s).useDelimiter(EMPTY_STRING);
		T result = null;
		count = 0;
		ignoreInput(stringScanner, SPACE);

		try {
			result = statement(stringScanner); 
		} catch (APException e){
			result = null;
			System.out.println(e);
		}
		return result;
	}

	private void ignoreInput (Scanner in, String s) {
		while (in.hasNext(s)) {
			nextChar(in);
		}
	}

	private T statement(Scanner statementScanner) throws APException {
		T result;

		ignoreInput(statementScanner, SPACE);

		if (nextCharIsLetter(statementScanner)) { 
			result =  assignment(statementScanner);
		} else if (nextCharIs(statementScanner, PRINT_STATEMENT)) {
			nextChar(statementScanner);
			ignoreInput(statementScanner, SPACE);
			result =  printStatement(statementScanner);
		} else if (nextCharIs(statementScanner, COMMENT)) {
			nextChar(statementScanner);
			result =  comment(statementScanner);
		} else {
			throw new APException ("Invalid input");
		}

		ignoreInput(statementScanner, SPACE);

		return result;
	}

	private T assignment (Scanner assignmentScanner) throws APException {
		T set = null;
		Identifier identifier = null;

		ignoreInput(assignmentScanner, SPACE);

		// 1. Get identifier 
		identifier = identifier(assignmentScanner);
		// 2. Pass = character
		if (nextCharIs(assignmentScanner, ASSIGN)) {
			nextChar(assignmentScanner);
		} else throw new APException (String.format("Missing '%c'", ASSIGN));
		// 3. Get expression value
		set = expression(assignmentScanner);
		ignoreInput(assignmentScanner, SPACE);

		if (count != 0) {
			throw new APException ("Missing brakets for complex factors");
		}

		map.put(identifier, set);

		return null;
	}

	private T printStatement(Scanner printStatementScanner) throws APException {
		T set;
		
		ignoreInput(printStatementScanner, SPACE);
		set = expression(printStatementScanner);
		ignoreInput(printStatementScanner, SPACE);

		return set;
	}

	private T comment(Scanner commentScanner) {
		commentScanner.nextLine();
		return null;
	}

	private Identifier identifier (Scanner identifierScanner) throws APException {

		Identifier result = new Identifier();

		// 1. Init with first letter from input
		if (nextCharIsLetter(identifierScanner)) {
			result.init(nextChar(identifierScanner));
		}

		while (identifierScanner.hasNext()) {
			// 2. Check more char in Identifier
			if (nextCharIsLetter(identifierScanner) || nextCharIsDigit(identifierScanner)) {
				result.add(nextChar(identifierScanner));
				continue;
			} else  {
				// 3. Check spaces in identifier and = char
				ignoreInput(identifierScanner, SPACE);

				if (nextCharIs(identifierScanner,ASSIGN) || nextCharIs(identifierScanner, UNION) || nextCharIs(identifierScanner, DIFFERENCE) 
						|| nextCharIs(identifierScanner, SYMDIFF) || nextCharIs(identifierScanner, INTERSECTION) 
						|| (nextCharIs(identifierScanner, CLOSE_COMPLEX_FACTOR)  && count > 0)) {
					break; 
				} else throw new APException ("Invalid Identifier or statement. Identifier must start with a letter and contain only letters or natural numbers.");
			}
		}

		return result;
	}

	private T expression (Scanner expressionScanner) throws APException {
		ignoreInput(expressionScanner, SPACE);

		// 1. read first term 
		T result = term(expressionScanner);

		ignoreInput(expressionScanner, SPACE);

		// 2. Check other terms if available
		while (expressionScanner.hasNext()) {
			// 3. Check operators
			if (nextCharIs(expressionScanner, UNION) || nextCharIs(expressionScanner, DIFFERENCE) || nextCharIs(expressionScanner, SYMDIFF)) {
				char operator = nextChar(expressionScanner);
				// 4. Calculate and update result
				result = calculate(result, term (expressionScanner), operator);
				ignoreInput(expressionScanner, SPACE);
			} else if (nextCharIs(expressionScanner, CLOSE_COMPLEX_FACTOR) && count > 0) {
				break;
			} else if (nextCharIs(expressionScanner, CLOSE_COMPLEX_FACTOR) && count == 0) {
				count --;
			} else {
				throw new APException ("No operator detected");
			}
		}

		ignoreInput(expressionScanner, SPACE);
		return result;
	}

	private T calculate (T set1, T set2, char operator) {
		T result = null;

		if (operator == UNION) {
			result = (T) set1.union(set2);
		} else if (operator == DIFFERENCE) {
			result = (T) set1.difference(set2);
		} else if (operator == SYMDIFF) {
			result = (T) set1.symdiff(set2);
		} else if (operator == INTERSECTION)	{
			result = (T) set1.intersection(set2);
		}

		return result;
	}

	private T term (Scanner termScanner) throws APException {
		ignoreInput(termScanner, SPACE);

		// 1. read first factor
		T result = factor(termScanner);

		ignoreInput(termScanner, SPACE);

		// 2. Check other factors if available
		while (termScanner.hasNext()) {
			if (nextCharIs(termScanner, INTERSECTION)) {
		// 3. calculate and update result
				char operator = nextChar(termScanner);
				result = calculate(result,factor(termScanner),operator);
				ignoreInput(termScanner, SPACE);
			} else if (nextCharIs(termScanner, UNION) || nextCharIs(termScanner, DIFFERENCE) 
					|| nextCharIs(termScanner, SYMDIFF) || (nextCharIs(termScanner, CLOSE_COMPLEX_FACTOR) && count > 0)){
				break;
			} else if (nextCharIs(termScanner, CLOSE_COMPLEX_FACTOR) && count == 0) {
				count --;
			} else {
				throw new APException ("Unallowed character detected");
			}
		}

		return result;
	}

	private T factor (Scanner factorScanner) throws APException {
		T result;

		ignoreInput(factorScanner,SPACE);

		if (nextCharIsLetter (factorScanner)) {
			// 1. read an identifier
			Identifier id = identifier(factorScanner); 
			// 2. retrieve the set that belongs with that identifier
			if (map.containsKey(id)) { 
				result = map.get(id);
			} else {
				throw new APException(String.format("Identifier %s does not correspond to a set", id.value()));
			}
		} else if (nextCharIs(factorScanner, OPEN_SET)) {
			// 3. read a set
			result = set(factorScanner);
		} else if (nextCharIs(factorScanner, OPEN_COMPLEX_FACTOR)) {
			// 4. determine the set that is the result of the complex factor
			result = complexFactor(factorScanner);
		} else {
			throw new APException ("Invalid input. Factor syntax is wrong.");
		}

		ignoreInput(factorScanner,SPACE);

		return result;
	}

	private T complexFactor (Scanner complexFactorScanner) throws APException {
		nextChar(complexFactorScanner);
		count ++;

		T result = null;

		ignoreInput(complexFactorScanner,SPACE);
		if (nextCharIs(complexFactorScanner, CLOSE_COMPLEX_FACTOR)) {
			throw new APException ("Empty complex factor not allowed");
		}
		
		// 1. Check if there is factor to read
		while (!nextCharIs(complexFactorScanner, CLOSE_COMPLEX_FACTOR)) { 
			ignoreInput(complexFactorScanner, SPACE);
			if (!complexFactorScanner.hasNext()) {
				throw new APException(String.format("Missing '%c' in one or more complex factors", CLOSE_COMPLEX_FACTOR));
			}
		// 2. Evaluate complex factor
			result = expression(complexFactorScanner);

			ignoreInput(complexFactorScanner, SPACE);
		}

		// 3. Check end of factor
		if (!nextCharIs(complexFactorScanner, CLOSE_COMPLEX_FACTOR)) {
			throw new APException(String.format("Missing '%c' in one or more complex factors", CLOSE_COMPLEX_FACTOR));
		}
		nextChar(complexFactorScanner);
		count --;

		return result;
	}

	private T set (Scanner setScanner) throws APException {

		T result;
		// 1. Read open set
		nextChar(setScanner);
		ignoreInput(setScanner, SPACE);

		// 2. Read set contents
		result = naturalNumberRow(setScanner);

		// 3. check & read closing set }		
		if (!nextCharIs(setScanner,CLOSE_SET)) { 
			throw new APException (String.format("Missing '%c'", CLOSE_SET));
		}
		nextChar(setScanner);

		ignoreInput(setScanner, SPACE);

		// 4. check end of set, no other elements after closing
		while (setScanner.hasNext()) { 
			if (nextCharIs(setScanner, UNION) || nextCharIs(setScanner, DIFFERENCE) || nextCharIs(setScanner, SYMDIFF) 
					||  nextCharIs(setScanner, INTERSECTION) || (nextCharIs(setScanner, CLOSE_COMPLEX_FACTOR) && count > 0)) {
				break;
			} else if (nextCharIs(setScanner, CLOSE_COMPLEX_FACTOR) && count == 0) {
				count --;
			}
			else {
				throw new APException ("Unallowed character after set.");
			}
		}

		return result;
	}	

	private T naturalNumberRow(Scanner rowScanner) throws APException {
		SetInterface<BigInteger> result = new Set<BigInteger>();
		result.init();
		BigInteger num;

		// 1. Check if there is factor to read
		// empty sets are allowed
		while (!nextCharIs(rowScanner,CLOSE_SET)) {
			ignoreInput(rowScanner, SPACE);

			if (!rowScanner.hasNext()) {
				throw new APException (String.format("Missing '%c'", CLOSE_SET));
			}
		// 2. Read first element
			num = naturalNumber(rowScanner);
			result.add(num);
			ignoreInput(rowScanner, SPACE);
			
		// 2. Read next element and check comma
			while (nextCharIs(rowScanner,COMMA)) {
				if (nextCharIs(rowScanner,COMMA)) {
					nextChar(rowScanner);
					if(nextCharIs(rowScanner,COMMA)) {
						throw new APException ("Missing number");
					}
				}
				ignoreInput(rowScanner, SPACE);

				num = naturalNumber(rowScanner);
				result.add(num);
				
				ignoreInput(rowScanner, SPACE);
			}

			if (nextCharIsDigit(rowScanner)) {
				throw new APException ("Missing delimiter");
			}

			ignoreInput(rowScanner, SPACE);

		}

		return (T) result;
	}

	private BigInteger naturalNumber(Scanner input) throws APException {
		BigInteger result;
		StringBuffer number = new StringBuffer();

		ignoreInput(input, SPACE);

		if (nextCharIsDigit(input) && !nextCharIs(input,ZERO)) {
			while (nextCharIsDigit(input)) {
				number.append(nextChar(input));
			}
		} else if (nextCharIs(input, ZERO)) {
			number.append(nextChar(input));
			if (nextCharIsDigit(input)) {
				throw new APException("Number cannot start with '0'");
			}
		} else {
			throw new APException("Invalid element in set. Set can only consist of natural numbers separated by ','.");
		}

		ignoreInput(input, SPACE);

		return result = new BigInteger(number.toString());
	}


	private char nextChar (Scanner in) { // read next character from input
		return in.next().charAt(0);
	}

	boolean nextCharIs(Scanner in, char c) { // Method to check if the next character to be read when calling nextChar() is equal to the provided character
		return in.hasNext(Pattern.quote(c+""));
	}

	boolean nextCharIsDigit (Scanner in) { // Method to check if the next character to be read when calling nextChar() is a digit.
		return in.hasNext("[0-9]");
	}

	private boolean nextCharIsLetter (Scanner in) { // Method to check if the next character to be read when calling nextChar() is a letter
		return in.hasNext("[a-zA-Z]");
	}
}
