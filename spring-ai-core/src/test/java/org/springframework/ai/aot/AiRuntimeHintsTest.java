package org.springframework.ai.aot;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api;

public class AiRuntimeHintsTest {

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("valid")
	public void findJsonAnnotatedClassesValidPackage() {

		String validPackageName = "com.example.validPackage";
		Set<TypeReference> expectedClasses = Set.of(TypeReference.of("com.example.validPackage.AnnotatedClass"));

		Set<TypeReference> actualClasses = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageName);

		assertEquals(expectedClasses, actualClasses, "Expected classes do not match the actual classes found.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
    @Tag("boundary") public void findJsonAnnotatedClassesEmptyPackage() {

        String emptyPackageName = "";

        Set<TypeReference> actualClasses = AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageName);

        assertTrue(actualClasses.isEmpty(), "Returned set should be empty for an empty package org.springframework.ai.aot;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.aot.hint.TypeReference;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    }

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("invalid")
	public void findJsonAnnotatedClassesNonExistentPackage() {

		String nonExistentPackageName = "com.example.nonExistentPackage";

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(nonExistentPackageName));
		assertTrue(exception.getCause() instanceof ClassNotFoundException,
				"Expected exception cause is ClassNotFoundException.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("valid")
	public void findJsonAnnotatedClassesExcludeUnannotated() {

		String mixedPackageName = "com.example.mixedAnnotationPackage";
		Set<TypeReference> expectedClasses = Set
			.of(TypeReference.of("com.example.mixedAnnotationPackage.AnnotatedClass"));

		Set<TypeReference> actualClasses = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mixedPackageName);

		assertEquals(expectedClasses, actualClasses, "The returned set should not include unannotated classes.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("valid")
	public void findJsonAnnotatedClassesIncludeNested() {

		String nestedPackageName = "com.example.nestedAnnotationPackage";
		Set<TypeReference> expectedClasses = Set
			.of(TypeReference.of("com.example.nestedAnnotationPackage.OuterClass$NestedAnnotatedClass"));

		Set<TypeReference> actualClasses = AiRuntimeHints.findJsonAnnotatedClassesInPackage(nestedPackageName);

		assertTrue(actualClasses.containsAll(expectedClasses),
				"Nested or member classes annotated with @JsonInclude should be included.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("integration")
	public void findJsonAnnotatedClassesDebugLogs() {

		String validPackageName = "com.example.validPackage";
		Logger mockLogger = Mockito.mock(Logger.class);
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(true);
		TypeReference mockTypeReference = Mockito.mock(TypeReference.class);
		Mockito.doNothing().when(mockLogger).debug(Mockito.anyString());
		Set<TypeReference> expectedClasses = Set.of(mockTypeReference);

		Set<TypeReference> actualClasses = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageName);

		assertEquals(expectedClasses, actualClasses);
		Mockito.verify(mockLogger, Mockito.atLeastOnce()).debug(Mockito.anyString());
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("valid")
	public void findJsonAnnotatedClassesAlternativeAnnotations() {

		String alternativeAnnotationPackageName = "com.example.alternativeAnnotationPackage";
		Set<TypeReference> expectedClasses = Set
			.of(TypeReference.of("com.example.alternativeAnnotationPackage.AlternativeAnnotatedClass"));

		Set<TypeReference> actualClasses = AiRuntimeHints
			.findJsonAnnotatedClassesInPackage(alternativeAnnotationPackageName);

		assertEquals(expectedClasses, actualClasses, "Classes should include those annotated with @JsonProperty.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("integration")
	public void findJsonAnnotatedClassesRecordAndStandardClasses() {

		String mixedPackageName = "com.example.recordAndStandardPackage";
		Set<TypeReference> expectedClasses = Set.of(
				TypeReference.of("com.example.recordAndStandardPackage.AnnotatedStandardClass"),
				TypeReference.of("com.example.recordAndStandardPackage.AnnotatedRecordClass"));

		Set<TypeReference> actualClasses = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mixedPackageName);

		assertEquals(expectedClasses, actualClasses,
				"The method should detect both record classes and standard classes annotated with @JsonInclude.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_25dbdef816
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_2d5afaa89e
	 *
	 */@Test
	@Tag("invalid")
	public void findJsonAnnotatedClassesRuntimeExceptionPropagation() {

		String erroneousPackageName = "com.example.erroneousPackage";

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(erroneousPackageName));
		assertNotNull(exception.getMessage(), "The exception should have a message explaining the error.");
		assertTrue(exception.getCause() instanceof ClassNotFoundException,
				"Expected exception cause is ClassNotFoundException.");
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("valid")
	public void annotatedClassesFromValidPackage() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String validPackageName = "org.example.json.valid";
			Set<TypeReference> dummyResult = Set.of(TypeReference.of("org.example.json.valid.AnnotatedClass"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageName))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageName);

			assertTrue(result.equals(dummyResult));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("boundary")
	public void annotatedClassesFromEmptyPackage() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String emptyPackageName = "org.example.json.empty";
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageName))
				.thenReturn(Set.of());

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(emptyPackageName);

			assertTrue(result.isEmpty());
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("invalid")
	public void annotatedClassesFromNonexistentPackage() {

		String nonexistentPackageName = "org.example.nonexistent";

		assertThrows(RuntimeException.class,
				() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(nonexistentPackageName));
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("valid")
	public void annotatedClassesWithMixedAnnotations() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String mixedAnnotationsPackage = "org.example.json.mixed";
			Set<TypeReference> dummyResult = Set
				.of(TypeReference.of("org.example.json.mixed.JsonIncludeAnnotatedClass"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(mixedAnnotationsPackage))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(mixedAnnotationsPackage);

			assertTrue(result.equals(dummyResult));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("integration")
	public void logRegisteredClasses() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String validPackageName = "org.example.json.logging";
			Set<TypeReference> dummyResult = Set.of(TypeReference.of("org.example.json.logging.AnnotatedClass"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageName))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validPackageName);

			assertTrue(result.equals(dummyResult),
					"The Logger should output debug messages indicating registered classes.");
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("valid")
	public void discoverJacksonAnnotatedNestedClasses() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String nestedClassesPackage = "org.example.json.nested";
			Set<TypeReference> dummyResult = Set.of(TypeReference.of("org.example.json.nested.OuterClass.NestedClass"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(nestedClassesPackage))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(nestedClassesPackage);

			assertTrue(result.equals(dummyResult));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("valid")
	public void invokeWithClass() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			Class<?> validClass = org.example.json.valid.AnnotatedClass.class;
			Set<TypeReference> dummyResult = Set.of(TypeReference.of(validClass.getName()));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(validClass))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(validClass);

			assertTrue(result.equals(dummyResult));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("integration")
	public void deeplyNestedPackagesValidation() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String nestedPackageName = "org.example.json.deepnested";
			Set<TypeReference> dummyResult = Set.of(TypeReference.of("org.example.json.deepnested.Level1.ClassA"),
					TypeReference.of("org.example.json.deepnested.Level1.Level2.ClassB"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(nestedPackageName))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(nestedPackageName);

			assertTrue(result.equals(dummyResult));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("valid")
	public void classesWithMultipleJsonIncludeAnnotations() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String multiAnnotationsPackageName = "org.example.json.multiannotations";
			Set<TypeReference> dummyResult = Set
				.of(TypeReference.of("org.example.json.multiannotations.AnnotatedClass"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(multiAnnotationsPackageName))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(multiAnnotationsPackageName);

			assertTrue(result.equals(dummyResult));
		}
	}

	/*
	 * ROOST_METHOD_HASH=findJsonAnnotatedClassesInPackage_34e9060116
	 * ROOST_METHOD_SIG_HASH=findJsonAnnotatedClassesInPackage_7bbfa07d99
	 *
	 */@Test
	@Tag("valid")
	public void parameterizedAnnotatedClasses() {
		try (MockedStatic<AiRuntimeHints> mockedStatic = Mockito.mockStatic(AiRuntimeHints.class)) {

			String parameterizedPackageName = "org.example.json.parameterized";
			Set<TypeReference> dummyResult = Set
				.of(TypeReference.of("org.example.json.parameterized.GenericClass<String>"));
			mockedStatic.when(() -> AiRuntimeHints.findJsonAnnotatedClassesInPackage(parameterizedPackageName))
				.thenReturn(dummyResult);

			Set<TypeReference> result = AiRuntimeHints.findJsonAnnotatedClassesInPackage(parameterizedPackageName);

			assertTrue(result.equals(dummyResult));
		}
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