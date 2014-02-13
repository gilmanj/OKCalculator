package com.android.okcalculator;

import java.math.BigDecimal;
import java.util.LinkedList;

import android.annotation.SuppressLint;

public class CalcUtils {

	//This is best for vertical phone. Add different layout styles
	private int screenCapacity = 12;
	
	public double evaluate(LinkedList<Double> numbers, LinkedList<Key> operators) {
		
		//Do multiplication and division first, then do addition and subtraction. 
		//It's inelegant but it works
		LinkedList<Double> tempNumbers = new LinkedList<Double>();
		LinkedList<Key> tempOperators = new LinkedList<Key>();
		
		double tempResult = numbers.pop();
		while(!numbers.isEmpty()) {
			Key operation = operators.pop();
			double nextNum = numbers.pop();
			switch(operation) {
				//Just pass +/- through for the next round
				case PLUS:
					tempNumbers.add(tempResult);
					tempOperators.add(operation);
					tempResult = nextNum;
					break;
				case MINUS:
					tempNumbers.add(tempResult);
					tempOperators.add(operation);
					tempResult = nextNum;
					break;
				case MULTIPLY:
					tempResult = tempResult * nextNum;
					break;
				case DIVIDE:
					tempResult = tempResult / nextNum;
					break;
				default:
					//No other operations
			}
		}
		tempNumbers.add(tempResult);
		
		double result = tempNumbers.pop();
		while(!tempNumbers.isEmpty()) {
			Key operation = tempOperators.pop();
			double nextNum = tempNumbers.pop();
			switch(operation) {
				case PLUS:
					result =  result + nextNum;
					break;
				case MINUS:
					result = result - nextNum;
					break;
				default:
					//No other operations
			}
		}
		return result;
	}
	
	@SuppressLint("DefaultLocale")
	public String format(double d) {
    	if(Double.isInfinite(d)) {
    		return "undefined";
    	}
    	int decimalsAvailable = screenCapacity - this.getNumberOfWholeDigits(d);
    	BigDecimal bd = new BigDecimal(d);
    	d = bd.setScale(decimalsAvailable, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(d == (int) d) {
            return String.format("%d",(int)d);
        } else {
            return String.format("%s",d);
        }
    }
	
	//Get the number of digits to the left of the decimal
	public int getNumberOfWholeDigits(double d) {
		return Integer.toString((int)d).length();
	}
}
