/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ai.writer;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.ai.document.DocumentWriter;
import org.springframework.util.Assert;

class FileDocumentWriterAcceptTest {

	@Test
	@Tag("valid")
	public void writeDocumentsToFileWithDocumentMarkers() {
		// Arrange
		String fileName = "testOutput.txt"; // TODO: Change file name as required
		MetadataMode metadataMode = MetadataMode.NONE;
		boolean withDocumentMarkers = true;
		boolean append = false;
		Document mockDoc1 = mock(Document.class);
		Document mockDoc2 = mock(Document.class);
		when(mockDoc1.getMetadata())
			.thenReturn(Map.of("METADATA_START_PAGE_NUMBER", "1", "METADATA_END_PAGE_NUMBER", "10"));
		when(mockDoc2.getMetadata())
			.thenReturn(Map.of("METADATA_START_PAGE_NUMBER", "11", "METADATA_END_PAGE_NUMBER", "20"));
		when(mockDoc1.getFormattedContent(metadataMode)).thenReturn("Content of Doc 1\n");
		when(mockDoc2.getFormattedContent(metadataMode)).thenReturn("Content of Doc 2\n");
		List<Document> mockDocs = List.of(mockDoc1, mockDoc2);
		FileDocumentWriter fileDocumentWriter = new FileDocumentWriter(fileName, withDocumentMarkers, metadataMode,
				append);
		// Act
		try (MockedStatic<FileWriter> mockedStatic = mockStatic(FileWriter.class)) {
			mockedStatic.when(() -> new FileWriter(fileName, append)).thenReturn(mock(FileWriter.class)); // TODO:
																											// Adapt
																											// logic
																											// as
																											// per
																											// environment
			fileDocumentWriter.accept(mockDocs);
			// Assert
			mockedStatic.verify(() -> new FileWriter(fileName, append), times(1));
			mockedStatic.verifyNoMoreInteractions();
		}
		catch (Exception e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	@Test
	@Tag("invalid")
	public void handleEmptyDocumentListProperly() {
		// Arrange
		String fileName = "emptyDocsOutput.txt"; // TODO: Change file name as required
		MetadataMode metadataMode = MetadataMode.NONE;
		boolean withDocumentMarkers = false;
		boolean append = false;
		List<Document> emptyDocs = List.of();
		FileDocumentWriter fileDocumentWriter = new FileDocumentWriter(fileName, withDocumentMarkers, metadataMode,
				append);
		// Act / Assert
		try (MockedStatic<FileWriter> mockedStatic = mockStatic(FileWriter.class)) {
			mockedStatic.when(() -> new FileWriter(fileName, append)).thenReturn(mock(FileWriter.class));
			fileDocumentWriter.accept(emptyDocs);
			// Assert
			mockedStatic.verify(() -> new FileWriter(fileName, append), times(1));
			mockedStatic.verifyNoMoreInteractions();
		}
		catch (Exception e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	@Test
	@Tag("invalid")
	public void handleExceptionDuringFileWriting() {
		// Arrange
		String fileName = "invalidTestFile.txt"; // TODO: Change file name path if
													// necessary
		MetadataMode metadataMode = MetadataMode.NONE;
		boolean withDocumentMarkers = true;
		boolean append = false;
		Document mockDoc = mock(Document.class);
		when(mockDoc.getMetadata()).thenThrow(new RuntimeException("Simulated Metadata Error"));
		List<Document> errorDocs = List.of(mockDoc);
		FileDocumentWriter fileDocumentWriter = new FileDocumentWriter(fileName, withDocumentMarkers, metadataMode,
				append);
		// Act / Assert
		assertThrows(RuntimeException.class, () -> fileDocumentWriter.accept(errorDocs),
				"Expected RuntimeException due to simulated error in fetching metadata.");
	}

	@Test
	@Tag("boundary")
	public void writeDocumentsBoundaryCase() {
		// Arrange
		String fileName = "boundaryOutput.txt"; // TODO: Change file name as required
		MetadataMode metadataMode = MetadataMode.NONE;
		boolean withDocumentMarkers = true;
		boolean append = true;
		Document mockDoc = mock(Document.class);
		when(mockDoc.getMetadata())
			.thenReturn(Map.of("METADATA_START_PAGE_NUMBER", "1", "METADATA_END_PAGE_NUMBER", "1"));
		when(mockDoc.getFormattedContent(metadataMode)).thenReturn("Boundary content\n");
		List<Document> boundaryDocs = List.of(mockDoc);
		FileDocumentWriter fileDocumentWriter = new FileDocumentWriter(fileName, withDocumentMarkers, metadataMode,
				append);
		// Act
		try (MockedStatic<FileWriter> mockedStatic = mockStatic(FileWriter.class)) {
			mockedStatic.when(() -> new FileWriter(fileName, append)).thenReturn(mock(FileWriter.class));
			fileDocumentWriter.accept(boundaryDocs);
			// Assert
			mockedStatic.verify(() -> new FileWriter(fileName, append), times(1));
			mockedStatic.verifyNoMoreInteractions();
		}
		catch (Exception e) {
			fail("Boundary case failed with unexpected exception.");
		}
	}

}