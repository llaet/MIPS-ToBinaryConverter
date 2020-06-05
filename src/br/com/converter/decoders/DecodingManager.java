package br.com.converter.decoders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture disciplin
 */
public class DecodingManager {

	private List<String> decodedInstructions;

	/*
	 * processes a .txt file removing: ',' - '(' - ')' or ' ' chars
	 * 
	 * @return List<String> with the lines already converted to binary machine
	 * language
	 */
	public List<String> textProcessing(List<String> textDocument) {
		if (!(textDocument.isEmpty())) {
			StringBuilder sb = new StringBuilder();
			textDocument.forEach(line -> {
				List<String> lineDiggested = new ArrayList<>();
				// diggests each line (index) of a list received and decode it with
				// decodeInstruction method
				for (char character : line.toCharArray()) {
					if (character == ' ' | character == ',' | character == '(' | character == ')') {
						// verify if ' ' (space) char isn't at init of the line
						if (!(sb.toString().isBlank())) {
							lineDiggested.add(sb.toString());
						}
						// reset the data of the StringBuilder to get another line
						sb.setLength(0);
					} else {
						sb.append(character);
					}
				}
				// get last char from each list line (index)
				lineDiggested.add(sb.toString().strip());
				sb.setLength(0);
				// calls decoder method
				decodeInstruction(lineDiggested);
			});
			return decodedInstructions;
		} else {
			JOptionPane.showMessageDialog(null, "The file is empty. Please open a new file.");
			return Arrays.asList("");
		}
	}

	/*
	 * Fill the String list decodedInstructions with each line received from
	 * textProcessing method use the @Class InstructionsDecoder of I, J or R MIPS
	 * instructions to convert for binary
	 */
	private void decodeInstruction(List<String> list) {
		RInstructionsDecoder r = new RInstructionsDecoder();
		JInstructionsDecoder j = new JInstructionsDecoder();
		IInstructionsDecoder i = new IInstructionsDecoder();
		// filter eventually blank or null index
		List<String> formattedInstruction = list.stream().filter(object -> !(object.isBlank()) && object != null)
				.collect(Collectors.toList());
		// verify which type of MIPS instruction is from list received
		if (r.hasInstruction(formattedInstruction.get(0))) {
			decodedInstructions.add(r.decode(formattedInstruction));
		} else if (j.hasInstruction(formattedInstruction.get(0))) {
			decodedInstructions.add(j.decode(formattedInstruction));
		} else if (i.hasInstruction(formattedInstruction.get(0))) {
			decodedInstructions.add(i.decode(formattedInstruction));
		}
	}
}
