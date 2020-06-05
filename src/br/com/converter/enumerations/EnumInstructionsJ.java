package br.com.converter.enumerations;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class enumeration class that has the binary values from J instructions
 *        operations code
 */
public enum EnumInstructionsJ {

	J("000010"), JAL("000011");

	private String opCode;

	EnumInstructionsJ(String opCode) {
		this.opCode = opCode;
	}

	public String getOpCode() {
		return opCode;
	}
}
