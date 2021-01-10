package com.example.node_red_01.DSV;

import androidx.annotation.NonNull;

import com.example.node_red_01.RequestsAndPosition.Position;

import java.util.ArrayList;

public class Waiting_time {
    private final ArrayList<Position> posArr = new ArrayList<>();
    private final Position startPositionOfDrone;
    private final Position callPosition;
    private final Position finishPosition;

    public Waiting_time(Position startPositionOfDrone, Position startPositionFromCall, Position finishPosition) {
        this.startPositionOfDrone = startPositionOfDrone;
        this.callPosition = startPositionFromCall;
        this.finishPosition = finishPosition;
        posArr.add(startPositionOfDrone);
        posArr.add(startPositionFromCall);
        posArr.add(finishPosition);
    }

    /*public int getWaitingTime(){
        return startPositionOfDrone.distanceToPosition(callPosition, startPositionOfDrone)+startPositionOfDrone.distanceToPosition(startPositionOfDrone, finishPosition);
    }*/

    public int getWaitingTime(){
        return startPositionOfDrone.distanceToPosition(callPosition, startPositionOfDrone);
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(getWaitingTime());
    }
}
