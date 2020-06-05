package br.com.converter.enumerations;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class enumeration class that has the binary values from R instructions
 *        operations code
 */
public enum EnumInstructionsR {

	ADD("100000"), ADDU("100001"), SUB("100010"), SUBU("100011"), AND("100100"), OR("100101"), XOR("100110"),
	NOR("100111"), SLT("101010"), SLTU("101011"), SLL("000000"), SRL("000010"), SRA("000011"), SLLV("000100"),
	SRLV("000110"), SRAV("000111"), MFHI("010000"), MTHI("010001"), MFLO("010010"), MTLO("010011"), MULT("011000"),
	MULTU("011001"), DIV("011010"), DIVU("011011"), JR("001000"), JALR("001001");

	private String opCode;

	EnumInstructionsR(String opCode) {
		this.opCode = opCode;
	}

	public String getOpCode() {
		return opCode;
	}

}
