package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.DefaultToolCallResultConverter;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
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
	@Tag("valid")
	public void checkValidNonNullRuntimeHintsBehavior() {

		RuntimeHints runtimeHintsMock = Mockito.mock(RuntimeHints.class);
		RuntimeHints.ReflectionHints reflectionHintsMock = Mockito.mock(RuntimeHints.ReflectionHints.class);
		Mockito.when(runtimeHintsMock.reflection()).thenReturn(reflectionHintsMock);
		ClassLoader nullClassLoader = null;

		toolRuntimeHints.registerHints(runtimeHintsMock, nullClassLoader);

		verify(reflectionHintsMock).registerType(DefaultToolCallResultConverter.class, MemberCategory.values());
	}

}