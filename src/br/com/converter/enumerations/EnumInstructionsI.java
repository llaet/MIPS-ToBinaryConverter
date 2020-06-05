package br.com.converter.enumerations;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class enumeration class that has the binary values from I instructions
 *        operations code
 */
public enum EnumInstructionsI {

	LB("100000"), LH("100001"), LWL("100010"), LW("100011"), LBU("100100"), LHU("100101"), LWR("100110"), SB("101000"),
	SH("101001"), SWL("101010"), SW("101011"), SWR("101110"), ADDI("001000"), ADDIU("001001"), SLTI("001010"),
	SLTIU("001011"), ANDI("001100"), ORI("001101"), XORI("001110"), LUI("001111"), BLTZ("000001"), BGEZ("000001"),
	BLTZAL("000001"), BGEZAL("000001"), BEQ("000100"), BNE("000101"), BLEZ("000110"), BGTZ("000111");

	private String opCode;

	EnumInstructionsI(String opCode) {
		this.opCode = opCode;
	}

	public String getOpCode() {
		return opCode;
	}
}
