package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.mockito.Mockito;
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
	@Tag("valid")
	public void registerHintsWithValidParameters() {

		RuntimeHints hints = mock(RuntimeHints.class);
		RuntimeHints.ReflectionHints reflectionHints = mock(RuntimeHints.ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		ToolRuntimeHints toolRuntimeHints = new ToolRuntimeHints();

		toolRuntimeHints.registerHints(hints, null);

		verify(reflectionHints, times(1)).registerType(DefaultToolCallResultConverter.class, MemberCategory.values());
	}

}