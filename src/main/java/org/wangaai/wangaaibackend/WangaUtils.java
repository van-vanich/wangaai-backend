package org.wangaai.wangaaibackend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.wangaai.wangaaibackend.dto.Agent;
import org.wangaai.wangaaibackend.dto.Message;
import org.wangaai.wangaaibackend.dto.MessagePart;
import org.wangaai.wangaaibackend.dto.PostBodyDto;

import java.util.List;

@Component
public class WangaUtils {
    private static Agent agent = new Agent("crypto_tarot", "text");
    public static final String URL = "https://dev-api.promptcatalog.online/api/message";
    @Value("${X_Api_Token}")
    public String X_Api_Token;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WangaUtils() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String getData(String content) {
        MessagePart messagePart = new MessagePart(content, "user");
        Message message = new Message(new MessagePart[]{messagePart});
        PostBodyDto postBodyDto = new PostBodyDto(agent, message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.ALL));
        headers.set("X-Api-Token", X_Api_Token);

        HttpEntity<PostBodyDto> request = new HttpEntity<>(postBodyDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            System.out.println(root);
            return root.path("message_id").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response", e);
        }
    }

    public String fetchMessageText(String messageId) {
        String url = "https://dev-api.promptcatalog.online/api/message/" + messageId;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON, MediaType.APPLICATION_PROBLEM_JSON));
        headers.set("X-Api-Token", X_Api_Token);


        String result = "";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch message text: " + response.getStatusCode());
        }
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            System.out.println(root);
            result = root.path("text").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch message text", e);
        }
        return result;
    }
}
