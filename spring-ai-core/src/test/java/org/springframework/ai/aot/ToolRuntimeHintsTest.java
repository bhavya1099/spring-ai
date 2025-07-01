package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.junit.jupiter.api;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class ToolRuntimeHintsTest {

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void validateReflectionRegistrationNullClassLoader() {

		RuntimeHints runtimeHints = Mockito.mock(RuntimeHints.class);
		var mcs = MemberCategory.values();
		ToolRuntimeHints toolRuntimeHints = new ToolRuntimeHints();

		toolRuntimeHints.registerHints(runtimeHints, null);

		verify(runtimeHints).reflection().registerType(DefaultToolCallResultConverter.class, mcs);
	}

}