package com.dinu.Main2048.Iterator;

import com.dinu.Main2048.Memento.Memento;

public interface Iterator {
        boolean hasNext();
        Memento current();
        void next();
}


