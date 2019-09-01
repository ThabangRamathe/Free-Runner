package com.freerunner.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.freerunner.Freerunner;
import com.freerunner.states.PlayState;

import java.util.Random;

public class Car {
    private Texture car, bill,texture;
    private Vector2 pos;
    private int width;
//    public int x,y;
    private Rectangle rec;
    private Array<Texture> p;
    private Random rand;

    public Car(int x){
        car =new Texture("beatle.png");
        bill=new Texture("duck.png");
        rand = new Random();
//        this.x=x*(PlayState.SPACE+width);
        int i=rand.nextInt(2);
        pos=new Vector2(x,0);
        if (i==1){
            texture=car;
            rec= new Rectangle(pos.x,0,car.getWidth(),car.getHeight());
        }
        else if (i==0){
            texture=bill;
            rec= new Rectangle(pos.x,bill.getHeight()/2,bill.getWidth(),bill.getHeight()/2);
        }
        width=texture.getWidth();
    }

    public Texture getCar() {
        return texture;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void reposition(float x) {
        pos.set(x,0);
        if (texture.equals(bill)){
            rec.setPosition(x, bill.getHeight()/2);
        }
        else if (texture.equals(car)) {
            rec.setPosition(x, 0);
        }
    }

    public Rectangle getRec() {
        return rec;
    }

    public int getWidth() {
        return width;
    }

    public void setTexture() {
        int i=rand.nextInt(2);
        if (i==1){
            texture=car;
            rec= new Rectangle(pos.x,0,car.getWidth(),car.getHeight());
        }
        else if (i==0){
            texture=bill;
            rec= new Rectangle(pos.x,bill.getHeight()/2,bill.getWidth(),bill.getHeight()/2);
        }
        width=texture.getWidth();
    }

    public void dispose(){
        car.dispose();
        bill.dispose();
        texture.dispose();
    }
}
