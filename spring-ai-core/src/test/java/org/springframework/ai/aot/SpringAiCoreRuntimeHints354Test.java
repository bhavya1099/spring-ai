package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHints.ReflectionHints;
import org.springframework.aot.hint.RuntimeHints.ResourceHints;
import org.springframework.ai.chat.messages;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.Set;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.function.DefaultFunctionCallbackResolver;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class SpringAiCoreRuntimeHints354Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void verifyReflectionTypeRegistrationForChatTypes() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		runtimeHintsRegistrar.registerHints(hints, null);
		Set<Class<?>> expectedChatTypes = Set.of(AbstractMessage.class, AssistantMessage.class,
				ToolResponseMessage.class, Message.class, MessageType.class, UserMessage.class, SystemMessage.class,
				DefaultFunctionCallbackResolver.class, FunctionCallback.class);
		for (Class<?> chatType : expectedChatTypes) {
			verify(reflectionHints).registerType(chatType);
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void validateReflectionMethodRegistrationForFunctionCallback() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		runtimeHintsRegistrar.registerHints(hints, null);
		assertNotNull((Object) getDescription, "Method getDescription should be found.");
		assertNotNull((Object) getInputTypeSchema, "Method getInputTypeSchema should be found.");
		assertNotNull((Object) getName, "Method getName should be found.");
		verify(reflectionHints).registerMethod(getDescription, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getInputTypeSchema, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getName, ExecutableMode.INVOKE);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void confirmExternalResourceRegistration() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ResourceHints resourceHints = mock(ResourceHints.class);
		when(hints.resources()).thenReturn(resourceHints);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(resourceHints).registerResource(
				new ClassPathResource("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4"));
		verify(resourceHints)
			.registerResource(new ClassPathResource("embedding/embedding-model-dimensions.properties"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void handleNullClassLoaderGracefully() {
		RuntimeHints hints = mock(RuntimeHints.class);
		assertDoesNotThrow(() -> runtimeHintsRegistrar.registerHints(hints, null));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void validateFindMethodFunctionality() {
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		assertNotNull((Object) getDescription, "Method getDescription should be found.");
		assertNotNull((Object) getInputTypeSchema, "Method getInputTypeSchema should be found.");
		assertNotNull((Object) getName, "Method getName should be found.");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void verifyHandlingOfEmptyChatTypesSet() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);

		assertDoesNotThrow(() -> runtimeHintsRegistrar.registerHints(hints, null));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("boundary")
	public void testDuplicateClassRegistration() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(reflectionHints, times(1)).registerType(AbstractMessage.class);
		verify(reflectionHints, times(1)).registerType(AssistantMessage.class);

	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void verifyMultipleResourceRegistration() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ResourceHints resourceHints = mock(ResourceHints.class);
		when(hints.resources()).thenReturn(resourceHints);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(resourceHints, times(1)).registerResource(
				new ClassPathResource("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4"));
		verify(resourceHints, times(1))
			.registerResource(new ClassPathResource("embedding/embedding-model-dimensions.properties"));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void verifyExecutableModeInvokeUsage() {
		RuntimeHints hints = mock(RuntimeHints.class);

		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		assertNotNull((Object) getDescription, "Method getDescription should be found.");
		assertNotNull((Object) getInputTypeSchema, "Method getInputTypeSchema should be found.");
		assertNotNull((Object) getName, "Method getName should be found.");
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(reflectionHints).registerMethod(getDescription, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getInputTypeSchema, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getName, ExecutableMode.INVOKE);
	}

}