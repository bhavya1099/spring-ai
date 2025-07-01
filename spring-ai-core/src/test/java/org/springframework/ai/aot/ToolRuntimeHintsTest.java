package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.MemberCategory;
import org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito.verify;
import org.junit.jupiter.api;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable; 
 public class ToolRuntimeHintsTest {

/*ROOST_METHOD_HASH=registerHints_a64fc3b94a
ROOST_METHOD_SIG_HASH=registerHints_4cbaa06045

*/@Tag("valid")
@Test
public void successfullyRegistersDefaultToolCallResultConverter() {
    
    RuntimeHints runtimeHintsMock = Mockito.mock(RuntimeHints.class);
    RuntimeHints.ReflectionHints reflectionHintsMock = Mockito.mock(RuntimeHints.ReflectionHints.class);
    Mockito.when(runtimeHintsMock.reflection()).thenReturn(reflectionHintsMock);
    ToolRuntimeHints toolRuntimeHints = new ToolRuntimeHints();
    MemberCategory[] memberCategories = MemberCategory.values();
    
    toolRuntimeHints.registerHints(runtimeHintsMock, null);
    
    verify(reflectionHintsMock).registerType(eq(DefaultToolCallResultConverter.class), eq(memberCategories));
}
} 