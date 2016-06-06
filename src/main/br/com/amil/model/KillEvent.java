package br.com.amil.model;

import java.time.LocalDateTime;

public class KillEvent extends AbstractEvent<KillEvent> {

    private Player killer;
    private Player killed;

    public KillEvent(LocalDateTime dateTime) {
        super(dateTime);
    }

    @Override
    public void addKiller(Player killer) {

    }

    @Override
    public void addKilled(Player killed) {

    }

    @Override
    public int compareTo(KillEvent o) {
        return dateTime.compareTo(o.getDateTime());
    }
}
