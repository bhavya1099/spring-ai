package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.ResourceHints;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.Set;
import org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class SpringAiCoreRuntimeHints162Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void verifyChatTypesRegistration() {

		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		Set<Class<?>> expectedChatTypes = Set.of(AbstractMessage.class, AssistantMessage.class,
				ToolResponseMessage.class, Message.class, MessageType.class, UserMessage.class, SystemMessage.class,
				DefaultFunctionCallbackResolver.class, FunctionCallback.class);

		hintsRegistrar.registerHints(runtimeHints, null);

		ReflectionHints reflectionHints = runtimeHints.reflection();
		for (Class<?> chatType : expectedChatTypes) {
			assertTrue(reflectionHints.isTypeRegistered(chatType), "Chat type not registered: " + chatType.getName());
		}
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void verifyMethodsRegistration() {

		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		Method expectedGetDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method expectedGetInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method expectedGetName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");

		hintsRegistrar.registerHints(runtimeHints, null);

		ReflectionHints reflectionHints = runtimeHints.reflection();
		assertTrue(reflectionHints.isMethodRegistered(expectedGetDescription),
				"Method 'getDescription' not registered");
		assertTrue(reflectionHints.isMethodRegistered(expectedGetInputTypeSchema),
				"Method 'getInputTypeSchema' not registered");
		assertTrue(reflectionHints.isMethodRegistered(expectedGetName), "Method 'getName' not registered");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void verifyResourcesRegistration() {

		RuntimeHints runtimeHints = new RuntimeHints();
		SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
		Set<String> expectedResources = Set.of("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4",
				"embedding/embedding-model-dimensions.properties");

		hintsRegistrar.registerHints(runtimeHints, null);

		ResourceHints resourceHints = runtimeHints.resources();
		for (String resource : expectedResources) {
			assertTrue(resourceHints.isResourceRegistered(resource), "Resource not registered: " + resource);
		}
	}

}