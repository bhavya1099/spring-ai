package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class KnuddelsRuntimeHints839Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerResourceHintsCorrectPath() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsWithNullClassLoader() {
		RuntimeHints runtimeHints = new RuntimeHints();
		assertDoesNotThrow(() -> knuddelsRuntimeHints.registerHints(runtimeHints, null));
		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsForNonExistentResource() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();

	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("integration")
	public void registerHintsDoesNotOverwriteExistingHints() {
		RuntimeHints runtimeHints = new RuntimeHints();
		runtimeHints.resources().registerResource(new ClassPathResource("/pre/existing/resource"));
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertThat(runtimeHints.resources().hasResource(new ClassPathResource("/pre/existing/resource"))).isTrue();
		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsWithEmptyRuntimeHints() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);
		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("invalid")
	public void registerHintsWithMalformedResourcePath() {
		RuntimeHints runtimeHints = new RuntimeHints();

		assertDoesNotThrow(() -> knuddelsRuntimeHints.registerHints(runtimeHints, null));
		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_bc6a96ef8a
	 * ROOST_METHOD_SIG_HASH=registerHints_4964e90a69
	 *
	 */@Test
	@Tag("valid")
	public void registerResourceTypeCorrectness() {
		RuntimeHints runtimeHints = new RuntimeHints();
		knuddelsRuntimeHints.registerHints(runtimeHints, null);

		assertThat(runtimeHints.resources()
			.hasResource(new ClassPathResource("/com/knuddels/jtokkit/cl100k_base.tiktoken"))).isTrue();
		assertThat(runtimeHints.resources().getResources()).allMatch(r -> r instanceof ClassPathResource);
	}

}