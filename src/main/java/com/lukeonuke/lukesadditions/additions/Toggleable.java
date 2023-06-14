package com.lukeonuke.lukesadditions.additions;

import lombok.Getter;

public abstract class Toggleable {
    @Getter
    private boolean isActive = false;

    public boolean toggle() {
        isActive = !isActive;
        toggleEvent();
        return isActive;
    }

    abstract public void toggleEvent();
}
