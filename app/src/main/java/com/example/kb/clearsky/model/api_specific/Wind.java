package com.example.kb.clearsky.model.api_specific;

/**
 * Created by Karlo on 2017-07-04.
 */

public class Wind {

    private float speed;
    private float direction;

    public Wind(){

    }

    public Wind(float speed, float direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }
}
