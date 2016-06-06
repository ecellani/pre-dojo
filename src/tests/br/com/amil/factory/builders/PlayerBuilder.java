package br.com.amil.factory.builders;

import br.com.amil.model.Player;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class PlayerBuilder {

    private Player player;

    public static final String DEFAULT_MAIN_WEAPON = "M16";
    public static final String DEFAULT_PLAYER_NAME = "Erick";

    public PlayerBuilder(Player player) {
        this.player = player;
    }

    public static PlayerBuilder withPlayer(Player player) {
        return new PlayerBuilder(player);
    }

    public static PlayerBuilder withPlayer() {
        return new PlayerBuilder(new Player(DEFAULT_PLAYER_NAME));
    }

    public PlayerBuilder addKill(final String weapon) {
        this.player.addKill(now(), weapon);
        return this;
    }

    public PlayerBuilder addKill(final LocalDateTime dateTime) {
        this.player.addKill(dateTime, DEFAULT_MAIN_WEAPON);
        return this;
    }

    public PlayerBuilder addKill() {
        this.player.addKill(now(), DEFAULT_MAIN_WEAPON);
        return this;
    }

    public PlayerBuilder addDeath() {
        this.player.addDeath();
        return this;
    }

    public Player build() {
        return this.player;
    }
}
