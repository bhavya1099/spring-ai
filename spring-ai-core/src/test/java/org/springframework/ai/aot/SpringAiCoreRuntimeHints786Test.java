package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.lang.Nullable;
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
 public class SpringAiCoreRuntimeHints786Test {

/*ROOST_METHOD_HASH=registerHints_a9eb20a924
ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045

*/@Tag("valid")
@Test
public void registerHintsForChatTypes() {
    
    RuntimeHints runtimeHints = new RuntimeHints();
    SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
    Set<Class<?>> chatTypes = Set.of(AbstractMessage.class, AssistantMessage.class, ToolResponseMessage.class, Message.class, MessageType.class, UserMessage.class, SystemMessage.class, DefaultFunctionCallbackResolver.class, FunctionCallback.class);
    
    hintsRegistrar.registerHints(runtimeHints, null);
    
    for (Class<?> chatType : chatTypes) {
        assertTrue(runtimeHints.reflection().isTypeHinted(chatType), "Type " + chatType.getName() + " should be registered for reflection.");
    }
}

/*ROOST_METHOD_HASH=registerHints_a9eb20a924
ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045

*/@Tag("valid")
@Test
public void registerHintsForMethods() {
    
    RuntimeHints runtimeHints = new RuntimeHints();
    SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
    Method getDescription = ReflectionUtils.findMethod(FunctionCallback.class, "getDescription");
    Method getInputTypeSchema = ReflectionUtils.findMethod(FunctionCallback.class, "getInputTypeSchema");
    Method getName = ReflectionUtils.findMethod(FunctionCallback.class, "getName");
    
    hintsRegistrar.registerHints(runtimeHints, null);
    
    assertTrue(runtimeHints.reflection().isMethodHinted(getDescription, ExecutableMode.INVOKE), "Method getDescription should be registered for invocation.");
    assertTrue(runtimeHints.reflection().isMethodHinted(getInputTypeSchema, ExecutableMode.INVOKE), "Method getInputTypeSchema should be registered for invocation.");
    assertTrue(runtimeHints.reflection().isMethodHinted(getName, ExecutableMode.INVOKE), "Method getName should be registered for invocation.");
}

/*ROOST_METHOD_HASH=registerHints_a9eb20a924
ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045

*/@Tag("valid")
@Test
public void registerHintsForResources() {
    
    RuntimeHints runtimeHints = new RuntimeHints();
    SpringAiCoreRuntimeHints hintsRegistrar = new SpringAiCoreRuntimeHints();
    Set<String> resourcePaths = Set.of("antlr4/org/springframework/ai/vectorstore/filter/antlr4/Filters.g4", "embedding/embedding-model-dimensions.properties");
    
    hintsRegistrar.registerHints(runtimeHints, null);
    
    for (String resourcePath : resourcePaths) {
        assertTrue(runtimeHints.resources().isResourceHinted(resourcePath), "Resource path " + resourcePath + " should be registered.");
    }
}
} 