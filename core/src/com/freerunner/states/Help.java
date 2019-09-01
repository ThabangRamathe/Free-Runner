package com.freerunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freerunner.Freerunner;

public class Help extends State{
    private Texture bg;
    private BitmapFont font,t;

    public Help(GameStateManager gsm) {
        super(gsm);
        bg=new Texture("bg.png");
        font=new BitmapFont();
        t=new BitmapFont();
        t.setColor(Color.GOLDENROD);
        font.setColor(Color.ROYAL);
        cam.setToOrtho(false, Freerunner.WIDTH/2,Freerunner.HEIGHT/2);
        cam.position.x=0;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)){
            gsm.set(new MenuState(gsm));
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
        t.draw(sb,String.format("Controls in Gameplay"),20,150);
        font.draw(sb, String.format("Jump: <UP>"),20,125);
        font.draw(sb, String.format("Duck/Slide: <DOWN>"),20,100);
        font.draw(sb, String.format("Exit to Menu: <ESC>"),20,75);
        font.draw(sb, String.format("Exit to Desktop: <Q>"),20,50);
        t.draw(sb, String.format("Back: <BACKSPACE>"),200,150);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        font.dispose();
    }
}
