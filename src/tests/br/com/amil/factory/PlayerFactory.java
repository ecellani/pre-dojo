package br.com.amil.factory;

import br.com.amil.factory.builders.PlayerBuilder;

public class PlayerFactory {

    public static PlayerBuilder defaultPlayer() {
        return PlayerBuilder.withPlayer();
    }

    public static PlayerBuilder withFiveStreakInAMinute() {
        return PlayerBuilder.withPlayer()
                .addKill()
                .addKill()
                .addKill()
                .addKill()
                .addKill();
    }

    public static PlayerBuilder withFourStreakInAMinute() {
        return PlayerBuilder.withPlayer()
                .addKill()
                .addKill()
                .addKill()
                .addKill();
    }
}
