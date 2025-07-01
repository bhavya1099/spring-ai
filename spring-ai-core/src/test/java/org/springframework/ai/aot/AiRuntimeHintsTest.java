package org.springframework.ai.aot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.mockito.Mockito;

public class AiRuntimeHintsTest {

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
    @Tag("valid") public void validateJsonIncludeAnnotationDetection() {

        String testPackageName = "com.test.package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(testPackageName);

        assertTrue(result.size() > 0, "Expected number of annotated classes not found");
        result.forEach(typeReference -> assertTrue(Objects.requireNonNull(typeReference).getName().contains(testPackageName),
                "TypeReference does not belong to the expected package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
    @Tag("invalid") public void validateClassNotFoundExceptionHandling() {

        String invalidPackageName = "com.nonexistent.package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        assertThrows(RuntimeException.class, () -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(invalidPackageName),
                "Expected RuntimeException for invalid package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
    @Tag("boundary") public void validateEmptyPackageHandling() {

        String emptyPackageName = "";

        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageName);

        assertTrue(result.isEmpty(), "Expected an empty result for an empty package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
    @Tag("boundary") public void validateNullPackageHandling() {

        String nullPackageName = null;

        assertThrows(NullPointerException.class, () -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(nullPackageName),
                "Expected NullPointerException for a null package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
    @Tag("integration") public void validateAnnotatedClassDetectionInNestedPackages() {

        String nestedPackageName = "com.test.nested.package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(nestedPackageName);

        assertTrue(result.size() > 0, "Expected number of annotated classes in nested package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

        result.forEach(typeReference -> assertTrue(Objects.requireNonNull(typeReference).getName().contains(nestedPackageName),
                "TypeReference does not belong to the expected nested package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("valid") public void findAnnotatedClassesForPackageWithJacksonAnnotations() {

        Class<?> mockPackage = Mockito.mock(Class.class);
        Mockito.when(mockPackage.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(tr -> tr instanceof TypeReference && tr.getName().contains("JsonAnnotatedClass")));
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("invalid") public void findAnnotatedClassesForPackageWithoutJacksonAnnotations() {

        Class<?> mockPackage = Mockito.mock(Class.class);
        Mockito.when(mockPackage.getPackageName()).thenReturn("com.empty.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("valid") public void findAnnotatedClassesInNestedClasses() {

        Class<?> mockRootPackage = Mockito.mock(Class.class);
        Mockito.when(mockRootPackage.getPackageName()).thenReturn("com.nested.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockRootPackage);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(tr -> tr instanceof TypeReference && tr.getName().contains("NestedAnnotatedClass")));
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("invalid")
	public void handleClassNotFoundExceptionDuringScanning() {

		Class<?> mockPackage = Mockito.mock(Class.class);
		Mockito.when(mockPackage.getPackageName()).thenThrow(new RuntimeException(new ClassNotFoundException()));

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage));
		assertTrue(exception.getCause() instanceof ClassNotFoundException);
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("integration") public void verifyLogOutputDuringDebugMode() {

        Logger mockLogger = Mockito.mock(Logger.class);
        Mockito.when(mockLogger.isDebugEnabled()).thenReturn(true);
        Class<?> mockPackage = Mockito.mock(Class.class);
        Mockito.when(mockPackage.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage);

        assertNotNull(result);
        Mockito.verify(mockLogger, Mockito.atLeastOnce()).debug(Mockito.contains("registering"));
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("invalid")
	public void handleNullInputForFindJsonAnnotatedClassesInPackage() {

		NullPointerException exception = assertThrows(NullPointerException.class,
				() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage((Class<?>) null));
		assertNotNull(exception);
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("valid") public void findAnnotatedClassesWithAbstractClassesAndInterfaces() {

        Class<?> mockPackage = Mockito.mock(Class.class);
        Mockito.when(mockPackage.getPackageName()).thenReturn("com.abstract.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(tr -> tr instanceof TypeReference && tr.getName().contains("AnnotatedAbstractOrInterface")));
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("integration") public void handleMultipleClassLoadersDuringScanning() {

        Class<?> mockPackage = Mockito.mock(Class.class);
        Mockito.when(mockPackage.getPackageName()).thenReturn("com.multiloader.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("boundary") public void findAnnotatedClassesUsingFieldMethodAndConstructorAnnotations() {

        Class<?> mockPackage = Mockito.mock(Class.class);
        Mockito.when(mockPackage.getPackageName()).thenReturn("com.field.method.constructor.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.aot.hint.TypeReference;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackage);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(tr -> tr instanceof TypeReference && tr.getName().contains("AnnotatedClass")));
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("valid") public void findClassesInPackageWithValidInputs() {

        String validPackageName = "com.example.valid";
        TypeFilter typeFilter = (metadataReader, metadataReaderFactory) -> {
            try {
                Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                return clazz.isAnnotationPresent(javax.annotation.Generated.class);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
        log.debug("DEBUG logging enabled for test");

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(validPackageName, typeFilter);

        assertTrue(result.size() > 0, "Expected non-empty result set");
        result.forEach(typeRef -> assertTrue(typeRef.getName().startsWith(validPackageName), "Unexpected package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        if (log.isDebugEnabled()) {
            log.debug("Validated successful registration for classes");
        }
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void findClassesInPackageWithNonExistentPackage() {

        String invalidPackageName = "invalid.package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.type.filter.AnnotationTypeFilter;

        TypeFilter typeFilter = (metadataReader, metadataReaderFactory) -> false;

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(invalidPackageName, typeFilter);

        assertEquals(0, result.size(), "Expected empty result for an invalid package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.type.filter.AnnotationTypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("boundary") public void findClassesInPackageWithEmptyPackageName() {

        String emptyPackageName = "";
        TypeFilter typeFilter = (metadataReader, metadataReaderFactory) -> false;

        try {
            AiRuntimeHints.findClassesInPackage(emptyPackageName, typeFilter);
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.type.filter.AnnotationTypeFilter;

        }
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("integration") public void findClassesInPackageWithComplexAnnotations() {

        String complexPackageName = "com.example.annotations";
        TypeFilter typeFilter = (metadataReader, metadataReaderFactory) -> {
            try {
                Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                return clazz.isAnnotationPresent(JsonInclude.class) ||
                       clazz.isAnnotationPresent(JsonProperty.class);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(complexPackageName, typeFilter);

        assertTrue(result.size() > 0, "Expected non-empty result set with annotated classes");
        result.forEach(typeRef -> assertTrue(typeRef.getName().startsWith(complexPackageName), "Unexpected package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.type.filter.AnnotationTypeFilter;

    }

}