package org.wangaai.wangaaibackend;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wangaai")
public class WangaiController {

    private final WangaUtils wangaUtils;

    public WangaiController(WangaUtils wangaUtils) {
        this.wangaUtils = wangaUtils;
    }

    @GetMapping("/fetch")
    public String getResult(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        return wangaUtils.getData(content);
    }

    @GetMapping("/data")
    public String getData(@RequestBody Map<String, String> request) {
        String messageId = request.get("message_id");
        return wangaUtils.fetchMessageText(messageId);
    }
}
