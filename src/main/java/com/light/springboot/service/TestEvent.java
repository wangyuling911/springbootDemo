package com.light.springboot.service;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class TestEvent extends EventObject {


    public TestEvent() {
        super(new Object());
    }

    public TestEvent(Object source) {
        super(source);
    }

    public String a;
}
