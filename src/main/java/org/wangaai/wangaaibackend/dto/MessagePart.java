package org.wangaai.wangaaibackend.dto;

public class MessagePart {
    private String content;
    private String role;

    public MessagePart(String content, String role) {
        this.content = content;
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
