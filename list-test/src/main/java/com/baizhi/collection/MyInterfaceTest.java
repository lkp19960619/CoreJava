package com.baizhi.collection;

public class MyInterfaceTest {
    public static void main(String[] args) {
        Light light = new Light();  //初始化台灯，相当于新购一台台灯
        RedBulb bulb1 = new RedBulb();  //初始化一个灯泡，相当于新购一个灯泡
        light.setBulb(bulb1);   //把灯泡安装到台灯上
        light.powerOn();

    }
}

class Light {
    private RedBulb bulb;

    public void setBulb(RedBulb bulb) {
        this.bulb = bulb;
    }


    public void powerOn() {
        bulb.liang();
    }

}

class RedBulb {
    public void liang() {
        System.out.println("发出红光");
    }
}


