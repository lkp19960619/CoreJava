package com.baizhi;
import	java.io.BufferedOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * BufferedOutputStream/BufferedInputStream可以将数据先写到缓冲区，提高IO效率，减少访问磁盘的次数
 * 数据存储在缓冲区中，flush是将缓冲区的内容写到文件中，也可以直接close
 */
public class TestBufferedStream {
    public static void main(String[] args) throws Exception {
        FileOutputStream outputStream = new FileOutputStream("1.txt");
        //创建过滤流-->缓冲流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write('A');
        bufferedOutputStream.write('B');
        //将缓冲区中的数据写到文件
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
