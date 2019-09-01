package com.freerunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freerunner.Freerunner;

public class PauseState extends State {
    private Texture bg,ply;

    public PauseState(GameStateManager gsm) {
        super(gsm);
        bg=new Texture("bg.png");
        ply=new Texture("ply.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.pop();
            dispose();
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0,0, Freerunner.WIDTH,Freerunner.HEIGHT);
        sb.draw(ply,(Freerunner.WIDTH/2)-(ply.getWidth()/2), (Freerunner.HEIGHT/2)-(ply.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        ply.dispose();
    }
}
