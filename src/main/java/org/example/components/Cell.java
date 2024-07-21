package org.example.components;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private int adjacentMineCount;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public int getAdjacentMineCount() {
        return adjacentMineCount;
    }

    public void setAdjacentMineCount(int adjacentMineCount) {
        this.adjacentMineCount = adjacentMineCount;
    }

    @Override
    public String toString() {
        if (isRevealed) {
            return isMine ? "*" : String.valueOf(adjacentMineCount);
        } else {
            return "_";
        }
    }
}
