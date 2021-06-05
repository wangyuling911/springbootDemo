package com.light.springboot.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

/**
 * 类功能描述：<br>
 * <ul>
 * <li>类功能描述1<br>
 * <li>类功能描述2<br>
 * <li>类功能描述3<br>
 * </ul>
 * 修改记录：<br>
 * <ul>
 * <li>修改记录描述1<br>
 * <li>修改记录描述2<br>
 * <li>修改记录描述3<br>
 * </ul>
 *
 * @author xuefl
 * @version 5.0 since 2020-01-13
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //自定义topic
    public static final String TOPIC_TEST = "topic.test";

    //
    public static final String TOPIC_GROUP1 = "topic.group1";

    //
    public static final String TOPIC_GROUP2 = "topic.group2";

    public void send(Object obj) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        List<PartitionInfo> partitionInfos = kafkaTemplate.partitionsFor(TOPIC_TEST);
        MessageConverter messageConverter = kafkaTemplate.getMessageConverter();
        System.out.println(partitionInfos.toString());
        for (int i = 0; i < 6; i++) {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST,0,"wang0", obj2String+i);
        }

//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                //发送失败的处理
//                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
//            }
//            @Override
//            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
//                //成功的处理
//                log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
//            }
//        });


    }
}