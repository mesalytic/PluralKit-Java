package org.mesa.pkwrapper.enums;

public enum SystemPrivacyState {
    PRIVATE("private"),
    PUBLIC("public");

    private String state;

    private SystemPrivacyState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
