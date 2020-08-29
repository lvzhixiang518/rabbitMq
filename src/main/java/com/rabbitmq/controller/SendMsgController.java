package com.rabbitmq.controller;

import com.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SendMsgController
 * @Description 发送消息的测试类
 * @Author LZX
 * @Date 2020/8/23 22:03
 **/
@RestController
public class SendMsgController {
    //注入rabbitMQ的模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam String msg,@RequestParam String key){
        /**
         * 发送消息
         * 参数1：交换机名称
         * 参数2：路由key
         * 参数3：发送的消息
         */
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOPIC_EXCHANGE,key,msg);
        return "发送消息成功！";
    }
}
