
// ********RoostGPT********
/*

roost_feedback [22/08/2025, 2:05:08 PM]:add appropriate comments\n
*/

// ********RoostGPT********

package org.springframework.ai.chat.client;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Tests for ChatClientResponseEntity.
 * Validates the response entity conversions and metadata integrity during ChatClient interactions.
 */
@ExtendWith(MockitoExtension.class)
public class ChatClientResponseEntityTests {

	@Mock
	ChatModel chatModel;

	@Captor
	ArgumentCaptor<Prompt> promptCaptor;

	@Test
	public void responseEntityTest() {
		// Prepare mock metadata and chat response with assistant message
		ChatResponseMetadata metadata = ChatResponseMetadata.builder().keyValue("key1", "value1").build();
		var chatResponse = new ChatResponse(List.of(new Generation(new AssistantMessage("""
				{"name":"John", "age":30}
				"""))), metadata);

		// Mock ChatModel behavior for capturing prompts and returning a predefined response
		given(this.chatModel.call(this.promptCaptor.capture())).willReturn(chatResponse);

		// Invoke ChatClient and validate the returned response and entity objects
		ResponseEntity<ChatResponse, MyBean> responseEntity = ChatClient.builder(this.chatModel)
			.build()
			.prompt()
			.user("Tell me about John")
			.call()
			.responseEntity(MyBean.class);

		// Assert the correctness of the metadata and response structure
		assertThat(responseEntity.getResponse()).isEqualTo(chatResponse);
		assertThat(responseEntity.getResponse().getMetadata().get("key1").toString()).isEqualTo("value1");
		assertThat(responseEntity.getEntity()).isEqualTo(new MyBean("John", 30));

		// Validate the captured user message within the prompt
		Message userMessage = this.promptCaptor.getValue().getInstructions().get(0);
		assertThat(userMessage.getMessageType()).isEqualTo(MessageType.USER);
		assertThat(userMessage.getText()).contains("Tell me about John");
	}

	@Test
	public void parametrizedResponseEntityTest() {
		// Prepare mock chat response with assistant message containing list of beans
		var chatResponse = new ChatResponse(List.of(new Generation(new AssistantMessage("""
				[
					{"name":"Max", "age":10},
					{"name":"Adi", "age":13}
				]
				"""))));

		// Mock ChatModel behavior for capturing prompts and returning a predefined response
		given(this.chatModel.call(this.promptCaptor.capture())).willReturn(chatResponse);

		// Invoke ChatClient and validate the returned response and list of entities
		ResponseEntity<ChatResponse, List<MyBean>> responseEntity = ChatClient.builder(this.chatModel)
			.build()
			.prompt()
			.user("Tell me about them")
			.call()
			.responseEntity(new ParameterizedTypeReference<List<MyBean>>() {});

		// Assert the correctness of the response and list entity values
		assertThat(responseEntity.getResponse()).isEqualTo(chatResponse);
		assertThat(responseEntity.getEntity().get(0)).isEqualTo(new MyBean("Max", 10));
		assertThat(responseEntity.getEntity().get(1)).isEqualTo(new MyBean("Adi", 13));

		// Validate the captured user message within the prompt
		Message userMessage = this.promptCaptor.getValue().getInstructions().get(0);
		assertThat(userMessage.getMessageType()).isEqualTo(MessageType.USER);
		assertThat(userMessage.getText()).contains("Tell me about them");
	}

	@Test
	public void customSoCResponseEntityTest() {
		// Prepare mock chat response with assistant message in JSON format
		var chatResponse = new ChatResponse(List.of(new Generation(new AssistantMessage("""
					{"name":"Max", "age":10},
				"""))));

		// Mock ChatModel behavior for capturing prompts and returning a predefined response
		given(this.chatModel.call(this.promptCaptor.capture())).willReturn(chatResponse);

		// Invoke ChatClient and validate the returned response and map entity
		ResponseEntity<ChatResponse, Map<String, Object>> responseEntity = ChatClient.builder(this.chatModel)
			.build()
			.prompt()
			.user("Tell me about Max")
			.call()
			.responseEntity(new MapOutputConverter());

		// Assert the correctness of the metadata, response structure, and returned map values
		assertThat(responseEntity.getResponse()).isEqualTo(chatResponse);
		assertThat(responseEntity.getEntity().get("name")).isEqualTo("Max");
		assertThat(responseEntity.getEntity().get("age")).isEqualTo(10);

		// Validate the captured user message within the prompt
		Message userMessage = this.promptCaptor.getValue().getInstructions().get(0);
		assertThat(userMessage.getMessageType()).isEqualTo(MessageType.USER);
		assertThat(userMessage.getText()).contains("Tell me about Max");
	}

	record MyBean(String name, int age) {

	}

}
