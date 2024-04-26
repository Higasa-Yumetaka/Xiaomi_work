package com.example.work_liuchangxu.work_0425;

public class MyEvent {
    public final boolean stared;
    public final int position;

    public MyEvent(int position, boolean stared) {
        this.position = position;
        this.stared = stared;
    }

    public boolean isStared() {
        return stared;
    }

    public int getPosition() {
        return position;
    }


}
