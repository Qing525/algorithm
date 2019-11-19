package com.wld.msg.controller;


import com.wld.msg.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RemindService wldNoticeService;

    @MessageMapping("/notice")
    @SendToUser("/user/topic/get-notice")
    public void getNotice(Principal principal, String hello) {
        // template.convertAndSend("/topic/get-notice", principal.getName());

        template.convertAndSendToUser(principal.getName(), "/topic/get-notice", "hello");
    }

    @MessageMapping("/read-notice")
    public void isReadNotice(Principal principal, long noticeId) {
       // wldNoticeService.setNoticeIsRead(noticeId);
    }
}



