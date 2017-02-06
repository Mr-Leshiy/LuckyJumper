package com.mygdx.game.GameObjects;

/**
 * Created by alexey on 06.02.17.
 */

public class PlatformData
{
     boolean isActive;
     boolean isBoost;

    public PlatformData(boolean isActive,boolean isBoost)
    {
        this.isActive=isActive;
        this.isBoost=isBoost;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isBoost() {
        return isBoost;
    }
}