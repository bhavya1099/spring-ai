package org.springframework.ai.aot;

import org.junit.jupiter.api;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito.mock;
import org.mockito.Mockito.when;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.util.Collections;

public class AiRuntimeHintsTest {

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("valid")
	@Test
	public void validPackageWithJsonIncludedClasses() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.annotated", Mockito.any()))
				.thenReturn(Set.of(TypeReference.of("com.example.Class1"), TypeReference.of("com.example.Class2")));
			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.annotated");
			assertNotNull(result);
			assertEquals(2, result.size());
			assertTrue(result.contains(TypeReference.of("com.example.Class1")));
			assertTrue(result.contains(TypeReference.of("com.example.Class2")));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("invalid")
	@Test
	public void emptyResultForNonAnnotatedPackage() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.nonannotated", Mockito.any()))
				.thenReturn(Set.of());
			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.nonannotated");
			assertNotNull(result);
			assertTrue(result.isEmpty());
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("invalid")
	@Test
	public void handleMissingClassWithGracefulError() {
		try {
			AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.invalid");

			fail("Expected RuntimeException to be thrown.");
		}
		catch (RuntimeException ex) {
			assertTrue(ex.getMessage().contains("ClassNotFoundException"));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("valid")
	@Test
	public void nestedClassesWithJsonAnnotations() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.nested", Mockito.any()))
				.thenReturn(Set.of(TypeReference.of("com.example.Parent$Nested1"),
						TypeReference.of("com.example.Parent$Nested2")));
			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.nested");
			assertNotNull(result);
			assertEquals(2, result.size());
			assertTrue(result.contains(TypeReference.of("com.example.Parent$Nested1")));
			assertTrue(result.contains(TypeReference.of("com.example.Parent$Nested2")));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("valid")
	@Test
	public void detectOnlyJacksonRelatedAnnotations() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.mixed", Mockito.any()))
				.thenReturn(Set.of(TypeReference.of("com.example.JsonAnnotated")));
			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.mixed");
			assertNotNull(result);
			assertEquals(1, result.size());
			assertTrue(result.contains(TypeReference.of("com.example.JsonAnnotated")));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("integration")
	@Test
	public void verifyLoggingOfRegisteredClasses() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.logging", Mockito.any()))
				.thenReturn(Set.of(TypeReference.of("com.example.LoggedClass")));

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.logging");
			assertNotNull(result);
			assertEquals(1, result.size());
			assertTrue(result.contains(TypeReference.of("com.example.LoggedClass")));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("invalid")
	@Test
	public void handleEmptyPackageName() {
		Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("valid")
	@Test
	public void validateRecordComponentsHandling() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.records", Mockito.any()))
				.thenReturn(Set.of(TypeReference.of("com.example.RecordClass")));
			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.records");
			assertNotNull(result);
			assertEquals(1, result.size());
			assertTrue(result.contains(TypeReference.of("com.example.RecordClass")));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("valid")
	@Test
	public void handleInheritedAnnotations() {
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("com.example.baseclasses", Mockito.any()))
				.thenReturn(
						Set.of(TypeReference.of("com.example.BaseClass"), TypeReference.of("com.example.SubClass1")));
			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage("com.example.baseclasses");
			assertNotNull(result);
			assertEquals(2, result.size());
			assertTrue(result.contains(TypeReference.of("com.example.BaseClass")));
			assertTrue(result.contains(TypeReference.of("com.example.SubClass1")));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Tag("boundary")
	@Test
	public void verifyOverloadedBehaviorWithClassParameter() {

		Class<?> testClass = this.getClass();
		try (MockedStatic<AiRuntimeHints> mockedHints = Mockito.mockStatic(AiRuntimeHints.class)) {
			mockedHints.when(() -> AiRuntimeHints.findClassesInPackage("org.springframework.ai.aot", Mockito.any()))
				.thenReturn(Set.of(TypeReference.of(testClass.getName())));
			mockedHints.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(testClass))
				.thenReturn(Set.of(TypeReference.of(testClass.getName())));
			Set<TypeReference> resultByName = AiRuntimeHints
				.findJsonAnnotatedClassesInPackage("org.springframework.ai.aot");
			Set<TypeReference> resultByClass = AiRuntimeHints.findJsonAnnotatedClassesInPackage(testClass);
			assertNotNull(resultByName);
			assertNotNull(resultByClass);
			assertEquals(resultByName, resultByClass);
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Tag("valid")
	@Test
	public void validateValidClassToPackage() {

		Class<?> validClass = SIMULATED_VALID_CLASS;

		Set<TypeReference> resultSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validClass);

		assertNotNull(resultSet, "Result set should not be null");
		resultSet
			.forEach(typeReference -> assertNotNull(typeReference.getName(), "TypeReference name should not be null"));
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Tag("invalid")
    @Test public void validateInvalidClassToPackage() {

        Class<?> invalidClass = SIMULATED_INVALID_CLASS;

        Set<TypeReference> resultSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(invalidClass);

        assertNotNull(resultSet, "Result set should not be null");
        assertEquals(0, resultSet.size(), "Result set should be empty for invalid package org.springframework.ai.aot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Set;
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
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Tag("boundary")
	@Test
	public void validateBoundaryScenarioWithEmptyPackageName() {

		Class<?> emptyPackageClass = SIMULATED_INVALID_CLASS;

		Set<TypeReference> resultSet = AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageClass);

		assertNotNull(resultSet, "Result set should not be null");
		assertEquals(0, resultSet.size(), "Boundary scenario should result in an empty set");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("valid") public void findClassesMatchingFilterInPackage() {

        String testPackageName = "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);
        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        Mockito.when(mockProvider.findCandidateComponents(testPackageName)).thenReturn(Collections.singletonList(() -> "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(testPackageName, mockFilter);

        assertEquals(1, result.size());
        assertNotNull(result.iterator().next());
        assertEquals("org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("valid") public void findClassesInEmptyPackage() {

        String emptyPackageName = "org.empty.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);
        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        Mockito.when(mockProvider.findCandidateComponents(emptyPackageName)).thenReturn(Collections.emptyList());

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(emptyPackageName, mockFilter);

        assertTrue(result.isEmpty());
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void handleInvalidPackageName() {

        String invalidPackageName = "!invalid.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;

        TypeFilter mockFilter = mock(TypeFilter.class);

        assertThrows(RuntimeException.class, () -> AiRuntimeHints.findClassesInPackage(invalidPackageName, mockFilter));
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("integration") public void debugLoggingWhenEnabled() {

        String testPackageName = "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);
        Logger mockLogger = mock(Logger.class);
        when(mockLogger.isDebugEnabled()).thenReturn(true);
        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        Mockito.when(mockProvider.findCandidateComponents(testPackageName)).thenReturn(Collections.singletonList(() -> "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(testPackageName, mockFilter);

        verify(mockLogger, atLeastOnce()).debug(contains("registering [org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;

    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("boundary") public void returnedSetIsImmutable() {

        String testPackageName = "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);
        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        Mockito.when(mockProvider.findCandidateComponents(testPackageName)).thenReturn(Collections.singletonList(() -> "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(testPackageName, mockFilter);

        assertThrows(UnsupportedOperationException.class, () -> result.add(TypeReference.of("additional.class")));
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("invalid")
	public void handleNullPackageName() {

		TypeFilter mockFilter = mock(TypeFilter.class);

		assertThrows(NullPointerException.class, () -> AiRuntimeHints.findClassesInPackage(null, mockFilter));
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void handleNullTypeFilter() {

        String testPackageName = "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;



        assertThrows(NullPointerException.class, () -> AiRuntimeHints.findClassesInPackage(testPackageName, null));
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void validateMalformedClassNames() {

        String testPackageName = "org.test.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);
        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        Mockito.when(mockProvider.findCandidateComponents(testPackageName)).thenReturn(Collections.singletonList(() -> null));

        assertThrows(NullPointerException.class, () -> AiRuntimeHints.findClassesInPackage(testPackageName, mockFilter));
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("boundary") public void testPerformanceUnderHeavyLoad() {

        String testPackageName = "org.large.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);

        long startTime = System.nanoTime();
        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(testPackageName, mockFilter);
        long endTime = System.nanoTime();

        assertNotNull(result);
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("valid") public void handleNestedClassesCorrectly() {

        String testPackageName = "org.nested.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        TypeFilter mockFilter = mock(TypeFilter.class);
        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        Mockito.when(mockProvider.findCandidateComponents(testPackageName)).thenReturn(Collections.singletonList(() -> "org.nested.package org.springframework.ai.aot;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;


        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(testPackageName, mockFilter);

        assertEquals(1, result.size());
        assertTrue(result.iterator().next().getName().contains("$"));
    }

}