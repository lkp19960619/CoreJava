package com.baizhi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用
 */
public class TestNIOFileCopy {
    public static void main(String[] args) throws Exception {
        fileCopy("a.mp4", "b.mp4");
    }

    static void fileCopy(String srcName, String destName) throws Exception {
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
    }
}
