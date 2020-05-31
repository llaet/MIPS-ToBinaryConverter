package br.com.main;

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
