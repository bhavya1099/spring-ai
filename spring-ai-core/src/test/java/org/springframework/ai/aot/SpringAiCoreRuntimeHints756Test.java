package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

public class SpringAiCoreRuntimeHints756Test {

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerReflectionTypesCorrectly() {

		RuntimeHints runtimeHints = new RuntimeHints();

		springAiCoreRuntimeHints.registerHints(runtimeHints, null);

		Set<Class<?>> chatTypes = Set.of(AbstractMessage.class, AssistantMessage.class, ToolResponseMessage.class,
				Message.class, MessageType.class, UserMessage.class, SystemMessage.class,
				DefaultFunctionCallbackResolver.class, FunctionCallback.class);
		chatTypes.forEach(type -> assertTrue(runtimeHints.reflection().isTypeRegistered(type),
				"Reflection type should be registered: " + type.getName()));
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerMethodsCorrectly() {

		RuntimeHints runtimeHints = new RuntimeHints();

		springAiCoreRuntimeHints.registerHints(runtimeHints, null);

		Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
		Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
		Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
		assertTrue(runtimeHints.reflection().isMethodRegistered(getDescription, ExecutableMode.INVOKE),
				"Method should be registered for reflection: getDescription");
		assertTrue(runtimeHints.reflection().isMethodRegistered(getInputTypeSchema, ExecutableMode.INVOKE),
				"Method should be registered for reflection: getInputTypeSchema");
		assertTrue(runtimeHints.reflection().isMethodRegistered(getName, ExecutableMode.INVOKE),
				"Method should be registered for reflection: getName");
	}

	/*
	 * ROOST_METHOD_HASH=registerHints_a9eb20a924
	 * ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045
	 *
	 */@Test
	@Tag("valid")
	public void registerResourcesCorrectly() {

		RuntimeHints runtimeHints = new RuntimeHints();

		springAiCoreRuntimeHints.registerHints(runtimeHints, null);

		Set<String> resources = Set.of("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4",
				"embedding/embedding-model-dimensions.properties");
		resources.forEach(
				resource -> assertTrue(runtimeHints.resources().isResourceRegistered(new ClassPathResource(resource)),
						"Resource should be registered: " + resource));
	}

}