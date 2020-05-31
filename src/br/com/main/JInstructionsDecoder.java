package br.com.main;

import java.util.Arrays;
import java.util.List;

public class JInstructionsDecoder {

	private String decodeJumpInstruction(String operation, String address) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachAddress(address);
		return decodedInstruction;
	}

	public String decode(List<String> list) {
		TextReader.progressBar.setValue(50);
		return decodeJumpInstruction(list.get(0), list.get(1));
	}

	private String attachOpCode(String opCode) {
		return EnumInstructionsJ.valueOf(opCode).getOpCode();
	}

	private String attachAddress(String addressValue) {
		return new DecimalToBinary().convertToBinary(addressValue, 26);
	}

	public boolean hasInstruction(String instruction) {
		List<EnumInstructionsJ> jInstructions = Arrays.asList(EnumInstructionsJ.values());
		return jInstructions.toString().contains(instruction);
	}
}
