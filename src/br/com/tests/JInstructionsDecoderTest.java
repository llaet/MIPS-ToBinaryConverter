package br.com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import br.com.converter.decoders.JInstructionsDecoder;

public class JInstructionsDecoderTest {

	private JInstructionsDecoder jDecoder;

	public JInstructionsDecoderTest() {
		jDecoder = new JInstructionsDecoder();
	}

	@Test
	public void decodeJumpInstructionTest() {
		String instructionDecoded = jDecoder.decode(Arrays.asList("J", "1024"));
		assertNotNull(instructionDecoded);
		assertEquals(instructionDecoded, "00001000000000000000010000000000");
	}
}
