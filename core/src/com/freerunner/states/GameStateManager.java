package com.freerunner.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states=new Stack<State>();
    }

    public void push(State st){
        states.push(st);
    }

    public void pop(){
        states.pop();
    }

    public State peek(){
        return states.peek();
    }

    public void set(State st){
        states.pop();
        states.push(st);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
