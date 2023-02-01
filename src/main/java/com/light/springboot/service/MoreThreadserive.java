package com.light.springboot.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class MoreThreadserive {



    List a;

    public static void main(String[] args) {
        MoreThreadserive moreThreadserive = new MoreThreadserive();
        List a = moreThreadserive.getA();
        a= new ArrayList<String>();
        moreThreadserive.setA(a);
        a.add("1212");
        System.out.println(moreThreadserive);

    }



}
