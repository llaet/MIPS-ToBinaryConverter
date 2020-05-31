package br.com.main;

abstract class CommonMethods {

	abstract protected String attachOpCode(String opCode);

	protected String attachRegister(String register) {
		return EnumRegisters.valueOf(register).getRegister();
	}

}
