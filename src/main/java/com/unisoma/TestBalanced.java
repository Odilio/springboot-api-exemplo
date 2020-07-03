package com.unisoma;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TestBalanced {

	public static void main(String[] args) {
		List<String> test = new ArrayList<String>();
		test.add("a+(b*c)-2-a");
		test.add("(a+b*(2-c)-2+a)*2");
		test.add("2*(3-a))");
		test.add(")3+b*(2-c)(");
		test.add("(a*b-(2+c)");
		test.add("()");
		System.out.println(isBalanced(test));
	}
	
	  public static List<String> isBalanced(List<String> str) {
	    	List<String> results = new ArrayList<String>();
	    	for (String string : str) {
			boolean incorrect = false;
	    	String newStr = "";
	        char[] ch = string.toCharArray();
	        for (char c : ch) {
	            if ( c == '(' ||  c == ')') {
	                newStr = newStr.concat(c+"");
	             }
	        }
	        
	        if (newStr.length() % 2 != 0) {
	        	results.add("incorrect");
	        	incorrect= true;
	        	continue;
	        }
	        Deque<Character> deque = new LinkedList<>();
	        for (char chara : newStr.toCharArray()) {
	            if ( chara == '(') {
	                deque.addFirst(chara);
	            } else {
	                if (!deque.isEmpty() && ((deque.peekFirst() == '(' && chara == ')'))) {
	                    deque.removeFirst();
	                } else {
	                	results.add("incorrect");
	                	incorrect = true;
	                	break;
	                }
	            }
	        }
	        if (incorrect == false)
	        	results.add("correct");
	    }
	    	return results;
	    }
}