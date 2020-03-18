package com.baizhi;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestFileOutputStream {
    public static void main(String[] args) throws Exception {
        //创建文件输出流对象，简历JVM和外部文件之间的关联,如果文件不存在会帮助创建
        OutputStream outputStream = new FileOutputStream("a.txt",true);
        //通过字节输出流写数据
        outputStream.write(65);
        outputStream.write(new byte[]{'B','C','D','E'});
        //关闭输出流
        outputStream.close();
    }
}
