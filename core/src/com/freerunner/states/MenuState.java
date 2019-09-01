package com.freerunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freerunner.Freerunner;

public class MenuState extends State {
    private Texture bg;
    private BitmapFont font;

    public MenuState(GameStateManager gsm){
        super(gsm);
        bg=new Texture("free.png");
        font=new BitmapFont();
        font.setColor(Color.ROYAL);
        cam.setToOrtho(false, Freerunner.WIDTH/2,Freerunner.HEIGHT/2);
        cam.position.x=0;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gsm.set(new PlayState(gsm));
            dispose();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            dispose();
            Gdx.app.exit();
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            gsm.set(new Help(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,0,0, Freerunner.WIDTH/2,Freerunner.HEIGHT/2);
        font.draw(sb, String.format("Play: <SPACE>"),20,125);
        font.draw(sb, String.format("Help: <H>"),20,100);
        font.draw(sb, String.format("HighScore: <S>"),20,75);
        font.draw(sb, String.format("Exit: <ESC>"),20,50);
        sb.end();
    }

    @Override
    public void dispose(){
        bg.dispose();
    }
}
