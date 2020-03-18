package com.baizhi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestFileInputStream {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("a.txt");
        while(true){
            int a = in.read();
            if(a == -1)break;
            System.out.println((char) a);
        }
        in.close();
    }
}
