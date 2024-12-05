package com.phase2.GameProperties;

import com.phase2.Handler;
import com.phase2.GameObjects.GameObject;

/**
 * The {@code GenerationConfig} class encapsulates the configuration for generating game objects.
 * <p>
 * This class specifies details such as the number of objects to generate, the type of objects,
 * the handler responsible for managing these objects, and the minimum distance from the main character, Doug.
 * </p>
 */
public class GenerationConfig {

    /**
     * The number of objects to generate.
     */
    private int count;

    /**
     * The type of game objects to generate (e.g., obstacles, rewards).
     */
    private Class<? extends GameObject> objectType;

    /**
     * The handler responsible for managing the generated objects.
     */
    private Handler handler;

    /**
     * The minimum distance from Doug at which objects should be generated.
     */
    private int minDistanceFromDoug;

    /**
     * Constructs a {@code GenerationConfig} with the specified parameters.
     * 
     * @param count              the number of objects to generate
     * @param objectType         the type of game objects to generate
     * @param handler            the handler responsible for managing the objects
     * @param minDistanceFromDoug the minimum distance from Doug for object placement
     */
    public GenerationConfig(int count, Class<? extends GameObject> objectType, Handler handler, int minDistanceFromDoug) {
        this.count = count;
        this.objectType = objectType;
        this.handler = handler;
        this.minDistanceFromDoug = minDistanceFromDoug;
    }

    /**
     * Gets the number of objects to generate.
     * 
     * @return the number of objects to generate
     */
    public int getCount() {
        return count;
    }

    /**
     * Gets the type of game objects to generate.
     * 
     * @return the class type of the objects to generate
     */
    public Class<? extends GameObject> getObjectType() {
        return objectType;
    }

    /**
     * Gets the handler responsible for managing the generated objects.
     * 
     * @return the handler for managing the objects
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Gets the minimum distance from Doug at which objects should be generated.
     * 
     * @return the minimum distance from Doug
     */
    public int getMinDistanceFromDoug() {
        return minDistanceFromDoug;
    }
}
