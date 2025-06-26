package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;
import java.util.Set;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.function.DefaultFunctionCallbackResolver;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class SpringAiCoreRuntimeHints734Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerReflectionTypeForChatMessages() {

		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints springAiCoreRuntimeHints = new SpringAiCoreRuntimeHints();
		Set<Class<?>> expectedTypes = Set.of(AbstractMessage.class, AssistantMessage.class, ToolResponseMessage.class,
				Message.class, MessageType.class, UserMessage.class, SystemMessage.class,
				DefaultFunctionCallbackResolver.class, FunctionCallback.class);

		springAiCoreRuntimeHints.registerHints(runtimeHints, null);

		for (var type : expectedTypes) {
			assertTrue(runtimeHints.reflection().isTypeRegistered(type),
					"Type " + type.getName() + " should be registered in reflection hints");
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerMethodsForFunctionCallbackClass() {

		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints springAiCoreRuntimeHints = new SpringAiCoreRuntimeHints();
		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");

		springAiCoreRuntimeHints.registerHints(runtimeHints, null);

		assertTrue(runtimeHints.reflection().isMethodRegistered(getDescription, ExecutableMode.INVOKE),
				"Method getDescription should be registered in reflection hints");
		assertTrue(runtimeHints.reflection().isMethodRegistered(getInputTypeSchema, ExecutableMode.INVOKE),
				"Method getInputTypeSchema should be registered in reflection hints");
		assertTrue(runtimeHints.reflection().isMethodRegistered(getName, ExecutableMode.INVOKE),
				"Method getName should be registered in reflection hints");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerResourcesForHints() {

		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints springAiCoreRuntimeHints = new SpringAiCoreRuntimeHints();
		Set<String> resourcesToBeRegistered = Set.of(
				"antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4",
				"embedding/embedding-model-dimensions.properties");

		springAiCoreRuntimeHints.registerHints(runtimeHints, null);

		for (var resourcePath : resourcesToBeRegistered) {
			assertTrue(runtimeHints.resources().isResourceRegistered(new ClassPathResource(resourcePath)),
					"Resource " + resourcePath + " should be registered in resource hints");
		}
	}

}