package org.wangaai.wangaaibackend.dto;

public class PostBodyDto {
    private Agent agent;
    private Message message;

    public PostBodyDto(Agent agent, Message message) {
        this.agent = agent;
        this.message = message;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
