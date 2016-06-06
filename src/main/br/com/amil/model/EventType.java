package br.com.amil.model;

public enum EventType {
    START_MATCH("(?<date>^((\\d*.){5}\\d*)) - New match (?<id>\\d*) has started")
    , PLAYER_KILL("(?<date>^((\\d*.){5}\\d*)) - (?<killer>\\w*) killed (?<killed>\\w*) using (?<weapon>\\w*)")
    , WORLD_KILL("(?<date>^((\\d*.){5}\\d*)) - <WORLD> killed (?<killed>\\w*) by (\\w*)")
    , END_MATCH("(?<date>^((\\d*.){5}\\d*)) - Match (?<id>\\d*) has ended");

    private String regex;
    EventType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
