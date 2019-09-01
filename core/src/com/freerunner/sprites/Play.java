package com.freerunner.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Play extends Character{
    public int x,y;
    private Rectangle rec;
    private int move;
    public Animation<Texture> anim,jmp,run,stnd,sld;
    public Texture[] r,s,j,sl;
    public float elapsed;

    public Play(int x,int y){
        super(x,y);
        this.x=x;
        this.y=y;

        r=new Texture[17];
        s=new Texture[5];
        j=new Texture[13];
        sl=new Texture[4];

        for (int i=0;i<4;i++){
            sl[i]=new Texture("run/slide/s"+Integer.toString(i+1)+".png");
        }
        sld=new Animation<Texture>(1f/4f,sl);

        for (int i=0;i<17;i++){
            r[i]=new Texture("run/run/r"+Integer.toString(i+1)+".png");
        }
        run=new Animation<Texture>(1f/17f,r);

        for (int i=0;i<5;i++){
            s[i]=new Texture("run/stand/s"+Integer.toString(i+1)+".png");
        }
        stnd=new Animation<Texture>(1f/5f,s);

        for (int i=0;i<13;i++){
            j[i]=new Texture("run/j/j"+Integer.toString(i+7)+".png");
        }
        jmp=new Animation<Texture>(1f/10f,j);

        run();
        rec= new Rectangle(x-1,y-1,30,30);
        move=2;
    }

    public void slide(){
        anim=sld;
    }

    @Override
    public void dispose() {
        for (Texture t:j){t.dispose();}
        for (Texture t:r){t.dispose();}
        for (Texture t:sl){t.dispose();}
    }

    public void run(){
        anim=run;
    }

    public void stand(){
        anim=stnd;
    }

    public void jump(){
        anim=jmp;
    }

    public void update(float dt){
        rec.setPosition(pos.x,pos.y);
    }

    public void move(float newx,float newy){
        pos.set(newx,newy);
        rec.setPosition(newx,newy);
    }

    public void reposition(float x, float y){
        rec.setPosition(x,y);
    }

    public Texture getPlay() {
        return anim.getKeyFrame(elapsed,true);
    }

    public boolean collides(Rectangle other){
        return other.overlaps(rec);
    }

    public void setMove(int move) {
        this.move = move;
    }

    public Animation<Texture> getAnim() {
        return anim;
    }

    public Texture[] getP() {
        return r;
    }

    public Rectangle getRec() {
        return rec;
    }

    public void recH(int h){
        rec.setHeight(h);
    }
}
