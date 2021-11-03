package com.light.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventLisenter {

    class TestEvent extends ApplicationEvent {

        /**
         * Create a new {@code ApplicationEvent}.
         *
         * @param source the object on which the event initially occurred or with
         *               which the event is associated (never {@code null})
         */
        public TestEvent(Object source) {
            super(source);
        }

    }

    @Component
    class TestAsync {

        @Async
        public void as() throws InterruptedException {
            Thread.sleep(1000);
            System.out.println("run");
        }
    }

    @Component
    class TestListener implements ApplicationListener<TestEvent> {

        @Override
        public void onApplicationEvent(TestEvent event) {
            Object source = event.getSource();
            System.out.println(source);
            System.out.println("TestLisenter  onApplicationEvent run");
        }
    }

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TestAsync testAsync;

    @RequestMapping(value = "event", method = RequestMethod.GET)
    public void event() throws InterruptedException {
        testAsync.as();
        System.out.println("over");
        return;
    }


}
