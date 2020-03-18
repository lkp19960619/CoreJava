package com.baizhi;

import java.io.*;

/**
 * 用节点流实现文件的拷贝
 * 用文件输入流把文件内容一个一个的读到JVM
 * JVM使用文件输出流 把文件内容一个一个写到目标文件
 */
public class TestFileCopy {
    public static void main(String[] args) throws Exception {
//        fileCopy1("a.txt","b.txt");
        fileCopy2("a.mp4","b.mp4");
    }

    static void fileCopy1(String srcName, String destName) throws Exception {
        InputStream in = new FileInputStream(srcName);
        OutputStream out = new FileOutputStream(destName);
        //从源文件中读数据，写入到JVM
        while (true) {
            //一次读一个字节，速度很慢
            int a = in.read();
            //如果读完了，中断
            if (a == -1) break;
            //把JVM中的内容写出到目标文件
            out.write(a);
        }
        //关闭输入流和输出流
        in.close();
        out.close();
    }

    static void fileCopy2(String srcName,String destName) throws Exception {
        InputStream in = new FileInputStream(srcName);
        OutputStream out = new FileOutputStream(destName);
        //每次读1kb
        byte[] bs = new byte[1024];
        while(true){
            //把读取到的byte数组内容写到虚拟机
            int len = in.read(bs);
            if(len == -1)break;
            //把byte数组从JVM写入到目标文件
            out.write(bs,0,len);
        }
        //关闭流
        in.close();
        out.close();
    }
}
