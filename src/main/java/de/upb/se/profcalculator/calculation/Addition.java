package de.upb.se.profcalculator.calculation;

import de.upb.se.profcalculator.Expression;
import de.upb.se.profcalculator.Value;


public class Addition extends Expression {


	
	public Addition(Value leftValue, Value rightValue) {
		super(leftValue, rightValue);
		
	}
	public String returnTerm() {
		return leftValue.toString() + " + " + rightValue.toString();
	}
	
	
	@Override
	public int evaluate() {
		return leftValue.getValue() + rightValue.getValue();
	}
	
	
}
