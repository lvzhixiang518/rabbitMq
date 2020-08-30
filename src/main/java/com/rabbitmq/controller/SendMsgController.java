package com.rabbitmq.controller;

import com.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 过期队列消息
     * 投递到该队列的消息如果没有在5秒内进行消费将被删除
     */
    @RequestMapping("/testTT1")
    public void tt1QueueTest(@RequestParam String msg) {
        rabbitTemplate.convertAndSend("my_tt1_queue",msg);
    }

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
