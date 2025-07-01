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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
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
    @Tag("valid") public void validateValidClassToPackage() {

        Class<?> validPackageClass = SampleTestClass.class;

        Set<TypeReference> actualResult = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageClass);

        assertEquals(1, (int) actualResult.size(),
            "Expected only one annotated class in the package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("invalid") public void validateClassNotInPackage() {

        Class<?> invalidPackageClass = InvalidClass.class;

        Set<TypeReference> actualResult = AiRuntimeHints.findJsonAnnotatedClassesInPackage(invalidPackageClass);

        assertEquals(0, (int) actualResult.size(),
            "Expected no annotated classes from package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("boundary")
	public void validateNullInput() {

		Class<?> nullClass = null;

		assertThrows(NullPointerException.class, () -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(nullClass),
				"Expected NullPointerException when input is null.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("boundary") public void validateInvalidPackageName() {

        Class<?> invalidPackageClass = InvalidPackageClass.class;

        assertThrows(RuntimeException.class,
            () -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(invalidPackageClass),
            "Expected RuntimeException for invalid package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
    @Tag("boundary") public void validateEmptyPackage() {

        Class<?> emptyPackageClass = EmptyPackageClass.class;

        Set<TypeReference> actualResult = AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageClass);

        assertEquals(0, (int) actualResult.size(),
            "Expected no annotated classes in an empty package org.springframework.ai.aot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.aot.hint.TypeReference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("valid")
	public void findClassesMatchingFilterInPackage() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);
		Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(VALID_PACKAGE, typeFilter);
		assertNotNull(result, "Result should not be null");
		assertFalse(result.isEmpty(), "Result should not be empty");
		result
			.forEach(typeReference -> assertNotNull(typeReference.getName(), "TypeReference name should not be null"));
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("valid")
	public void findClassesInEmptyPackage() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);
		Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(EMPTY_PACKAGE, typeFilter);
		assertNotNull(result, "Result should not be null");
		assertTrue(result.isEmpty(), "Result should be empty when no classes are found");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("invalid")
	public void handleInvalidPackageName() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			AiRuntimeHints.findClassesInPackage(INVALID_PACKAGE, typeFilter);
		});
		assertNotNull(exception.getMessage(), "Exception message should not be null");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("integration")
	public void debugLoggingWhenEnabled() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);

		Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(VALID_PACKAGE, typeFilter);
		assertNotNull(result, "Result should not be null");
		assertFalse(result.isEmpty(), "Result should not be empty");

	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("valid")
	public void returnedSetIsImmutable() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);
		Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(VALID_PACKAGE, typeFilter);
		assertNotNull(result, "Result should not be null");
		Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
			result.add(TypeReference.of("test.ClassName"));
		});
		assertNotNull(exception, "Exception should be thrown if modification attempt is made");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("invalid")
	public void handleNullPackageName() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			AiRuntimeHints.findClassesInPackage(null, typeFilter);
		});
		assertNotNull(exception.getMessage(), "Exception message should not be null");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
    @Tag("invalid") public void handleNullTypeFilter() {
        String package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
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

        Exception exception = assertThrows(NullPointerException.class, () -> {
            AiRuntimeHints.findClassesInPackage(package org.springframework.ai.aot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
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

        });
        assertNotNull(exception.getMessage(), "Exception message should not be null");
    }

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("invalid")
	public void validateMalformedClassNames() {
		TypeFilter typeFilter = (metadataReader, metadataReaderFactory) -> true;
		var classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);

		classPathScanningCandidateComponentProvider.addIncludeFilter(typeFilter);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			AiRuntimeHints.findClassesInPackage(VALID_PACKAGE, typeFilter);
		});
		assertNotNull(exception.getMessage(), "Exception should be logged for malformed class names");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("boundary")
	public void testPerformanceUnderHeavyLoad() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);

		long startTime = System.currentTimeMillis();
		Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(VALID_PACKAGE, typeFilter);
		long endTime = System.currentTimeMillis();
		assertNotNull(result, "Result should not be null");
		assertFalse(result.isEmpty(), "Result should not be empty under heavy load");
		assertTrue((endTime - startTime) < 5000, "Execution time should be under 5 seconds");
	}

	/*
	 * ROOST_METHOD_HASH=findClassesInPackage_1bff6d9786
	 * ROOST_METHOD_SIG_HASH=findClassesInPackage_caabc0c4e2
	 *
	 */@Test
	@Tag("valid")
	public void handleNestedClassesCorrectly() {
		TypeFilter typeFilter = new AnnotationTypeFilter(JsonInclude.class);
		Set<TypeReference> result = AiRuntimeHints.findClassesInPackage(VALID_PACKAGE, typeFilter);
		assertNotNull(result, "Result should not be null");
		assertFalse(result.isEmpty(), "Result should not be empty");
		result.forEach(typeReference -> assertNotNull(typeReference.getName(),
				"Nested TypeReference name should not be null"));
	}

}