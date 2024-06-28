package de.upb.se.profcalculator.calculation;

import de.upb.se.profcalculator.Value;
import de.upb.se.profcalculator.Expression;

public class Subtraction extends Expression {


	
	public Subtraction(Value leftValue, Value rightValue) {
		super(leftValue, rightValue);
		

	}
	public String returnTerm() {
		return leftValue.toString() + " - " + rightValue.toString();
	}
	
	
	@Override
	public int evaluate() {
		return leftValue.getValue() - rightValue.getValue();
	}
	
	
}