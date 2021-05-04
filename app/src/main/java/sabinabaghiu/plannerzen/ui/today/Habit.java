package sabinabaghiu.plannerzen.ui.today;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Habit {
    private String title;
    private int goal;
    private int iconId;
    private boolean isDone;
    private int count;

    public Habit(String title, int goal, int iconId, boolean isDone, int count) {
        this.title = title;
        this.goal = goal;
        this.iconId = iconId;
        this.isDone = false;
        this.count = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
