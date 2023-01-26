package com.dinu.Main2048.Memento;

import com.dinu.Main2048.Iterator.Iterator;

import java.util.ArrayList;
import java.util.List;

public class MementoHistory {
    private List<Memento> states = new ArrayList<>();

    public void push(Memento state) {
        states.add(state);
    }
    public Memento pop() {
        var lastIndex = states.size() - 1;
        var lastState = states.get(lastIndex);
        states.remove(lastState);
        return lastState;

    }

    public int size(){
        return states.size() -1;
    }
    public Iterator createIterator() {
        return new StateIterator(this);
    }


    public class StateIterator implements Iterator{
        private MementoHistory history;
        private int index;

        public StateIterator(MementoHistory history) {
            this.history = history;
        }

        @Override
        public boolean hasNext() {
            return (index < history.states.size());
        }

        @Override
        public Memento current() {
            return history.states.get(index);
        }

        @Override
        public void next() {
            index++;
        }
    }
}
