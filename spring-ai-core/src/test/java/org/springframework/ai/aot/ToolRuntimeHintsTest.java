package org.springframework.ai.aot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.lang.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;

public class ToolRuntimeHintsTest {

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@BeforeEach
	void setUp() {
		toolRuntimeHints = new ToolRuntimeHints();
		runtimeHints = Mockito.mock(RuntimeHints.class);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("invalid")
	public void registerHintsWithNullHints() {

		RuntimeHints nullHints = null;
		@Nullable
		ClassLoader classLoader = null;

		assertDoesNotThrow(() -> {
			toolRuntimeHints.registerHints(nullHints, classLoader);
		});
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsWithNullClassLoader() {

		@Nullable
		ClassLoader classLoader = null;

		assertDoesNotThrow(() -> {
			toolRuntimeHints.registerHints(runtimeHints, classLoader);
		});

		verify(runtimeHints.reflection(), atLeastOnce()).registerType(eq(DefaultToolCallResultConverter.class),
				any(MemberCategory[].class));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void registerHintsWithAllMemberCategories() {

		var expectedMemberCategories = MemberCategory.values();
		@Nullable
		ClassLoader classLoader = null;

		assertDoesNotThrow(() -> toolRuntimeHints.registerHints(runtimeHints, classLoader));

		verify(runtimeHints.reflection(), atLeastOnce()).registerType(DefaultToolCallResultConverter.class,
				expectedMemberCategories);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsExecutionWithoutErrors() {

		@Nullable
		ClassLoader classLoader = null;

		assertDoesNotThrow(() -> toolRuntimeHints.registerHints(runtimeHints, classLoader));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerHintsCorrectClassReflection() {

		var expectedMemberCategories = MemberCategory.values();
		@Nullable
		ClassLoader classLoader = null;

		toolRuntimeHints.registerHints(runtimeHints, classLoader);

		verify(runtimeHints.reflection(), atLeastOnce()).registerType(eq(DefaultToolCallResultConverter.class),
				eq(expectedMemberCategories));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void registerHintsWithEmptyRuntimeHints() {

		RuntimeHints emptyRuntimeHints = new RuntimeHints();

		toolRuntimeHints.registerHints(emptyRuntimeHints, null);

		assertNotNull(emptyRuntimeHints.reflection());

	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a64fc3b94a
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void registerHintsWithEmptyMemberCategory() {

		RuntimeHints hints = Mockito.mock(RuntimeHints.class);
		@Nullable
		ClassLoader classLoader = null;

		Mockito.when(MemberCategory.values()).thenReturn(new MemberCategory[0]);

		toolRuntimeHints.registerHints(hints, classLoader);

		verify(hints.reflection(), never()).registerType(DefaultToolCallResultConverter.class, new MemberCategory[0]);
	}

}