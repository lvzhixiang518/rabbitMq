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

    /**
     * 过期队列消息投递到死信队列
     * 投递到正常的队列，但是该队列有过期时间限制，到了过期时间后，消息会被发送到死信交换机（队列）
     */
    @RequestMapping("/testTT2")
    public void tt2QueueTest(@RequestParam String msg) {
        rabbitTemplate.convertAndSend("my_normal_exchange","my_ttl_dlx",msg);
    }

    /**
     * 消息长度超过2会投递到死信队列
     * 投递到正常的队列，长度超过2以后，消息会被发送到死信交换机（队列）
     */
    @RequestMapping("/testTT3")
    public void tt3QueueTest(@RequestParam String msg) {
        rabbitTemplate.convertAndSend("my_normal_exchange","my_max_dlx",msg);
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
