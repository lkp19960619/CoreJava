package com.baizhi;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNIO {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("a.txt");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //创建FileChannel对象，channel相当于BIO里面的流
        FileChannel channel = inputStream.getChannel();

        while(true){
            //buffer中读取数据
            int len = channel.read(buffer);
            //判断是否读完
            if(len == -1)break;
            //获取读取的数据，首先要把当前读取的位置归零
            buffer.flip();
            //如果buffer中有数据，继续读
            while(buffer.hasRemaining()){
                //从buffer中拿到数据
                System.out.println((char)buffer.get());
            }
            //完成了一次读写之后把当前读写位置position和缓冲位置limit都归零
            buffer.clear();
        }
        //关闭channel
        channel.close();
    }
}
