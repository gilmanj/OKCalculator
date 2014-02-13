package com.android.okcalculator;

public enum Key {
	
	ZERO("0", KeyTypes.NUMBER),
	ONE("1", KeyTypes.NUMBER),
	TWO("2", KeyTypes.NUMBER),
	THREE("3", KeyTypes.NUMBER),
	FOUR("4", KeyTypes.NUMBER),
	FIVE("5", KeyTypes.NUMBER),
	SIX("6", KeyTypes.NUMBER),
	SEVEN("7", KeyTypes.NUMBER),
	EIGHT("8", KeyTypes.NUMBER),
	NINE("9", KeyTypes.NUMBER),
	DECIMAL(".", KeyTypes.DECIMAL),
	PLUS("+", KeyTypes.OPERATOR),
	MINUS("-", KeyTypes.OPERATOR),
	MULTIPLY("*", KeyTypes.OPERATOR),
	DIVIDE("/", KeyTypes.OPERATOR),
	ENTER("=", KeyTypes.ENTER),
	BCKSP("<", KeyTypes.BACKSPACE),
	CLEAR("C", KeyTypes.CLEAR),
	SQUAREROOT("√", KeyTypes.SQUAREROOT),
	BLANK("",KeyTypes.BLANK);
	
	private CharSequence text;
	private KeyTypes keyType;
	
	Key(CharSequence text, KeyTypes keyType) {
		this.text = text;
		this.keyType = keyType;
	}
	
	public CharSequence getText() {
		return this.text;
	}
	
	public String getTextString() {
		return this.text.toString();
	}
	
	public KeyTypes getKeyType() {
		return this.keyType;
	}
}
