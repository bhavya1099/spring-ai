package org.springframework.ai.aot;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.core.io.ClassPathResource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api;

public class KnuddelsRuntimeHints613Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void resourceRegistrationWithCorrectPath() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void resourceRegistrationWithoutNullResource() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertNotNull(runtimeHints.resources().resourceHints());
		assertFalse(runtimeHints.resources().resourceHints().isEmpty());
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void resourceRegistrationWithInvalidPath() {
		RuntimeHints runtimeHints = new RuntimeHints();

		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertFalse(runtimeHints.resources().hasResource(new ClassPathResource("/invalid/path/resource.txt")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void registrationWithNullClassLoader() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void multipleResourceRegistrations() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		knuddelsRuntimeHints.registerHints(runtimeHints, null);

		assertEquals(1,
				runtimeHints.resources()
					.resourceHints()
					.stream()
					.filter(resource -> resource.getResource()
						.equals(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken")))
					.count());
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void verifyResourceType() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertEquals(ClassPathResource.class, runtimeHints.resources().resourceHints().get(0).getResource().getClass());
	}

}