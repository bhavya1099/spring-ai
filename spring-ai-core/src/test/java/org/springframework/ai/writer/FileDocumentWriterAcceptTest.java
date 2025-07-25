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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.springframework.ai.document.DocumentWriter;

public class FileDocumentWriterAcceptTest {

	private static final String TEST_FILE_NAME = "testFile.txt";

	private static final String METADATA_START_PAGE_NUMBER = "startPage";

	private static final String METADATA_END_PAGE_NUMBER = "endPage";

	private FileDocumentWriter fileDocumentWriter;

	private List<Document> documents;

	@BeforeEach
	public void setUp() {
		documents = new ArrayList<>();
	}

	@AfterEach
	public void tearDown() {
		File file = new File(TEST_FILE_NAME);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	@Tag("valid")
	public void normalOperationWithDocumentMarkers() {
		Document doc1 = mock(Document.class);
		Map<String, String> metadata1 = new HashMap<>();
		metadata1.put(METADATA_START_PAGE_NUMBER, "1");
		metadata1.put(METADATA_END_PAGE_NUMBER, "10");
		when(doc1.getMetadata()).thenReturn(metadata1);
		when(doc1.getFormattedContent(MetadataMode.NONE)).thenReturn("Content 1");
		Document doc2 = mock(Document.class);
		Map<String, String> metadata2 = new HashMap<>();
		metadata2.put(METADATA_START_PAGE_NUMBER, "11");
		metadata2.put(METADATA_END_PAGE_NUMBER, "20");
		when(doc2.getMetadata()).thenReturn(metadata2);
		when(doc2.getFormattedContent(MetadataMode.NONE)).thenReturn("Content 2");
		documents.add(doc1);
		documents.add(doc2);
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME, true, MetadataMode.NONE, false);
		fileDocumentWriter.accept(documents);
		// TODO: Verify the content of the file
	}

	@Test
	@Tag("valid")
	public void normalOperationWithoutDocumentMarkers() {
		Document doc1 = mock(Document.class);
		when(doc1.getFormattedContent(MetadataMode.NONE)).thenReturn("Content 1");
		Document doc2 = mock(Document.class);
		when(doc2.getFormattedContent(MetadataMode.NONE)).thenReturn("Content 2");
		documents.add(doc1);
		documents.add(doc2);
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME, false, MetadataMode.NONE, false);
		fileDocumentWriter.accept(documents);
		// TODO: Verify the content of the file
	}

	@Test
	@Tag("boundary")
	public void emptyDocumentList() {
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME);
		fileDocumentWriter.accept(documents);
		File file = new File(TEST_FILE_NAME);
		assertTrue(file.exists());
		assertEquals(0, file.length());
	}

	@Test
	@Tag("invalid")
	public void fileWritingException() {
		fileDocumentWriter = new FileDocumentWriter("/invalid/path/testFile.txt");
		assertThrows(RuntimeException.class, () -> fileDocumentWriter.accept(documents));
	}

	@Test
	@Tag("valid")
	public void metadataModeHandling() {
		Document doc = mock(Document.class);
		when(doc.getFormattedContent(MetadataMode.FULL)).thenReturn("Content with full metadata");
		documents.add(doc);
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME, false, MetadataMode.FULL, false);
		fileDocumentWriter.accept(documents);
		// TODO: Verify the content of the file
	}

	@Test
	@Tag("valid")
	public void appendMode() throws IOException {
		File file = new File(TEST_FILE_NAME);
		file.createNewFile();
		Document doc = mock(Document.class);
		when(doc.getFormattedContent(MetadataMode.NONE)).thenReturn("Appended Content");
		documents.add(doc);
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME, false, MetadataMode.NONE, true);
		fileDocumentWriter.accept(documents);
		// TODO: Verify the content of the file
	}

	@Test
	@Tag("invalid")
	public void nullDocumentList() {
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME);
		assertThrows(NullPointerException.class, () -> fileDocumentWriter.accept(null));
	}

	@Test
	@Tag("boundary")
	public void largeDocumentList() {
		for (int i = 0; i < 1000; i++) {
			Document doc = mock(Document.class);
			when(doc.getFormattedContent(MetadataMode.NONE)).thenReturn("Content " + i);
			documents.add(doc);
		}
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME);
		fileDocumentWriter.accept(documents);
		// TODO: Verify the content of the file
	}

	@Test
	@Tag("invalid")
	public void missingMetadata() {
		Document doc1 = mock(Document.class);
		Map<String, String> metadata1 = new HashMap<>();
		metadata1.put(METADATA_START_PAGE_NUMBER, "1");
		metadata1.put(METADATA_END_PAGE_NUMBER, "10");
		when(doc1.getMetadata()).thenReturn(metadata1);
		when(doc1.getFormattedContent(MetadataMode.NONE)).thenReturn("Content 1");
		Document doc2 = mock(Document.class);
		when(doc2.getMetadata()).thenReturn(new HashMap<>());
		when(doc2.getFormattedContent(MetadataMode.NONE)).thenReturn("Content 2");
		documents.add(doc1);
		documents.add(doc2);
		fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME, true, MetadataMode.NONE, false);
		fileDocumentWriter.accept(documents);
		// TODO: Verify the content of the file
	}

	@Test
	@Tag("integration")
	public void concurrentAccess() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			List<Document> threadDocuments = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				Document doc = mock(Document.class);
				when(doc.getFormattedContent(MetadataMode.NONE)).thenReturn("Content " + j);
				threadDocuments.add(doc);
			}
			executorService.submit(() -> {
				fileDocumentWriter = new FileDocumentWriter(TEST_FILE_NAME, false, MetadataMode.NONE, true);
				fileDocumentWriter.accept(threadDocuments);
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		// TODO: Verify the content of the file
	}

}