package com.baizhi.collection;

import com.baizhi.dao.Bulb;

public class BulbInterfaceTest {
    public static void main(String[] args) {
        Light1 light1 = new Light1();
        YellowBulb bulb1 = new YellowBulb();
        light1.setBulb(bulb1);
        BlueBulb bulb2 = new BlueBulb();
        light1.setBulb(bulb2);
        BlackBlub blub3 = new BlackBlub();
        light1.setBulb(blub3);
        light1.powerOn();

    }

}


class Light1 {
    private Bulb bulb;

    public void setBulb(Bulb bulb) {
        this.bulb = bulb;
    }
    public void powerOn(){
        bulb.shine();
    }
}

class YellowBulb implements Bulb {
    @Override
    public void shine() {
        System.out.println("发出黄光");
    }

}
class BlueBulb implements Bulb{

    @Override
    public void shine() {
        System.out.println("发出蓝光");
    }

}
class BlackBlub implements Bulb{

    @Override
    public void shine() {
        System.out.println("发出黑光");
    }
}


