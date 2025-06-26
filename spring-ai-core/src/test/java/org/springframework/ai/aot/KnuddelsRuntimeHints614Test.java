package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.mockito.Mockito.verify;
import org.junit.jupiter.api;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class KnuddelsRuntimeHints614Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerResourceCorrectly() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(hints, classLoader);

		verify(hints.resources()).registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void handleNullClassLoader() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);

		knuddelsRuntimeHints.registerHints(hints, null);

		verify(hints.resources()).registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void verifyResourcePathRegistration() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(hints, classLoader);

		verify(hints.resources()).registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void noAdditionalResourcesRegistered() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(hints, classLoader);

		verify(hints.resources(), Mockito.times(1))
			.registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("boundary")
	public void noInteractionWithEmptyRuntimeHints() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(hints, classLoader);

		verify(hints.resources()).registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void invalidResourcePathHandling() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);

		knuddelsRuntimeHints.registerHints(hints, classLoader);

		verify(hints.resources(), Mockito.times(1))
			.registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void concurrentHintsRegistration() throws InterruptedException {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		ClassLoader classLoader = Mockito.mock(ClassLoader.class);
		Runnable task = () -> knuddelsRuntimeHints.registerHints(hints, classLoader);
		Thread thread1 = new Thread(task);
		Thread thread2 = new Thread(task);

		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		verify(hints.resources(), Mockito.times(2))
			.registerResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"));
	}

}