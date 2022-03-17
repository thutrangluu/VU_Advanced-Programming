package nl.vu.labs.phoenix.ap;

import java.math.BigInteger;
import java.util.Scanner;
import java.io.PrintStream;

public class Main {
	
	PrintStream out;
	
	private static final char OPEN_SET = '{';
	private static final char CLOSE_SET = '}';
	
	Main () {
		out = new PrintStream(System.out);
	}	
	
	private String setValue (SetInterface<BigInteger> set) {
		StringBuffer result = new StringBuffer();
		SetInterface<BigInteger> copy = set.copy();
		
		if (copy.isEmpty()) {
			result.append(OPEN_SET);
			result.append(CLOSE_SET);
		}else {
			result.append(OPEN_SET);
			result.append(copy.get());
			copy.remove(copy.get());
			while(!copy.isEmpty()){
				result.append(", ");
				result.append(copy.get());
				copy.remove(copy.get());
			}
			
			result.append(CLOSE_SET);
		}
		return result.toString(); 
	}
	
	private void printSet (String set) {
		System.out.println(set);
	}
	
	private void start() {
		InterpreterInterface<Set<BigInteger>> interpreter = new Interpreter<Set<BigInteger>>();
		Scanner in = new Scanner(System.in);

		Set<BigInteger> set;
		
		while(in.hasNextLine()) {
			set = interpreter.eval(in.nextLine());
			if (set != null) {
				printSet(setValue(set));
			} else continue;
		}
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}
