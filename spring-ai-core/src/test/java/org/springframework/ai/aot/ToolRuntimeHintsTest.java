package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.mockito.Mockito.verify;
import org.junit.jupiter.api;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class ToolRuntimeHintsTest {

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void validateDefaultToolCallResultConverterHintRegistration() {

		RuntimeHints runtimeHints = Mockito.mock(RuntimeHints.class);
		RuntimeHints.ReflectionHints reflectionHints = Mockito.mock(RuntimeHints.ReflectionHints.class);
		Mockito.when(runtimeHints.reflection()).thenReturn(reflectionHints);
		ToolRuntimeHints toolRuntimeHints = new ToolRuntimeHints();

		assertDoesNotThrow(() -> toolRuntimeHints.registerHints(runtimeHints, null));

		verify(reflectionHints).registerType(DefaultToolCallResultConverter.class, MemberCategory.values());
	}

}