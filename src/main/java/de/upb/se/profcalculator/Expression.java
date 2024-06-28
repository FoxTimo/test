package de.upb.se.profcalculator;

public abstract class Expression {
	
	protected Value leftValue, rightValue;
	
	public Expression(Value leftValue, Value rightValue) {
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}
	
	public abstract int evaluate();
	public abstract String returnTerm();
	
	public String returnEquation() {
		return returnTerm()+ " = " + evaluate();
	}
	
	public int resultToInt() {
        return evaluate();
    }
}