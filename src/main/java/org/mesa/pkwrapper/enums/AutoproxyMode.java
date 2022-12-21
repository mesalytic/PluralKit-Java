package org.mesa.pkwrapper.enums;

public enum AutoproxyMode {
    OFF("off"),
    FRONT("front"),
    LATCH("latch"),
    MEMBER("member");

    private final String state;

    AutoproxyMode(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}