
// ********RoostGPT********
/*

roost_feedback [03/07/2025, 3:59:58 PM]:remove compilation errors if any\n
*/

// ********RoostGPT********

package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHints.ReflectionHints;
import org.springframework.aot.hint.RuntimeHints.ResourceHints;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Set;

import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpringAiCoreRuntimeHints354Test {

	@Test
	@Tag("integration")
	public void verifyReflectionTypeRegistrationForChatTypes() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		runtimeHintsRegistrar.registerHints(hints, null);
		Set<Class<?>> expectedChatTypes = Set.of(AbstractMessage.class, AssistantMessage.class,
				ToolResponseMessage.class, Message.class, MessageType.class, UserMessage.class, SystemMessage.class,
				DefaultFunctionCallbackResolver.class, FunctionCallback.class);
		for (Class<?> chatType : expectedChatTypes) {
			verify(reflectionHints).registerType(chatType);
		}
	}

	@Test
	@Tag("integration")
	public void validateReflectionMethodRegistrationForFunctionCallback() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		runtimeHintsRegistrar.registerHints(hints, null);
		assertNotNull(getDescription, "Method getDescription should be found.");
		assertNotNull(getInputTypeSchema, "Method getInputTypeSchema should be found.");
		assertNotNull(getName, "Method getName should be found.");
		verify(reflectionHints).registerMethod(getDescription, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getInputTypeSchema, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getName, ExecutableMode.INVOKE);
	}

	@Test
	@Tag("valid")
	public void confirmExternalResourceRegistration() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ResourceHints resourceHints = mock(ResourceHints.class);
		when(hints.resources()).thenReturn(resourceHints);
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(resourceHints).registerResource(
				new ClassPathResource("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4"));
		verify(resourceHints)
				.registerResource(new ClassPathResource("embedding/embedding-model-dimensions.properties"));
	}

	@Test
	@Tag("boundary")
	public void handleNullClassLoaderGracefully() {
		RuntimeHints hints = mock(RuntimeHints.class);
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		assertDoesNotThrow(() -> runtimeHintsRegistrar.registerHints(hints, null));
	}

	@Test
	@Tag("valid")
	public void validateFindMethodFunctionality() {
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		assertNotNull(getDescription, "Method getDescription should be found.");
		assertNotNull(getInputTypeSchema, "Method getInputTypeSchema should be found.");
		assertNotNull(getName, "Method getName should be found.");
	}

	@Test
	@Tag("boundary")
	public void verifyHandlingOfEmptyChatTypesSet() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		assertDoesNotThrow(() -> runtimeHintsRegistrar.registerHints(hints, null));
	}

	@Test
	@Tag("boundary")
	public void testDuplicateClassRegistration() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(reflectionHints, times(1)).registerType(AbstractMessage.class);
		verify(reflectionHints, times(1)).registerType(AssistantMessage.class);
	}

	@Test
	@Tag("valid")
	public void verifyMultipleResourceRegistration() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ResourceHints resourceHints = mock(ResourceHints.class);
		when(hints.resources()).thenReturn(resourceHints);
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(resourceHints, times(1)).registerResource(
				new ClassPathResource("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4"));
		verify(resourceHints, times(1))
				.registerResource(new ClassPathResource("embedding/embedding-model-dimensions.properties"));
	}

	@Test
	@Tag("valid")
	public void verifyExecutableModeInvokeUsage() {
		RuntimeHints hints = mock(RuntimeHints.class);
		ReflectionHints reflectionHints = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(reflectionHints);
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		assertNotNull(getDescription, "Method getDescription should be found.");
		assertNotNull(getInputTypeSchema, "Method getInputTypeSchema should be found.");
		assertNotNull(getName, "Method getName should be found.");
		RuntimeHintsRegistrar runtimeHintsRegistrar = mock(RuntimeHintsRegistrar.class);
		runtimeHintsRegistrar.registerHints(hints, null);
		verify(reflectionHints).registerMethod(getDescription, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getInputTypeSchema, ExecutableMode.INVOKE);
		verify(reflectionHints).registerMethod(getName, ExecutableMode.INVOKE);
	}
}
