package br.com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import br.com.converter.decoders.IInstructionsDecoder;

public class IInstructionsDecoderTest {

	private IInstructionsDecoder iDecoder;

	public IInstructionsDecoderTest() {
		iDecoder = new IInstructionsDecoder();
	}

	@Test
	public void decodeLoadOrStoreInstructionTest() {
		String instructionDecoded = iDecoder.decode(Arrays.asList("LW", "$S0", "1235", "$ZERO"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "10001100000100000000010011010011");
	}

	@Test
	public void decodeLogicalInstructionTest() {
		String instructionDecoded = iDecoder.decode(Arrays.asList("ADDI", "$T0", "$S1", "22"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00100010001010000000000000010110");
		// negative immediate assertion test
		assertEquals(iDecoder.decode(Arrays.asList("ADDI", "$T0", "$S1", "-22")), "00100010001010001111111111101010");
	}

	@Test
	public void decodeBranchInstructionTest() {
		String instructionDecoded = iDecoder.decode(Arrays.asList("BEQ", "$S1", "$S2", "8"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00010010001100100000000000001000");
		// negative immediate assertion test
		assertEquals(iDecoder.decode(Arrays.asList("BEQ", "$S1", "$S2", "-8")), "00010010001100101111111111111000");
	}

	@Test
	public void decodeBLOrBGInstructionTest() {
		String instructionDecoded = iDecoder.decode(Arrays.asList("BGTZ", "$S1", "8"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00011110001000000000000000001000");
		// negative immediate assertion test
		assertEquals(iDecoder.decode(Arrays.asList("BGTZ", "$S1", "-8")), "00011110001000001111111111111000");
	}

	@Test
	public void loadUpperInstructionTest() {
		String instructionDecoded = iDecoder.decode(Arrays.asList("LUI", "$S1", "8"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00111100000100010000000000001000");
		// negative immediate assertion test
		assertEquals(iDecoder.decode(Arrays.asList("LUI", "$S1", "-8")), "00111100000100011111111111111000");
	}
}
