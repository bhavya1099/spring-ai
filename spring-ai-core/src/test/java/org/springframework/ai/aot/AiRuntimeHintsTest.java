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
import org.junit.jupiter.api.*;

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

        Class<?> mockPackageClass = Mockito.mock(Class.class);
        when(mockPackageClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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



        Set<TypeReference> expectedSet = Set.of();


        Set<TypeReference> actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass);

        assertEquals(expectedSet, actualSet, "Should find annotated classes correctly");
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("invalid") public void findAnnotatedClassesForPackageWithoutJacksonAnnotations() {

        Class<?> mockPackageClass = Mockito.mock(Class.class);
        when(mockPackageClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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


        Set<TypeReference> actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass);

        assertTrue(actualSet.isEmpty(), "Should return an empty set for package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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
    @Tag("valid") public void findAnnotatedClassesInNestedClasses() {

        Class<?> mockRootClass = Mockito.mock(Class.class);
        when(mockRootClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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


        Set<TypeReference> expectedSet = Set.of();

        Set<TypeReference> actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockRootClass);

        assertEquals(expectedSet, actualSet, "Should find annotated nested classes correctly");
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("invalid")
	public void handleClassNotFoundExceptionDuringScanning() {

		Class<?> mockPackageClass = Mockito.mock(Class.class);
		when(mockPackageClass.getPackageName()).thenThrow(ClassNotFoundException.class);

		assertThrows(RuntimeException.class, () -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass),
				"Should throw RuntimeException when ClassNotFoundException occurs");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("integration") public void verifyLogOutputDuringDebugMode() {


        Class<?> mockPackageClass = Mockito.mock(Class.class);
        when(mockPackageClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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


        var actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass);


    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("invalid")
	public void handleNullInputForFindJsonAnnotatedClassesInPackage() {

		assertThrows(NullPointerException.class, () -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(null),
				"Should throw NullPointerException for null input");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("valid") public void findAnnotatedClassesWithAbstractClassesAndInterfaces() {

        Class<?> mockPackageClass = Mockito.mock(Class.class);
        when(mockPackageClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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


        Set<TypeReference> expectedSet = Set.of();

        Set<TypeReference> actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass);

        assertEquals(expectedSet, actualSet, "Should include abstract classes and interfaces with annotations");
    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("integration") public void handleMultipleClassLoadersDuringScanning() {

        Class<?> mockPackageClass = Mockito.mock(Class.class);
        when(mockPackageClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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


        Set<TypeReference> expectedSet = Set.of();

        Set<TypeReference> actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass);

        assertEquals(expectedSet, actualSet, "Should correctly scan package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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
    @Tag("valid") public void findAnnotatedClassesUsingFieldMethodAndConstructorAnnotations() {

        Class<?> mockPackageClass = Mockito.mock(Class.class);
        when(mockPackageClass.getPackageName()).thenReturn("com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.*;
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


        Set<TypeReference> expectedSet = Set.of();

        Set<TypeReference> actualSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mockPackageClass);

        assertEquals(expectedSet, actualSet, "Should identify classes annotated at fields, methods, and constructors.");
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
                var clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
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
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        if (log.isDebugEnabled()) {
            log.debug("Validated successful registration for classes in package org.springframework.ai.aot;
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
import org.junit.jupiter.api.*;
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
import org.junit.jupiter.api.*;
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
import org.junit.jupiter.api.*;
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
            assertTrue(e.getMessage().contains("The package org.springframework.ai.aot;
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
import org.junit.jupiter.api.*;
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
                var clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                return clazz.isAnnotationPresent(com.fasterxml.jackson.annotation.JsonInclude.class) ||
                       clazz.isAnnotationPresent(com.fasterxml.jackson.annotation.JsonProperty.class);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(complexPackageName, typeFilter);

        assertTrue(result.size() > 0, "Expected non-empty result set with annotated classes");
        result.forEach(typeRef -> assertTrue(typeRef.getName().contains(complexPackageName), "Unexpected package org.springframework.ai.aot;
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
import org.junit.jupiter.api.*;
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