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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.util.Objects;

class ModerationMessageGetTextTest {

	@Test
	@Tag("valid")
	public void getTextReturnsNullWhenFieldIsUnset() {
		// Arrange
		ModerationMessage message = new ModerationMessage(null);
		// Act
		String actualText = message.getText();
		// Assert
		assertNull(actualText, "Expected getText() to return null when text is unset");
	}

	@Test
	@Tag("valid")
	public void getTextReturnsValueWhenFieldIsSet() {
		// Arrange
		String expectedText = "Sample Text"; // TODO: Replace with a specific scenario
												// text if needed
		ModerationMessage message = new ModerationMessage(expectedText);
		// Act
		String actualText = message.getText();
		// Assert
		assertEquals(expectedText, actualText, "Expected getText() to return the set text value");
	}

	@Test
	@Tag("boundary")
	public void getTextReturnsEmptyStringWhenFieldIsSetToEmptyString() {
		// Arrange
		String expectedText = "";
		ModerationMessage message = new ModerationMessage(expectedText);
		// Act
		String actualText = message.getText();
		// Assert
		assertEquals(expectedText, actualText,
				"Expected getText() to return an empty string when text is explicitly set to empty");
	}

	@Test
	@Tag("invalid")
	public void getTextDoesNotBreakWhenNullValueIsSet() {
		// Arrange
		String expectedText = null;
		ModerationMessage message = new ModerationMessage(expectedText);
		// Act
		String actualText = message.getText();
		// Assert
		assertNull(actualText, "Expected getText() to return null when null is explicitly set");
	}

	@Test
	@Tag("integration")
	public void getTextWorksForDifferentInputs() {
		// Arrange
		String expectedText = "A simple moderating message";
		ModerationMessage message = new ModerationMessage(expectedText);
		// Act
		String actualText = message.getText();
		// Assert
		assertEquals(expectedText, actualText,
				"Expected getText() to return the right value across various valid inputs");
	}

}