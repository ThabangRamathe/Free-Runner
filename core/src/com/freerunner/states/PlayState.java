package com.freerunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.freerunner.Freerunner;
import com.freerunner.sprites.Background;
import com.freerunner.sprites.Car;
import com.freerunner.sprites.Character;
import com.freerunner.sprites.Chaser;
import com.freerunner.sprites.Play;
import com.freerunner.sprites.PowerUps;

public class PlayState extends State {
    public static final int SPACE=190;
    private static final int COUNT=3;
    private static final int POWER=400;


    private Play player;
    private Chaser chaser;
    int m,u,c;
    float et,sc;
    public Music music;
    int h,h1,score;
    BitmapFont f;

    private Array<Car> cars;
    private Array<Background> b;
    private Array<PowerUps> pow;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        f=new BitmapFont();
        f.setColor(Color.CORAL);
        music=Gdx.audio.newMusic(Gdx.files.internal("mus.mp3"));
        music.setLooping(true);
        music.setVolume(1.0f);
        music.play();
        m=2;
        c=2;
        score=0;
        h=30;
        sc=0.0f;
        h1=30;
        u=50;
        player=new Play(0,5);
        chaser=new Chaser(-100,0);
        cam.setToOrtho(false, Freerunner.WIDTH/2,Freerunner.HEIGHT/2);
        cam.position.x=player.pos.x;

        cars=new Array<Car>();
        b=new Array<Background>();
        pow=new Array<PowerUps>();

        for (int i=1;i<=COUNT;i++){
            cars.add(new Car(i*SPACE));
        }

        for (int j=0;j<2;j++){
            b.add(new Background(j*(Freerunner.WIDTH/2)-190));
        }

        for (int k=0;k<3;k++){
            pow.add(new PowerUps(POWER*(k+1)));
        }
    }

    public void collide(Character e,float newX, float newY){
        if(!check(e,newX,newY)){
            e.move(newX,newY);
        }
    }

    public boolean check(Character e,float newX,float newY){
        boolean coll=false;
        for (Car c:cars){
            float dx=e.pos.x,dy=e.pos.y;
            e.move(newX,newY);
            if (e.collides(c.getRec())){
                coll=true;
//                System.out.println(e.getRec().y+" "+e.getRec().x+30);
//                System.out.println(c.getRec().y+" "+c.getRec().x);
            }
            e.move(dx,dy);
        }

        return coll;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if (player.pos.y==0) {
                player.jump();
                player.move(player.pos.x,player.pos.y+u);
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.push(new MenuState(gsm));
            dispose();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        chaser.update(dt);
        player.update(dt);
        cam.position.x = player.pos.x+10;

        collide(player,player.pos.x+m,player.pos.y);
        collide(chaser,chaser.pos.x+c,chaser.pos.y);
        AI();
        if(player.pos.y>0){
            collide(player,player.pos.x,player.pos.y-1);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            h=20;
            player.recH(h);
            player.slide();
        }
        else{
            h=30;
            player.recH(h);
            player.run();
        }

        if (chaser.pos.y>0){
            collide(chaser,chaser.pos.x,chaser.pos.y-1);
        }

        for (Car c: cars){
            if (cam.position.x-(cam.viewportWidth/2)> c.getPos().x+c.getWidth()){
                c.reposition(c.getPos().x+(c.getWidth()+SPACE)*COUNT);
                c.setTexture();
            }
        }

        for (Background bg:b){
            if(cam.position.x-(cam.viewportWidth/2)>bg.getPos().x+(Freerunner.WIDTH/2)){
                bg.reposition(cam.position.x-(cam.viewportWidth/2)+(Freerunner.WIDTH/2));
            }
        }

        for (PowerUps p:pow){
            if(cam.position.x-(cam.viewportWidth/2)> p.getPos().x+10){
                p.reposition(p.getPos().x+(10+POWER)*COUNT);
                p.setTexture();
            }
        }

        int dm=m;
        float dy= player.pos.y;

        for (PowerUps p:pow) {
            if (checker(player,p)) {
                if (p.getTexture().equals(p.getMon())){
                    sc+=50;
                    p.reposition(p.getPos().x+(10+POWER)*COUNT);
                    p.setTexture();
                }
                else if(p.getTexture().equals(p.getSpeed())){
                    m+=1;
                    p.reposition(p.getPos().x+(10+POWER)*COUNT);
                    p.setTexture();
                    dm=0;
                }
                else if(p.getTexture().equals(p.getWeed())){
                    player.pos.y=120;
                    player.jump();
                    p.reposition(p.getPos().x+(10+POWER)*COUNT);
                    p.setTexture();
                }
            }
        }
        boolean yes;

//        if (dm>200){
//            m=2;
//        }
//        else {dm++;}

        if(cam.position.x-(cam.viewportWidth/2)> chaser.getPos().x+30){
            c=m;
            yes=true;
        }
        else if((player.pos.x)-(chaser.getPos().x+30)<200){
            m=2;
            c=2;
        }

        cam.update();
    }

    private boolean checker(Character p, PowerUps u){
        return p.collides(u.getRec());
    }

    private boolean coll(Character e,Car c){
        boolean col=false;
        float dx=e.pos.x,dy=e.pos.y;
        e.move(dx+2,dy+2);
        if (e.collides(c.getRec())){
            col=true;
        }
        e.move(dx,dy);

        return col;
    }

    public void AI(){
        int q=0;
        for (Car c: cars){
            if (coll(chaser,c)){
//                System.out.println("true");
//                System.out.println(c.getRec().y);
                if (c.getRec().y==27){
                    h1=20;
                    chaser.recH(h1);
                    chaser.slide();
                    break;
                }
                else if (c.getRec().y==0 && chaser.pos.y==0){
                    h1=30;
                    chaser.recH(h1);
                    chaser.jump();
                    chaser.move(chaser.pos.x,chaser.pos.y+u);
                    break;
                }
                else{
                    q++;
//                    h1=30;
//                    chaser.recH(h1);
//                    chaser.run();
                }
            }
        }

        if(q==3){
            chaser.run();
            h1=30;
            chaser.recH(h1);
        }

        if (end(chaser,player)){
            gsm.set(new MenuState(gsm));
            dispose();
        }
    }

    private boolean end(Character c, Character p){
        return c.collides(p.getRec()) || c.pos.x>p.pos.x+30;
    }

    @Override
    public void render(SpriteBatch sb) {
        et+=Gdx.graphics.getDeltaTime();
        sb.setProjectionMatrix(cam.combined);
        sc+=0.1f;
        score=(int)sc;
        sb.begin();
        for (Background back:b){
            sb.draw(back.getBg(),back.getPos().x,0,Freerunner.WIDTH/2,Freerunner.HEIGHT/2);
        }
        for (PowerUps p: pow){
            sb.draw(p.getTexture(),p.getPos().x,p.getPos().y,10,10);
        }
        f.draw(sb,String.format("Score: %d",score),cam.position.x,200);
        sb.draw((player.getAnim().getKeyFrame(et,true)),player.pos.x,player.pos.y,30,h);
        sb.draw(chaser.getAnim().getKeyFrame(et,true),chaser.pos.x,chaser.pos.y,30,h1);
        for (Car c: cars) {
            sb.draw(c.getCar(), c.getPos().x, c.getPos().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        player.dispose();
        chaser.dispose();
        for(Car c: cars){
            c.dispose();
        }
        for(Background bg:b){
            bg.dispose();
        }
        music.dispose();
        cam.setToOrtho(false,0,0);
    }
}
