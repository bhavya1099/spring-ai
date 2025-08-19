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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api;
import org.springframework.ai.document.DocumentWriter;
import org.springframework.util.Assert;

public class FileDocumentWriterTest {

	/*
	 * ROOST_METHOD_HASH=accept_4df830b1a0 ROOST_METHOD_SIG_HASH=accept_91bdadd7c5
	 *
	 */@Test
	@Tag("valid")
	public void writeDocumentsToFileWithDocumentMarkers() {
		String fileName = "testOutput.txt";
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
		try (MockedStatic<FileWriter> mockedStatic = mockStatic(FileWriter.class)) {
			mockedStatic.when(() -> new FileWriter(fileName, append)).thenReturn(mock(FileWriter.class));
			fileDocumentWriter.accept(mockDocs);
			mockedStatic.verify(() -> new FileWriter(fileName, append), times(1));
			mockedStatic.verifyNoMoreInteractions();
		}
		catch (Exception e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	/*
	 * ROOST_METHOD_HASH=accept_4df830b1a0 ROOST_METHOD_SIG_HASH=accept_91bdadd7c5
	 *
	 */@Test
	@Tag("invalid")
	public void handleEmptyDocumentListProperly() {
		String fileName = "emptyDocsOutput.txt";
		MetadataMode metadataMode = MetadataMode.NONE;
		boolean withDocumentMarkers = false;
		boolean append = false;
		List<Document> emptyDocs = List.of();
		FileDocumentWriter fileDocumentWriter = new FileDocumentWriter(fileName, withDocumentMarkers, metadataMode,
				append);
		try (MockedStatic<FileWriter> mockedStatic = mockStatic(FileWriter.class)) {
			mockedStatic.when(() -> new FileWriter(fileName, append)).thenReturn(mock(FileWriter.class));
			fileDocumentWriter.accept(emptyDocs);
			mockedStatic.verify(() -> new FileWriter(fileName, append), times(1));
			mockedStatic.verifyNoMoreInteractions();
		}
		catch (Exception e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	/*
	 * ROOST_METHOD_HASH=accept_4df830b1a0 ROOST_METHOD_SIG_HASH=accept_91bdadd7c5
	 *
	 */@Test
	@Tag("invalid")
	public void handleExceptionDuringFileWriting() {
		String fileName = "invalidTestFile.txt";
		MetadataMode metadataMode = MetadataMode.NONE;
		boolean withDocumentMarkers = true;
		boolean append = false;
		Document mockDoc = mock(Document.class);
		when(mockDoc.getMetadata()).thenThrow(new RuntimeException("Simulated Metadata Error"));
		List<Document> errorDocs = List.of(mockDoc);
		FileDocumentWriter fileDocumentWriter = new FileDocumentWriter(fileName, withDocumentMarkers, metadataMode,
				append);
		assertThrows(RuntimeException.class, () -> fileDocumentWriter.accept(errorDocs),
				"Expected RuntimeException due to simulated error in fetching metadata.");
	}

	/*
	 * ROOST_METHOD_HASH=accept_4df830b1a0 ROOST_METHOD_SIG_HASH=accept_91bdadd7c5
	 *
	 */@Test
	@Tag("boundary")
	public void writeDocumentsBoundaryCase() {
		String fileName = "boundaryOutput.txt";
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
		try (MockedStatic<FileWriter> mockedStatic = mockStatic(FileWriter.class)) {
			mockedStatic.when(() -> new FileWriter(fileName, append)).thenReturn(mock(FileWriter.class));
			fileDocumentWriter.accept(boundaryDocs);
			mockedStatic.verify(() -> new FileWriter(fileName, append), times(1));
			mockedStatic.verifyNoMoreInteractions();
		}
		catch (Exception e) {
			fail("Boundary case failed with unexpected exception.");
		}
	}

}