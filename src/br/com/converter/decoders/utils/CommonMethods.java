package br.com.converter.decoders.utils;

import br.com.converter.enumerations.EnumRegisters;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class abstract class with R and I instructions common methods
 */
public abstract class CommonMethods {

	abstract protected String attachOpCode(String opCode);

	protected String attachRegister(String register) {
		return EnumRegisters.valueOf(register).getRegister();
	}

}
