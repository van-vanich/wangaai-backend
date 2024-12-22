package org.wangaai.wangaaibackend.dto;

public class Message {
    private MessagePart[] parts;

    public Message(MessagePart[] parts) {
        this.parts = parts;
    }

    public MessagePart[] getParts() {
        return parts;
    }

    public void setParts(MessagePart[] parts) {
        this.parts = parts;
    }
}
