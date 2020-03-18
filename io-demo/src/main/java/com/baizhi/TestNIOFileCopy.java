package com.baizhi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用
 */
public class TestNIOFileCopy {
    public static void main(String[] args) throws Exception {
        fileCopy1("a.mp4", "b.mp4");
        fileCopy2("a.mp4", "c.mp4");
        fileCopy3("a.mp4", "d.mp4");
    }

    static void fileCopy1(String srcName, String destName) throws Exception {
        long t1 = System.nanoTime();
        //创建一个Buffer对象
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileInputStream fis = new FileInputStream(srcName);
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(destName);
        FileChannel fosChannel = fos.getChannel();
        while (true) {
            //从channel中读数据写满缓冲区
            int len = fisChannel.read(buffer);
            if (len == -1) break;
            //从写模式切换到读模式
            buffer.flip();
            fosChannel.write(buffer);
            //从读模式切换到写模式
            buffer.clear();
        }
        fisChannel.close();
        fosChannel.close();
        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1E9);
    }

    //将一个文件直接映射到内存
    static void fileCopy2(String srcName,String destName) throws Exception {
        long t1 = System.nanoTime();
        FileInputStream fis = new FileInputStream(srcName);
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(destName);
        FileChannel fosChannel = fos.getChannel();

        MappedByteBuffer buffer = fisChannel.map(FileChannel.MapMode.READ_ONLY, 0, fisChannel.size());
        //把这个文件通过通道写出
        fosChannel.write(buffer);
        fisChannel.close();
        fosChannel.close();

        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1E9);
    }

    //效率最高
    static void fileCopy3(String srcName,String destName) throws Exception{
        long t1 = System.nanoTime();
        FileInputStream fis = new FileInputStream(srcName);
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(destName);
        FileChannel fosChannel = fos.getChannel();

        fisChannel.transferTo(0,fisChannel.size(), fosChannel);
        fisChannel.close();
        fosChannel.close();

        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1E9);
    }
}
