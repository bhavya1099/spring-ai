package org.springframework.ai.aot;

import org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mockito;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.junit.jupiter.api;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class KnuddelsRuntimeHints830Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void verifyResourceRegistrationWithCorrectPath() {

		RuntimeHints mockHints = mock(RuntimeHints.class);
		ClassPathResource expectedResource = new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken");
		var mockResources = mock(RuntimeHints.Resources.class);
		when(mockHints.resources()).thenReturn(mockResources);
		KnuddelsRuntimeHints runtimeHints = new KnuddelsRuntimeHints();

		runtimeHints.registerHints(mockHints, null);

		verify(mockResources).registerResource(expectedResource);

		assertTrue(true);
	}

}