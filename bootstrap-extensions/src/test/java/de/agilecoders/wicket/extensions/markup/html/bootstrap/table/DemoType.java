package de.agilecoders.wicket.extensions.markup.html.bootstrap.table;

import java.io.Serializable;

class DemoType implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    DemoType(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
