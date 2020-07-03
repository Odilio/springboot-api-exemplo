package com.unisoma.service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BalancedService {
    
    public List<String> getBalanced(List<String> expressions) {
    	 return isBalanced(expressions);
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
