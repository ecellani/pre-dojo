package br.com.amil.model;

import java.time.LocalDateTime;

public abstract class AbstractEvent<E> implements Event<E> {

    protected LocalDateTime dateTime;

    public AbstractEvent(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public abstract void addKiller(Player killer);

    public abstract void addKilled(Player killed);

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
