package data;

import java.util.Stack;

public class EFechoIndex {
    String state;
    Integer position;
    Stack<String> currentStack;

    public EFechoIndex(String state, Integer position) {
        this.state = state;
        this.position = position;
    }

    public String getState() {
        return state;
    }

    public Integer getPosition() {
        return position;
    }

    public Stack<String> getCurrentStack() {
        return currentStack;
    }
}
