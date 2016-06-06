package br.com.amil.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;

public class Player implements Comparable<Player> {

    private final String name;
    private Integer kills;
    private Integer deaths;
    private Integer streak;
    private Integer bestStreak;
    private Integer streakTime;
    private final Map<String, Integer> weaponsMapper;
    private LocalDateTime streakTimeControl;
    private boolean fiveTimesAward;

    public Player(final String name) {
        this.name = name;
        this.kills = 0;
        this.deaths = 0;
        this.streak = 0;
        this.bestStreak = 0;
        this.streakTime = 0;
        this.fiveTimesAward = false;
        this.weaponsMapper = new HashMap<>();
    }

    public Player addKill(final LocalDateTime dateTime, final String weapon) {
        kills++;
        useWeapon(weapon);
        calculateStreak(dateTime);
        return this;
    }

    public Player addDeath() {
        deaths++;
        streak = 0;
        return this;
    }

    public String getPrincipalWeapon() {
        if (weaponsMapper.isEmpty()) return null;
        return weaponsMapper.entrySet()
                .stream()
                .max(comparingByValue())
                .get()
                .getKey();
    }

    private void useWeapon(final String weapon) {
        Integer usages = 1;
        if (weaponsMapper.containsKey(weapon)) {
            usages = weaponsMapper.get(weapon) + 1;
        }
        weaponsMapper.put(weapon, usages);
    }

    private void calculateStreak(final LocalDateTime dateTime) {
        streak++;
        if (bestStreak < streak) {
            bestStreak = streak;
        }
        if (streakTimeControl != null
                && dateTime != null
                && Duration.between(streakTimeControl, dateTime).toMinutes() < 1L) {
            streakTime++;
            if (streakTime >= 5) {
                fiveTimesAward = true;
            }
        } else {
            streakTime = 1;
            streakTimeControl = dateTime;
        }
    }

    public Integer getKills() {
        return kills;
    }

    public String getName() {
        return name;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getBestStreak() {
        return bestStreak;
    }

    public boolean isFiveTimesAward() {
        return fiveTimesAward;
    }

    @Override
    public int compareTo(Player o) {
        return o.getKills().compareTo(kills);
    }
}
