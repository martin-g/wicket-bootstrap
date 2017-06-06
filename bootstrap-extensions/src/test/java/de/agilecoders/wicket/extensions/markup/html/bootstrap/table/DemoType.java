package de.agilecoders.wicket.extensions.markup.html.bootstrap.table;

import java.io.Serializable;

class DemoType implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    public DemoType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}