package com.stu.serverhello.controller;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.dto.ChatRequestDTO;
import com.stu.serverhello.vo.ChatResponseVO;
import com.stu.serverhello.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Result<ChatResponseVO> chat(@RequestBody ChatRequestDTO requestDTO) {
        String answer = chatService.chat(requestDTO.getMessage());
        ChatResponseVO vo = new ChatResponseVO(requestDTO.getMessage(), answer);
        return Result.success(vo);
    }
}