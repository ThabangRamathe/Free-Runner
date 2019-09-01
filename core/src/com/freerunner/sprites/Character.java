package com.freerunner.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character {
    public Vector2 pos;

    public Character(int x,int y) {
        pos=new Vector2(x,y);
    }

    public abstract boolean collides(Rectangle other);
    public abstract Rectangle getRec();
    public abstract void recH(int h);
    public abstract Animation<Texture> getAnim();
    public abstract void move(float newx,float newy);
    public abstract void update(float dt);
    public abstract void jump();
    public abstract void run();
    public abstract void slide();
    public abstract void dispose();
}
