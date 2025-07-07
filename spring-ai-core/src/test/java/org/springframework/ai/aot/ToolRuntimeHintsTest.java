
// ********RoostGPT********
/*

roost_feedback [07/07/2025, 6:17:17 PM]:remove runtime errors if any\n
*/

// ********RoostGPT********

package org.springframework.ai.aot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.MemberCategory;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito.verify;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class ToolRuntimeHintsTest {

    @Tag("valid")
    @Test
    public void successfullyRegistersDefaultToolCallResultConverter() {
        RuntimeHints runtimeHintsMock = Mockito.mock(RuntimeHints.class);
        RuntimeHints.ReflectionHints reflectionHintsMock = Mockito.mock(RuntimeHints.ReflectionHints.class);
        Mockito.when(runtimeHintsMock.reflection()).thenReturn(reflectionHintsMock);
        ToolRuntimeHints toolRuntimeHints = new ToolRuntimeHints();
        MemberCategory[] memberCategories = MemberCategory.values();

        toolRuntimeHints.registerHints(runtimeHintsMock, null);

        verify(reflectionHintsMock).registerType(ArgumentMatchers.eq(DefaultToolCallResultConverter.class), ArgumentMatchers.eq(memberCategories));
    }
}
