package org.example.components;

public abstract class UserAction {
    private final int row;
    private final int column;

    public UserAction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
