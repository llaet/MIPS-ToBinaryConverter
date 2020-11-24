package br.com.converter.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.converter.decoders.DecodingManager;

/**
 * @author Lucas Laet - 2020 - MIPS .txt document converter to binary computer
 *         language .txt document Made for Computers Architecture disciplin
 * @Class class that write a binary decoded instructions in .txt document
 */
public class TextWriter {

	private List<String> textDocument;

	/*
	 * receive a raw MIPS .txt document
	 */
	public TextWriter(List<String> textDocument) {
		this.textDocument = textDocument;
	}

	/*
	 * decode MIPS code document with @Class DecodingManager fill new .txt file with
	 * binary machine code
	 */
	public void writeDecodedTextDocument() {
		try {
			DecodingManager decoder = new DecodingManager();
			List<String> decodedInstructions = decoder.textProcessing(textDocument);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(".\\instrucoes-convertidas.txt"));
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
