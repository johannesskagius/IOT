package com.example.node_red_01.DSV;

import com.example.node_red_01.RequestsAndPosition.Position;

import java.util.ArrayList;

public class Waiting_time {
    private ArrayList<Position> posArr = new ArrayList<>();
    private Position startPositionOfDrone;
    private Position callPosition;
    private Position finishPosition;

    public Waiting_time(Position startPositionOfDrone, Position startPositionFromCall, Position finishPosition) {
        this.startPositionOfDrone = startPositionOfDrone;
        this.callPosition = startPositionFromCall;
        this.finishPosition = finishPosition;
        posArr.add(startPositionOfDrone);
        posArr.add(startPositionFromCall);
        posArr.add(finishPosition);
    }

    public int getWaitingTime(){
        return startPositionOfDrone.distanceToPosition(callPosition, startPositionOfDrone)+startPositionOfDrone.distanceToPosition(startPositionOfDrone, finishPosition);
    }

}
