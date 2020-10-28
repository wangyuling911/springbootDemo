package com.light.springboot;

import com.alipay.api.DefaultAlipayClient;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.time.LocalDate.now;

public  class JunitTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String a= "biejign";
    }
    //测试nio
    public  void save2Local(String data, String pathName) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        File dir = new File(pathName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Path localFilePath = Paths.get(pathName);
        File localFile = localFilePath.toFile();
        if (localFile.exists()) {
            localFile.delete();
        }
        FileChannel fileChannel = null;
        try {
            fileChannel = FileChannel.open(localFilePath, StandardOpenOption.CREATE, StandardOpenOption.READ);
            fileChannel.transferFrom(Channels.newChannel(inputStream), 0, Long.MAX_VALUE);
        } finally {
            //关闭文件操作
            fileChannel.close();
        }
    }

    //二分查找
    @Test
    public void finalize459546() {
        int y=12;
        int[] a=new int[]{2,3,6,9,12};
        int xuhuan = JunitTest.xuhuan(y, a);
        System.out.println(xuhuan);
    }
    public static int  xuhuan(int y,int[] a){
        int sta=0;
        int end=a.length-1;
        while (sta<=end){
            int mid=(sta+end)/2;
            if(y<a[mid]){
                end=mid-1;
            }else if (y>a[mid]){
                sta=mid+1;
            }else{
                return a[mid];
            }
        }
        return -1;
    }


}
