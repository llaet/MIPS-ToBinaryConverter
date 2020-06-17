package br.com.converter.decoders;

import java.util.Arrays;
import java.util.List;

import br.com.converter.decoders.utils.CommonMethods;
import br.com.converter.decoders.utils.DecimalToBinary;
import br.com.converter.enumerations.EnumInstructionsR;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture discipline
 * @Class class that decodes MIPS R instructions to binary machine value
 */
public class RInstructionsDecoder extends CommonMethods {

	/*
	 * @return String with decoded Logical or Shift Variable type R instructions
	 */
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

	/*
	 * @return String with decoded Shift R instructions
	 */
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

	/*
	 * @return String with decoded Move From R instructions
	 */
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

	/*
	 * @return String with decoded Move To or Jump R instructions
	 */
	private String decodeMoveToOrJRInstruction(String operation, String registerRS) {
		String decodedInstruction = "";
		decodedInstruction += attachShamt("0", 6);
		decodedInstruction += attachRegister(registerRS);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachShamt("0", 5);
		decodedInstruction += attachOpCode(operation);
		return decodedInstruction;
	}

	/*
	 * @return String with decoded Multiplication or Divide R instructions
	 */
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

	/*
	 * @return String with decoded JALR instruction
	 */
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

	/*
	 * @return String with binary decoded instruction
	 */
	public String decode(List<String> list) {
		// define first instructions filter according list size
		switch (list.size()) {
		case 2:
			if (list.get(0).equals("MFHI") || list.get(0).equals("MFLO")) {
				return decodeMoveFromInstruction(list.get(0), list.get(1));
			}
			return decodeMoveToOrJRInstruction(list.get(0), list.get(1));
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

	/*
	 * @return String get the MIPS R operation code
	 */
	protected String attachOpCode(String opCode) {
		return EnumInstructionsR.valueOf(opCode).getOpCode();
	}

	/*
	 * @return String with shamt binary value
	 */
	private String attachShamt(String shamtValue, int indexSize) {
		if (shamtValue.contains("-")) {
			return new DecimalToBinary().convertToNegativeBinary(shamtValue, indexSize);
		}
		return new DecimalToBinary().convertToBinary(shamtValue, indexSize);
	}

	/*
	 * @return Boolean return true if has a certain instruction in R instructions
	 * bank
	 */
	public boolean hasInstruction(String instruction) {
		List<EnumInstructionsR> rInstructions = Arrays.asList(EnumInstructionsR.values());
		return rInstructions.toString().contains(instruction);
	}

}
