package com.baizhi;

import java.io.*;

/**
 * 缓冲流实现文件拷贝
 */
public class TestBuffedFileCopy {
    public static void main(String[] args) throws Exception{
        copy("a.txt","b.txt");
    }

    static void copy(String srcName, String destName) throws Exception {
        //创建文件输入节点流对象
        FileInputStream fis = new FileInputStream(srcName);
        //创建文件输出节点流对象
        FileOutputStream fos = new FileOutputStream(destName);
        //创建文件输入过滤流对象
        BufferedInputStream bis = new BufferedInputStream(fis);
        //创建文件输出过滤流对象
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //一次读1KB
        byte[] bs = new byte[1024];
        //一行一行的读
        while (true) {
            int read = bis.read(bs);
            //假如读完了
            if (read == -1) break;
            //否则，从源文件中读取数据，写到缓冲区
            bos.write(bs, 0, read);
            //把缓冲区中的数据写到目标文件
            bos.flush();
        }
        //关闭流
        bis.close();
        bos.close();
    }
}
