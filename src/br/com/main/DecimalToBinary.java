package br.com.main;

public class DecimalToBinary {

	public String convertToBinary(String value, int numericHouses) {
		Long number = Long.parseLong(value);
		String result = number.toBinaryString(number);
		return "0".repeat(numericHouses - result.length()) + result;
	}

	public String convertToNegativeBinary(String value, int numericHouses) {
		Long number = Long.parseLong(value);
		String result = number.toBinaryString(number);
		return result.substring(result.length() - numericHouses, result.length());
	}
}
