package com.freerunner.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Chaser extends Character{
    private Animation<Texture> anim, run, jmp, sld;
    private Texture[] r,j,sl;
    private Rectangle rec;
    public int h;

    public Chaser(int x,int y) {
        super(x,y);
        pos=new Vector2(x,y);

        r=new Texture[7];
        j=new Texture[7];
        sl=new Texture[4];

        for (int i=0;i<7;i++){
            r[i]=new Texture("chase/run/r"+Integer.toString(i+1)+".png");
            j[i]=new Texture("chase/jump/j"+Integer.toString(i+1)+".png");
        }

        for (int i=0;i<4;i++){
            sl[i]=new Texture("chase/slide/s"+Integer.toString(i+1)+".png");
        }

        run=new Animation<Texture>(1f/14f,r);
        jmp= new Animation<Texture>(1f/7f,j);
        sld=new Animation<Texture>(1f/4f,sl);
        run();

        h=30;

        rec=new Rectangle(pos.x,pos.y,30,h);
    }

    @Override
    public boolean collides(Rectangle other) {
        return other.overlaps(rec);
    }

    public Animation<Texture> getAnim() {
        return anim;
    }

    @Override
    public void move(float newx, float newy) {
        pos.set(newx,newy);
        rec.setPosition(newx,newy);
    }

    @Override
    public void update(float dt) {
        rec.setPosition(pos.x,pos.y);
    }

    public void run() {
        anim=run;
    }

    public void jump() { anim=jmp; }

    public void slide() { anim=sld; }

    @Override
    public void dispose() {
        for (Texture t:j){t.dispose();}
        for (Texture t:r){t.dispose();}
        for (Texture t:sl){t.dispose();}
    }

    public Rectangle getRec() {
        return rec;
    }

    @Override
    public void recH(int h) {
        rec.setHeight(h);
        this.h=h;
    }

    public Vector2 getPos() {
        return pos;
    }
}
