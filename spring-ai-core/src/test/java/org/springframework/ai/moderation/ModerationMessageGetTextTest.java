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
package org.springframework.ai.moderation;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Objects;

public class ModerationMessageGetTextTest {

	@Test
	@Tag("valid")
	public void getTextReturnsCorrectText() {
		ModerationMessage message = new ModerationMessage("Hello, World!");
		assertEquals("Hello, World!", message.getText());
	}

	@Test
	@Tag("valid")
	public void getTextReturnsNullWhenTextNotSet() {
		ModerationMessage message = new ModerationMessage(null);
		assertNull(message.getText());
	}

	@Test
	@Tag("valid")
	public void getTextReturnsEmptyString() {
		ModerationMessage message = new ModerationMessage("");
		assertEquals("", message.getText());
	}

	@Test
	@Tag("valid")
	public void getTextReturnsCorrectTextAfterMultipleSetTextCalls() {
		ModerationMessage message = new ModerationMessage("First");
		message.setText("Second");
		assertEquals("Second", message.getText());
		message.setText("Third");
		assertEquals("Third", message.getText());
	}

	@Test
	@Tag("valid")
	public void getTextReturnsCorrectTextWithSpecialCharacters() {
		ModerationMessage message = new ModerationMessage("Hello, World! @#$%^&*()");
		assertEquals("Hello, World! @#$%^&*()", message.getText());
	}

	@Test
	@Tag("valid")
	public void getTextReturnsCorrectTextWithUnicodeCharacters() {
		ModerationMessage message = new ModerationMessage("こんにちは世界");
		assertEquals("こんにちは世界", message.getText());
	}

}