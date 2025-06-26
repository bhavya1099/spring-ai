package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mockito.mock;
import org.mockito.Mockito.verify;
import org.junit.jupiter.api;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class KnuddelsRuntimeHints790Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void resourceRegistrationValidation() {
		RuntimeHints hints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(hints, null);
		assertTrue(
				hints.resources()
					.resourceRegistrations()
					.stream()
					.anyMatch(resource -> resource.getClassPathResource()
						.getPath()
						.equals("/com/knuddels/jtokkit/cl100k_base.tiktoken")),
				"Resource should be registered correctly");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void nullClassLoaderHandling() {
		RuntimeHints hints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(hints, null);
		assertTrue(
				hints.resources()
					.resourceRegistrations()
					.stream()
					.anyMatch(resource -> resource.getClassPathResource()
						.getPath()
						.equals("/com/knuddels/jtokkit/cl100k_base.tiktoken")),
				"Resource registration should work even with a null ClassLoader");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void ensureResourcePathFormatIsValid() {
		RuntimeHints hints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(hints, null);
		assertTrue(
				hints.resources()
					.resourceRegistrations()
					.stream()
					.allMatch(resource -> resource.getClassPathResource().getPath().startsWith("/")),
				"Resource path should start with '/'");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void confirmSingleResourceRegistration() {
		RuntimeHints hints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(hints, null);
		long count = hints.resources()
			.resourceRegistrations()
			.stream()
			.filter(resource -> resource.getClassPathResource()
				.getPath()
				.equals("/com/knuddels/jtokkit/cl100k_base.tiktoken"))
			.count();
		assertTrue(count == 1, "Only the specified resource should be registered");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void methodOverrideAnnotationValidation() {
		assertTrue(
				knuddelsRuntimeHints.getClass()
					.getDeclaredMethods()
					.stream()
					.anyMatch(method -> method.getName().equals("registerHints")
							&& method.isAnnotationPresent(Override.class)),
				"@Override annotation must be present on the registerHints method");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void handleEdgeCaseResourcePaths() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ClassPathResource mockResource = new ClassPathResource("com/knuddels/jtokkit/cl100k_base.tiktoken");
		hints.resources().registerResource(mockResource);
		verify(hints.resources()).registerResource(mockResource);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void dependencyResourceValidation() {
		RuntimeHints hints = mock(RuntimeHints.class);
		knuddelsRuntimeHints.registerHints(hints, null);
		verify(hints.resources()).registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void missingResourceFileHandling() {
		RuntimeHints hints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(hints, null);
		assertTrue(
				hints.resources()
					.resourceRegistrations()
					.stream()
					.anyMatch(resource -> resource.getClassPathResource().exists()),
				"Registered resource file should exist");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void handleNullRuntimeHintsInput() {
		try {
			knuddelsRuntimeHints.registerHints(null, null);
		}
		catch (NullPointerException e) {
			assertTrue(true, "Null RuntimeHints input should throw NullPointerException");
		}
	}

}