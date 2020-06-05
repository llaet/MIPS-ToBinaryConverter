package br.com.converter.decoders;

import java.util.Arrays;
import java.util.List;

import br.com.converter.decoders.utils.DecimalToBinary;
import br.com.converter.enumerations.EnumInstructionsJ;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class class that decodes MIPS J instructions to binary machine value
 */
public class JInstructionsDecoder {

	/*
	 * @return String with decoded jump address binary code
	 */
	private String decodeJumpInstruction(String operation, String address) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachAddress(address);
		return decodedInstruction;
	}

	/*
	 * @return String with binary decoded instruction
	 */
	public String decode(List<String> list) {
		return decodeJumpInstruction(list.get(0), list.get(1));
	}

	/*
	 * @return String get the MIPS J operation code
	 */
	private String attachOpCode(String opCode) {
		return EnumInstructionsJ.valueOf(opCode).getOpCode();
	}

	/*
	 * @return String with memory address binary code
	 */
	private String attachAddress(String addressValue) {
		return new DecimalToBinary().convertToBinary(addressValue, 26);
	}

	/*
	 * @return Boolean return true if has a certain instruction in J instructions
	 * bank
	 */
	public boolean hasInstruction(String instruction) {
		List<EnumInstructionsJ> jInstructions = Arrays.asList(EnumInstructionsJ.values());
		return jInstructions.toString().contains(instruction);
	}
}
