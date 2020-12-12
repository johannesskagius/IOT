package com.example.node_red_01.DSV;

public class Waiting_time {
    private int startPositionOfDrone;
    private int startPositionFromCall;
    private int finishPosition;

    public Waiting_time(int startPositionOfDrone, int startPositionFromCall, int finishPosition) {
        this.startPositionOfDrone = startPositionOfDrone;
        this.startPositionFromCall = startPositionFromCall;
        this.finishPosition = finishPosition;
    }

    public int getWaitingTime(){
        return startPositionOfDrone+startPositionFromCall+finishPosition;
    }
}
