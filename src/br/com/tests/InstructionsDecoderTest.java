package br.com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import br.com.main.IInstructionsDecoder;
import br.com.main.JInstructionsDecoder;
import br.com.main.RInstructionsDecoder;

public class InstructionsDecoderTest {

	private IInstructionsDecoder iDecoder;
	private RInstructionsDecoder rDecoder;
	private JInstructionsDecoder jDecoder;

	public InstructionsDecoderTest() {
		iDecoder = new IInstructionsDecoder();
		rDecoder = new RInstructionsDecoder();
		jDecoder = new JInstructionsDecoder();
	}

	@Test
	public void decodeLoadOrStoreIInstructionTest() {
		assertNotNull(iDecoder.decode(Arrays.asList("LW", "$S0", "1235", "$ZERO")));
		assertEquals(iDecoder.decode(Arrays.asList("LW", "$S0", "1235", "$ZERO")), "10001100000100000000010011010011");
	}

	@Test
	public void decodeLogicalInstructionIInstructionTest() {
		assertNotNull(iDecoder.decode(Arrays.asList("ADDI", "$T0", "$S1", "22")));
		assertEquals(iDecoder.decode(Arrays.asList("ADDI", "$T0", "$S1", "22")), "00100010001010000000000000010110");
		// negative immediate assertion test
		assertEquals(iDecoder.decode(Arrays.asList("ADDI", "$T0", "$S1", "-22")), "00100010001010001111111111101010");
	}

	@Test
	public void decodeBranchInstructionIInstructionTest() {
		assertNotNull(iDecoder.decode(Arrays.asList("BEQ", "$S1", "$S2", "8")));
		assertEquals(iDecoder.decode(Arrays.asList("BEQ", "$S1", "$S2", "8")), "00010010001100100000000000001000");
		// negative immediate assertion test
		assertEquals(iDecoder.decode(Arrays.asList("BEQ", "$S1", "$S2", "-8")), "00010010001100101111111111111000");
	}
}
