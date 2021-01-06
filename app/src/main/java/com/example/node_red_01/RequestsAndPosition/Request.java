package com.example.node_red_01.RequestsAndPosition;

public class Request {
    private final Position startPos;
    private final Position finishPos;

    public Request(Position startPos, Position finishPos) {
        this.startPos = startPos;
        this.finishPos = finishPos;
    }
}
