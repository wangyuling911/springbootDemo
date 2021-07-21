package com.light.springboot.service;

import java.util.EventObject;


public class TestEvent extends EventObject {


    public TestEvent() {
        super(new Object());
    }

    public TestEvent(Object source) {
        super(source);
    }

    public String a;
}
