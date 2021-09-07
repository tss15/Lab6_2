package data;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1502L;
    private final int x;
    private final Double y;

    public Coordinates(int x, Double y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}