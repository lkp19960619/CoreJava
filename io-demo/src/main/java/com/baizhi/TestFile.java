package com.baizhi;

import java.io.File;
import java.io.FileFilter;

/**
 * File类的相关操作
 */
public class TestFile {
    public static void main(String[] args) {
        File file = new File("D:\\framework_code\\cloud-demo");
//        deleteDir(file);
        /**
         删除目录下指定类型的文件
         */
        //首先获取目录下的所有文件
        //创建文件过滤器
//        FileFilter fileFilter = new FileFilter() {
//            public boolean accept(File pathname) {
//                if(pathname.isFile())return true;
//                return false;
//            }
//        };
//        File[] files = file.listFiles(fileFilter);
//        for (File file1 : files) {
//            System.out.println(file1.getName());
//        }
        listJavaFiles(file);

    }
    //删除目录
    static void deleteDir(File dir){
        //首先获取当前目录下的所有内容
        File[] files = dir.listFiles();
        //判断目录下的内容
        for(File f:files){
            //如果是文件。直接删除
            if(f.isFile())f.delete();
            //如果是目录，递归调用
            if(f.isDirectory())deleteDir(f);
        }
        //最后删除目录
        dir.delete();
    }
    //打印出一个目录下所有的.java文件
    static void listJavaFiles(File dir){
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if(pathname.isDirectory())return true;
                if(pathname.isFile()){
                    String fileName = pathname.getName();
                    return fileName.endsWith(".java");
                }
                return false;
            }
        });
        for (File file : files) {
            if(file.isFile()) System.out.println(file.getAbsolutePath());
            if(file.isDirectory())listJavaFiles(file);
        }
    }
}
