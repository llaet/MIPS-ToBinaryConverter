package br.com.main;

import java.util.Arrays;
import java.util.List;

public class IInstructionsDecoder extends CommonMethods {

	private String decodeLoadOrStoreInstruction(String operation, String registerRT, String immediate,
			String registerRS) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	private String decodeLogicalInstruction(String operation, String registerRT, String registerRS, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	private String decodeBranchInstruction(String operation, String registerRS, String registerRT, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	private String decodeBLEZOrBGTZInstruction(String operation, String registerRS, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachImmediate("0", 5);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	private String decodeBLBGInstruction(String operation, String registerRS, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		if (operation.equals("BLTZ") | operation.equals("BLEZ") | operation.equals("BGTZ")) {
			decodedInstruction += attachImmediate("0", 5);
		} else if (operation.equals("BGEZ")) {
			decodedInstruction += attachImmediate("1", 5);
		} else if (operation.equals("BLTZAL")) {
			decodedInstruction += attachImmediate("16", 5);
		} else {// in case of BGEZAL
			decodedInstruction += attachImmediate("17", 5);
		}
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	private String loadUpperInstruction(String operation, String registerRT, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachImmediate("0", 5);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	public String decode(List<String> list) {
		// TextReader.progressBar.setValue(50);
		switch (list.size()) {
		case 3:
			if (list.get(0).equals("LUI")) {
				return loadUpperInstruction(list.get(0), list.get(1), list.get(2));
			} else if (list.get(0).equals("BLEZ") || list.get(0).equals("BGTZ")) {
				return decodeBLEZOrBGTZInstruction(list.get(0), list.get(1), list.get(2));
			}
			return decodeBLBGInstruction(list.get(0), list.get(1), list.get(2));
		case 4:
			if (list.get(0).equals("LW") || list.get(0).equals("SW")) {
				return decodeLoadOrStoreInstruction(list.get(0), list.get(1), list.get(2), list.get(3));
			} else if (list.get(0).equals("BEQ") || list.get(0).equals("BNE")) {
				return decodeBranchInstruction(list.get(0), list.get(1), list.get(2), list.get(3));
			}
			return decodeLogicalInstruction(list.get(0), list.get(1), list.get(2), list.get(3));
		default:
			return null;
		}

	}

	protected String attachOpCode(String opCode) {
		return EnumInstructionsI.valueOf(opCode).getOpCode();
	}

	private String attachImmediate(String immediate, int indexSize) {
		if (immediate.contains("-")) {
			return new DecimalToBinary().convertToNegativeBinary(immediate, indexSize);
		}
		return new DecimalToBinary().convertToBinary(immediate, indexSize);
	}

	public boolean hasInstruction(String instruction) {
		List<EnumInstructionsI> iInstructions = Arrays.asList(EnumInstructionsI.values());
		return iInstructions.toString().contains(instruction);
	}
}
