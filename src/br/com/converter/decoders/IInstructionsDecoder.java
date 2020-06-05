package br.com.converter.decoders;

import java.util.Arrays;
import java.util.List;

import br.com.converter.decoders.utils.CommonMethods;
import br.com.converter.decoders.utils.DecimalToBinary;
import br.com.converter.enumerations.EnumInstructionsI;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class class that decodes MIPS I instructions to binary machine value
 */
public class IInstructionsDecoder extends CommonMethods {

	/*
	 * @return String with decoded Load or Store type instructions
	 */
	private String decodeLoadOrStoreInstruction(String operation, String registerRT, String immediate,
			String registerRS) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	/*
	 * @return String with decoded Logical type instructions
	 */
	private String decodeLogicalInstruction(String operation, String registerRT, String registerRS, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	/*
	 * @return String with decoded branch type instructions
	 */
	private String decodeBranchInstruction(String operation, String registerRS, String registerRT, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	/*
	 * @return String with decoded BLBG instruction
	 */
	private String decodeBLOrBGInstruction(String operation, String registerRS, String immediate) {
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

	/*
	 * @return String with decoded Load Upper immediate instruction
	 */
	private String loadUpperInstruction(String operation, String registerRT, String immediate) {
		String decodedInstruction = "";
		decodedInstruction += attachOpCode(operation);
		decodedInstruction += attachImmediate("0", 5);
		decodedInstruction += attachRegister(registerRT);
		decodedInstruction += attachImmediate(immediate, 16);
		return decodedInstruction;
	}

	/*
	 * @return String with binary decoded instruction
	 */
	public String decode(List<String> list) {
		// define first instructions filter according list size
		switch (list.size()) {
		case 3:
			if (list.get(0).equals("LUI")) {
				return loadUpperInstruction(list.get(0), list.get(1), list.get(2));
			}
			return decodeBLOrBGInstruction(list.get(0), list.get(1), list.get(2));
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

	/*
	 * @return String get the MIPS I operation code
	 */
	protected String attachOpCode(String opCode) {
		return EnumInstructionsI.valueOf(opCode).getOpCode();
	}

	/*
	 * @return String get the immediate binary value
	 */
	private String attachImmediate(String immediate, int indexSize) {
		// verify if is there a negative value
		if (immediate.contains("-")) {
			return new DecimalToBinary().convertToNegativeBinary(immediate, indexSize);
		}
		return new DecimalToBinary().convertToBinary(immediate, indexSize);
	}

	/*
	 * @return Boolean return true if has a certain instruction in I instructions
	 * bank
	 */
	public boolean hasInstruction(String instruction) {
		List<EnumInstructionsI> iInstructions = Arrays.asList(EnumInstructionsI.values());
		return iInstructions.toString().contains(instruction);
	}
}
