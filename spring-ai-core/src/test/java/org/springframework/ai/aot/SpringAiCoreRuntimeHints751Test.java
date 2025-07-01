package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api;
import java.util.Set;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.function.DefaultFunctionCallbackResolver;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class SpringAiCoreRuntimeHints751Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerReflectionHintsForClasses() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		Class<?>[] expectedClasses = { AbstractMessage.class, AssistantMessage.class, ToolResponseMessage.class,
				Message.class, MessageType.class, UserMessage.class, SystemMessage.class,
				DefaultFunctionCallbackResolver.class, FunctionCallback.class };
		for (Class<?> clazz : expectedClasses) {
			assertTrue(runtimeHints.reflection().isRegistered(clazz));
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerReflectionHintsForFunctionCallbackMethods() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		Method[] expectedMethods = { ReflectionUtils.findMethod(FunctionCallback.class, "getDescription"),
				ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema"),
				ReflectionUtils.findMethod(FunctionCallback.class, "getName") };
		for (Method method : expectedMethods) {
			assertTrue(runtimeHints.reflection().isRegistered(method));
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerResourceHintsForFiles() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		String[] expectedResources = { "antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4",
				"embedding/embedding-model-dimensions.properties" };
		for (String resource : expectedResources) {
			assertTrue(runtimeHints.resources().isRegistered(new ClassPathResource(resource)));
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void handleNullClassLoader() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.reflection().isNonEmpty());
		assertTrue(runtimeHints.resources().isNonEmpty());
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void verifyReflectionUtilsInvocations() {
		RuntimeHints runtimeHints = Mockito.mock(RuntimeHints.class);
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		Mockito.verify(runtimeHints.reflection())
			.isRegistered(ReflectionUtils.findMethod(FunctionCallback.class, "getDescription"));
		Mockito.verify(runtimeHints.reflection())
			.isRegistered(ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema"));
		Mockito.verify(runtimeHints.reflection())
			.isRegistered(ReflectionUtils.findMethod(FunctionCallback.class, "getName"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerReflectionAndResourceHintsTogether() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.reflection().isNonEmpty());
		assertTrue(runtimeHints.resources().isNonEmpty());
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void handleEmptyRuntimeHintsInstance() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		assertTrue(runtimeHints.reflection().isNonEmpty());
		assertTrue(runtimeHints.resources().isNonEmpty());
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void validateExecutableModeForReflectionMethods() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		Method[] expectedMethods = { ReflectionUtils.findMethod(FunctionCallback.class, "getDescription"),
				ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema"),
				ReflectionUtils.findMethod(FunctionCallback.class, "getName") };
		for (Method method : expectedMethods) {
			assertTrue(runtimeHints.reflection().isRegistered(method));
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void handleNonNullRuntimeHints() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, this.getClass().getClassLoader());
		assertTrue(runtimeHints.reflection().isNonEmpty());
		assertTrue(runtimeHints.resources().isNonEmpty());
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void ensureNoDuplicateHints() {
		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		hintsRegistrar.registerHints(runtimeHints, null);
		int initialSize = runtimeHints.reflection().size();
		hintsRegistrar.registerHints(runtimeHints, null);
		int finalSize = runtimeHints.reflection().size();
		assertTrue(initialSize == finalSize);
	}

}