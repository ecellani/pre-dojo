package br.com.amil.model;

import java.time.LocalDateTime;
import java.util.*;

public class Match implements Comparable<Match> {

    private Integer id;
    private Map<String, Player> players;
    private LocalDateTime start;
    private LocalDateTime end;
    private static final String ALIGN = "| %-20s | %-8s | %-8s | %-20s | %-15s | %-20s%n";

    public Match(Integer id, LocalDateTime start) {
        this.id = id;
        this.start = start;
        this.players = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Player getPlayer(String name) {
        if (!players.containsKey(name)) {
            Player player = new Player(name);
            players.put(name, player);
        }
        return players.get(name);
    }

    @Override
    public int compareTo(Match match) {
        return start.compareTo(match.getStart());
    }

    public void printMatchSummary() {
        final String line = String.format("|%-85s|%n", "-").replace(' ', '-');
        System.out.format("Match %s%n%s", id, line);
        System.out.format(ALIGN, "Player", "Kills", "Deaths", "Principal Weapon", "Best Streak", "");
        players.values()
                .stream()
                .sorted()
                .forEachOrdered(p -> {
                    String principalWeapon = p.getPrincipalWeapon();
                    System.out.format(ALIGN
                            , p.getDeaths() == 0 ? p.getName() + "*" : p.getName()
                            , p.getKills()
                            , p.getDeaths()
                            , principalWeapon == null ? "" : principalWeapon, p.getBestStreak()
                            , p.isFiveTimesAward() ? "5 deaths in 1 minute" : "");
                });
        System.out.println(line);

    }
}
