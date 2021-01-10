package com.example.node_red_01.RequestsAndPosition;

public class Position {
    private final int X;
    private final int Y;

    public Position(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

     public int distanceToPosition(Position start, Position compare1){
        return start.getX()-compare1.getX()+start.getY()-compare1.getY();
     }
}
