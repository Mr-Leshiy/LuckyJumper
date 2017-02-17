package com.mygdx.Lucky_Jumper.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by alexey on 05.01.17.
 */

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<State>();

    }

    public void push(State state)
    {
        states.push(state);

    }

    public void pop()
    {
        states.pop().dispose();
    }

    public void set(State state)
    {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float delta)
    {
        states.peek().update(delta);
    }

    public void render (SpriteBatch sb)
    {
        states.peek().render(sb);
    }

    public State peek () { return  states.peek();}




}
