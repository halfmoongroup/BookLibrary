package com.hmg.demo.app.booklibrary.model;

import com.hmg.demo.app.booklibrary.model.auto._Datamap;

public class Datamap extends _Datamap {

    private static Datamap instance;

    private Datamap() {}

    public static Datamap getInstance() {
        if(instance == null) {
            instance = new Datamap();
        }

        return instance;
    }
}
