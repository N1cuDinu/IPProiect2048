package com.dinu.Main2048.Memento;

import com.dinu.Main2048.GameObject.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Memento {
    private List<GameObject> objects;

    public Memento (List<GameObject> gObjects) {
        this.objects = new ArrayList<GameObject>();
        for (GameObject item : gObjects)
            this.objects.add(item.clone());
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    @Override
    public String toString() {
        return "Memento [objects=" + objects.toString() + "]";
    }


}

