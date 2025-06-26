package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.Mockito.mock;
import org.mockito.Mockito.verify;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class SpringAiCoreRuntimeHints23Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void registerHintsForChatTypes() {

		RuntimeHints runtimeHints = mock(RuntimeHints.class);
		RuntimeHintsRegistrar registrar = new SpringAiCoreRuntimeHints();

		registrar.registerHints(runtimeHints, null);

		verify(runtimeHints.reflection()).registerType(AbstractMessage.class);
		verify(runtimeHints.reflection()).registerType(AssistantMessage.class);
		verify(runtimeHints.reflection()).registerType(ToolResponseMessage.class);
		verify(runtimeHints.reflection()).registerType(Message.class);
		verify(runtimeHints.reflection()).registerType(MessageType.class);
		verify(runtimeHints.reflection()).registerType(UserMessage.class);
		verify(runtimeHints.reflection()).registerType(SystemMessage.class);
		verify(runtimeHints.reflection()).registerType(DefaultFunctionCallbackResolver.class);
		verify(runtimeHints.reflection()).registerType(FunctionCallback.class);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void registerHintsForMethods() {

		RuntimeHints runtimeHints = mock(RuntimeHints.class);
		RuntimeHintsRegistrar registrar = new SpringAiCoreRuntimeHints();

		registrar.registerHints(runtimeHints, null);

		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		assertNotNull(getDescription);
		verify(runtimeHints.reflection()).registerMethod(getDescription, ExecutableMode.INVOKE);
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		assertNotNull(getInputTypeSchema);
		verify(runtimeHints.reflection()).registerMethod(getInputTypeSchema, ExecutableMode.INVOKE);
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		assertNotNull(getName);
		verify(runtimeHints.reflection()).registerMethod(getName, ExecutableMode.INVOKE);
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("integration")
	public void registerHintsForResources() {

		RuntimeHints runtimeHints = mock(RuntimeHints.class);
		RuntimeHintsRegistrar registrar = new SpringAiCoreRuntimeHints();

		registrar.registerHints(runtimeHints, null);

		verify(runtimeHints.resources()).registerResource(
				new ClassPathResource("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4"));
		verify(runtimeHints.resources())
			.registerResource(new ClassPathResource("embedding/embedding-model-dimensions.properties"));
	}

}