package br.com.main;

import java.util.Arrays;
import java.util.List;

public class RInstructionsDecoder extends CommonMethods {

	private String decodeLogicalOrShiftVariableInstruction(String operation, String registerRD, String registerRS,
			String registerRT) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachRegister(registerRD);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	private String decodeShiftInstruction(String operation, String registerRD, String registerRT, String shamt) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachRegister(registerRD);
		decodedInstruction += attachShamt(shamt, 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	private String decodeMoveFromInstruction(String operation, String registerRD) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachRegister(registerRD);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	private String decodeMoveToOrJumpInstruction(String operation, String registerRS) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	private String decodeMultOrDivideInstruction(String operation, String registerRS, String registerRT) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	private String decodeJALRInstruction(String operation, String registerRD, String registerRS) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachRegister(registerRD);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	public String decode(List<String> list) {
		TextReader.progressBar.setValue(50);
		switch (list.size()) {
		case 2:
			if (list.get(0).equals("MFHI") || list.get(0).equals("MFLO")) {
				return decodeMoveFromInstruction(list.get(0), list.get(1));
			}
			return decodeMoveToOrJumpInstruction(list.get(0), list.get(1));
		case 3:
			if (list.get(0).equals("JALR")) {
				return decodeJALRInstruction(list.get(0), list.get(1), list.get(2));
			}
			return decodeMultOrDivideInstruction(list.get(0), list.get(1), list.get(2));
		case 4:
			if (list.get(3).substring(0, 1).equals("$")) {
				return decodeLogicalOrShiftVariableInstruction(list.get(0), list.get(1), list.get(2), list.get(3));
			}
			return decodeShiftInstruction(list.get(0), list.get(1), list.get(2), list.get(3));
		default:
			return null;
		}
	}

	protected String attachOpCode(String opCode) {
		return EnumInstructionsR.valueOf(opCode).getOpCode();
	}

	private String attachShamt(String shamtValue, int indexSize) {
		if (Integer.parseInt(shamtValue) == 0) {
			return "00000";
		} else if (shamtValue.contains("-")) {
			return new DecimalToBinary().convertToNegativeBinary(shamtValue, indexSize);
		}
		return new DecimalToBinary().convertToBinary(shamtValue, 6);
	}

	public boolean hasInstruction(String instruction) {
		List<EnumInstructionsR> rInstructions = Arrays.asList(EnumInstructionsR.values());
		return rInstructions.toString().contains(instruction);
	}

}
