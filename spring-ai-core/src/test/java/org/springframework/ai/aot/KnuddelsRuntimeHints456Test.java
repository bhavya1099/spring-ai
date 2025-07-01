package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.core.io.ClassPathResource;
import org.junit.jupiter.api.Assertions.assertTrue;

public class KnuddelsRuntimeHints456Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsWithValidClassLoader() {

		RuntimeHints runtimeHints = new RuntimeHints();
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);

		assertTrue(runtimeHints.resources()
			.getRegisteredResources()
			.contains(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsWithNullClassLoader() {

		RuntimeHints runtimeHints = new RuntimeHints();
		ClassLoader classLoader = null;

		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);

		assertTrue(runtimeHints.resources()
			.getRegisteredResources()
			.contains(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void registerHintsWithNonExistentResourcePath() {

		RuntimeHints runtimeHints = new RuntimeHints();
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);
		ClassPathResource resource = new ClassPathResource("/com/knuddels/non_existent_resource.file");

		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);

		assertTrue(runtimeHints.resources().getRegisteredResources().contains(resource));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void registerHintsWithEmptyRuntimeHintsInstance() {

		RuntimeHints runtimeHints = new RuntimeHints();
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);

		assertTrue(runtimeHints.resources()
			.getRegisteredResources()
			.contains(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsCalledMultipleTimes() {

		RuntimeHints runtimeHints = new RuntimeHints();
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);
		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);

		assertTrue(runtimeHints.resources()
			.getRegisteredResources()
			.contains(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void registerHintsWithAlternateResourcePaths() {

		RuntimeHints runtimeHints = new RuntimeHints();
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);
		ClassPathResource alternateResource = new ClassPathResource("/com/knuddels/alternate/resource.file");

		knuddelsRuntimeHints.registerHints(runtimeHints, classLoader);

		assertTrue(runtimeHints.resources().getRegisteredResources().contains(alternateResource));
	}

}