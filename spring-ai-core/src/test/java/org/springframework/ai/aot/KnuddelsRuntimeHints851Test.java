package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.junit.jupiter.api;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class KnuddelsRuntimeHints851Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void ensureResourceRegistration() {
		RuntimeHints runtimeHints = new RuntimeHints();
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.resources().hasResource(new ClassPathResource(REGISTERED_RESOURCE)),
				"Resource should be registered successfully in RuntimeHints.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void ensureNoExtraResourceRegistered() {
		RuntimeHints runtimeHints = new RuntimeHints();
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertEquals(1, runtimeHints.resources().getRegisteredResources().size(),
				"No additional resources should be registered.");
		assertTrue(runtimeHints.resources().hasResource(new ClassPathResource(REGISTERED_RESOURCE)),
				"Exact resource should be registered and nothing else.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void handleNullRuntimeHintsInstance() {
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		assertThrows(NullPointerException.class, () -> knuddelsRuntimeHints.registerHints(null, null),
				"Method should throw NullPointerException for null RuntimeHints.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void handleNullClassLoader() {
		RuntimeHints runtimeHints = new RuntimeHints();
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.resources().hasResource(new ClassPathResource(REGISTERED_RESOURCE)),
				"Resource should still be registered successfully with null ClassLoader.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void validateResourcePathFormat() {
		RuntimeHints runtimeHints = new RuntimeHints();
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		ClassPathResource registeredResource = new ClassPathResource(REGISTERED_RESOURCE);
		assertDoesNotThrow(registeredResource::exists, "Resource path should adhere to valid format.");
		assertTrue(runtimeHints.resources().hasResource(registeredResource),
				"Registered resource path must conform to valid ClassPathResource conventions.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void validateMethodOverrideCompliance() {
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		assertNotNull(
				knuddelsRuntimeHints.getClass()
					.getDeclaredMethod("registerHints", RuntimeHints.class, ClassLoader.class),
				"Method should be implemented and properly override the parent class's method.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void validateMockResourceRegistration() {
		RuntimeHints runtimeHints = mock(RuntimeHints.class);
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		ClassPathResource mockResource = mock(ClassPathResource.class);
		when(mockResource.getPath()).thenReturn(REGISTERED_RESOURCE);
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		verify(runtimeHints.resources(), times(1)).registerResource(mockResource);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void validateRuntimeHintsPostRegistration() {
		RuntimeHints runtimeHints = new RuntimeHints();
		KnuddelsRuntimeHints knuddelsRuntimeHints = new KnuddelsRuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.resources().hasResource(new ClassPathResource(REGISTERED_RESOURCE)),
				"RuntimeHints should properly contain the registered resource.");
		assertEquals(1, runtimeHints.resources().getRegisteredResources().size(),
				"RuntimeHints should contain exactly one registered resource.");
	}

}