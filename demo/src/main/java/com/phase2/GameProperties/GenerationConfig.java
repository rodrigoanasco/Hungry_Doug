package com.phase2.GameProperties;

import com.phase2.Handler;
import com.phase2.GameObjects.GameObject;

public class GenerationConfig {
    private int count;
    private Class<? extends GameObject> objectType;
    private Handler handler;
    private int minDistanceFromDoug;

    public GenerationConfig(int count, Class<? extends GameObject> objectType, Handler handler, int minDistanceFromDoug) {
        this.count = count;
        this.objectType = objectType;
        this.handler = handler;
        this.minDistanceFromDoug = minDistanceFromDoug;
    }

    public int getCount() { return count; }
    public Class<? extends GameObject> getObjectType() { return objectType; }
    public Handler getHandler() { return handler; }
    public int getMinDistanceFromDoug() { return minDistanceFromDoug; }
}
