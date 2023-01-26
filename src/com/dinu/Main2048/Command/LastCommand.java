package com.dinu.Main2048.Command;

import com.dinu.Main2048.Memento.MementoHistory;
import com.dinu.Main2048.game.Game;

public class LastCommand implements Command{
    MementoHistory history = new MementoHistory();
    public void getHistory(MementoHistory memento){
        history = memento;
    }
    @Override
    public void execute() {
        if(history.size() >= 0) {
            Game.restore(history.pop());
        }
    }
}
