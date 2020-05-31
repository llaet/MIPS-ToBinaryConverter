package br.com.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class TextWriter {

	private List<String> textDocument;
	List<String> decodedInstructions;

	public TextWriter(List<String> textDocument) {
		this.textDocument = textDocument;
		decodedInstructions = new ArrayList<>();
		textProcessing();
	}

	private void textProcessing() {
		if (!(textDocument.isEmpty())) {
			StringBuilder sb = new StringBuilder();
			textDocument.forEach(line -> {
				List<String> lineDiggested = new ArrayList<>();
				int index = 0;
				for (char character : line.toCharArray()) {
					if (character == ' ' | character == ',' | character == '(' | character == ')') {
						if (!(sb.toString().isBlank())) {
							lineDiggested.add(sb.toString());
							index++;
						}
						sb.setLength(0);
					} else {
						sb.append(character);
					}
				}
				lineDiggested.add(sb.toString().strip());
				sb.setLength(0);
				decodeInstruction(lineDiggested);
			});
			writeTextDocument();
		} else {
			JOptionPane.showMessageDialog(null, "The file is empty. Please open a new file.");
		}
	}

	private void decodeInstruction(List<String> list) {
		TextReader.progressBar.setValue(35);
		RInstructionsDecoder r = new RInstructionsDecoder();
		JInstructionsDecoder j = new JInstructionsDecoder();
		IInstructionsDecoder i = new IInstructionsDecoder();
		List<String> formattedInstruction = list.stream().filter(object -> !(object.isBlank()) && object != null)
				.collect(Collectors.toList());
		if (r.hasInstruction(formattedInstruction.get(0))) {
			decodedInstructions.add(r.decode(formattedInstruction));
		} else if (j.hasInstruction(formattedInstruction.get(0))) {
			decodedInstructions.add(j.decode(formattedInstruction));
		} else if (i.hasInstruction(formattedInstruction.get(0))) {
			decodedInstructions.add(i.decode(formattedInstruction));
		}
	}

	private void writeTextDocument() {
		TextReader.progressBar.setValue(75);
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(".\\decoded-instructions.txt"));
			for (String line : decodedInstructions) {
				fileWriter.write(line);
				fileWriter.newLine();
			}
			fileWriter.close();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe);
		}
	}
}
