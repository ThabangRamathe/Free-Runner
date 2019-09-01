package com.freerunner.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class PowerUps {
    private Texture texture,weed,mon,speed;
    private Vector2 pos;
    private Random rand;
    private Rectangle rec;

    public PowerUps(int x) {
        rand=new Random();
        pos=new Vector2(x,rand.nextInt(50));
        int i=rand.nextInt(3);
        rec=new Rectangle(pos.x,pos.y,10,10);
        weed=new Texture("power/weed.jpg");
        mon=new Texture("power/mon.jpg");
        speed=new Texture("power/speed.jpg");

        if(i==0){texture=weed;}
        else if (i==1){texture=mon;}
        else if (i==2){texture=speed;}
    }


    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Texture getWeed() {
        return weed;
    }

    public Texture getMon() {
        return mon;
    }

    public Texture getSpeed() {
        return speed;
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setTexture() {
        rand=new Random();
        int i=rand.nextInt(3);
        if(i==0){texture=weed;}
        else if (i==1){texture=mon;}
        else if (i==2){texture=speed;}
    }

    public void reposition(float x){
        pos.set(x,0);
        rec=new Rectangle(x,0,10,10);
    }
}
