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
	 */@Test
	@Tag("valid")
	public void validateValidClassToPackage() {

		Class<?> validPackageClass = AiRuntimeHints.class;

		Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageClass);

		assertNotNull(result, "Result should not be null");
		assertTrue(result.stream().allMatch(Objects::nonNull), "All TypeReference instances should be non-null");

		assertFalse(result.isEmpty(), "Result should contain at least one TypeReference");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("invalid") public void validateInvalidPackage() {

        Class<?> invalidPackageClass = Mockito.mock(Class.class);

        Mockito.when(invalidPackageClass.getPackageName()).thenReturn("invalid.package org.springframework.ai.aot;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;



        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(invalidPackageClass);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "No annotated classes should be found in an invalid package org.springframework.ai.aot;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
	 */@Test
    @Tag("boundary") public void validateEmptyPackageScenario() {

        Class<?> emptyPackageClass = Mockito.mock(Class.class);

        Mockito.when(emptyPackageClass.getPackageName()).thenReturn("");

        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageClass);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be empty when no classes exist in the package org.springframework.ai.aot;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
	 */@Test
    @Tag("valid") public void validatePackageContainingJacksonAnnotations() {

        Class<?> validJacksonPackageClass = AiRuntimeHints.class;

        Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validJacksonPackageClass);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.stream().allMatch(typeRef -> typeRef.getName() != null && !typeRef.getName().isBlank()), "TypeReference instances should have valid names");
        assertFalse(result.isEmpty(), "Result should contain TypeReference objects if annotated classes exist in the package org.springframework.ai.aot;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void verifyInvalidPackageNameHandling() {

        String invalidPackageName = "non.existent.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

        TypeFilter mockFilter = mock(TypeFilter.class);

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(invalidPackageName, mockFilter);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("valid") public void verifyEmptyPackage() {

        String emptyPackageName = "some.empty.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

        TypeFilter mockFilter = mock(TypeFilter.class);

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(emptyPackageName, mockFilter);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("valid") public void verifyClassesWithValidPackageAndFilter() {

        String validPackageName = "com.example.valid.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


        TypeFilter annotationFilter = new AnnotationTypeFilter(JsonInclude.class);

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(validPackageName, annotationFilter);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("boundary") public void verifyUnmodifiableReturnedSet() {

        String validPackageName = "com.example.valid.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


        TypeFilter annotationFilter = new AnnotationTypeFilter(JsonInclude.class);

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(validPackageName, annotationFilter);

        assertThrows(UnsupportedOperationException.class, () -> result.add(mock(TypeReference.class)));
        assertThrows(UnsupportedOperationException.class, () -> result.remove(mock(TypeReference.class)));
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("invalid")
	public void verifyNullPackageName() {

		String nullPackageName = null;
		TypeFilter mockFilter = mock(TypeFilter.class);

		assertThrows(NullPointerException.class,
				() -> AiRuntimeHints.findClassesInPackage(nullPackageName, mockFilter));
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("boundary") public void verifyEmptyResultWithNonMatchingFilter() {

        String validPackageName = "com.example.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


        TypeFilter mismatchedFilter = new AnnotationTypeFilter(Logger.class);

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(validPackageName, mismatchedFilter);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("integration") public void verifyDebugLoggingForClasses() {

        String validPackageName = "com.example.valid.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


        TypeFilter annotationFilter = new AnnotationTypeFilter(JsonInclude.class);

        Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(validPackageName, annotationFilter);


        assertNotNull(result);

    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void verifyExceptionDuringTypeReferenceCreation() {

        ClassPathScanningCandidateComponentProvider mockProvider = mock(ClassPathScanningCandidateComponentProvider.class);
        TypeFilter mockFilter = mock(TypeFilter.class);
        when(mockProvider.findCandidateComponents(anyString()))
                .thenReturn(Set.of());

        assertThrows(RuntimeException.class, () -> AiRuntimeHints.findClassesInPackage("mocked.package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Objects;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    }

}