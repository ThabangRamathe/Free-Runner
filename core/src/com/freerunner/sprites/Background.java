package com.freerunner.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private float x;
    private Texture bg;
    private Vector2 pos;

    public Background(int x) {
        this.x=x;
        pos=new Vector2(x,0);
        bg=new Texture("bg.png");
    }

    public float getX() {
        return x;
    }

    public Texture getBg() {
        return bg;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void reposition(float x) {
        pos.set(x,pos.y);
        this.x=x;
    }

    public void dispose(){
        bg.dispose();
    }
}
