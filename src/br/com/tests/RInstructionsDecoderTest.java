package br.com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import br.com.converter.decoders.RInstructionsDecoder;

public class RInstructionsDecoderTest {

	private RInstructionsDecoder rDecoder;

	public RInstructionsDecoderTest() {
		rDecoder = new RInstructionsDecoder();
	}

	@Test
	public void decodeLogicalOrShiftVariableInstructionTest() {
		String instructionDecoded = rDecoder.decode(Arrays.asList("ADD", "$S1", "$S2", "$S3"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00000010010100111000100000100000");
	}

	@Test
	public void decodeShiftInstructionTest() {
		String instructionDecoded = rDecoder.decode(Arrays.asList("SRL", "$S4", "$S4", "12"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00000000000101001010001100000010");
		// negative immediate assertion test
		assertEquals(rDecoder.decode(Arrays.asList("SRL", "$S4", "$S4", "-12")), "00000000000101001010010100000010");
	}

	@Test
	public void decodeMoveFromInstructionTest() {
		String instructionDecoded = rDecoder.decode(Arrays.asList("MFHI", "$S4"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00000000000000001010000000010000");
	}

	@Test
	public void decodeMoveToOrJRInstructionTest() {
		String instructionDecoded = rDecoder.decode(Arrays.asList("MTHI", "$S4"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00000010100000000000000000010001");
	}

	@Test
	public void decodeMultOrDivideInstructionTest() {
		String instructionDecoded = rDecoder.decode(Arrays.asList("MULT", "$S3", "$S4"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00000010011101000000000000011000");
	}

	@Test
	public void decodeJALRInstructionTest() {
		String instructionDecoded = rDecoder.decode(Arrays.asList("JALR", "$S3", "$S4"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00000010100000001001100000001001");
	}
}
