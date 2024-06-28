package de.upb.se.profcalculator;


public class Value {

	Integer value;

	public Value(int value){
		this.value = value;
	}
	public Value() {
		this.value = 0;
	}
	public String toString(){
		return value.toString();
	}

	public int getValue() { 
		return value.intValue();
	}

	public static Value parseValue(String zahl) {
		return new Value(Integer.parseInt(zahl));
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		return value.equals(other.value);
	}
	
	public int hashCode() {
		return value.hashCode();
	}
}
