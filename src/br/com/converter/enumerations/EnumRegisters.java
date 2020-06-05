package br.com.converter.enumerations;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class enumeration class that has the binary values from each of 17 main
 *        operational registers
 */
public enum EnumRegisters {

	$ZERO("00000"), $T0("01000"), $T1("01001"), $T2("01010"), $T3("01011"), $T4("01100"), $T5("01101"), $T6("01110"),
	$T7("01111"), $S0("10000"), $S1("10001"), $S2("10010"), $S3("10011"), $S4("10100"), $S5("10101"), $S6("10110"),
	$S7("10111");

	private String register;

	public String getRegister() {
		return register;
	}

	EnumRegisters(String register) {
		this.register = register;
	}

}
