package br.com.amil.model;

import br.com.amil.factory.PlayerFactory;
import br.com.amil.factory.builders.PlayerBuilder;
import org.junit.Test;

import static java.time.temporal.ChronoUnit.MINUTES;

import static br.com.amil.factory.builders.PlayerBuilder.DEFAULT_MAIN_WEAPON;
import static java.time.LocalDateTime.now;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testAddKill() throws Exception {
        final Player player = PlayerFactory.defaultPlayer()
                .addKill()
                .build();
        assertEquals(1, (int) player.getKills());
    }

    @Test
    public void testAddDeath() throws Exception {
        final Player player = PlayerFactory.defaultPlayer()
                .addDeath()
                .build();
        assertEquals(1, (int) player.getDeaths());
    }

    @Test
    public void testGetPrincipalWeapon() throws Exception {
        final Player player = PlayerFactory.defaultPlayer()
                .addKill(DEFAULT_MAIN_WEAPON)
                .addKill(DEFAULT_MAIN_WEAPON)
                .addKill("Barret .50")
                .build();
        assertEquals(DEFAULT_MAIN_WEAPON, player.getPrincipalWeapon());
    }

    @Test
    public void testCalculateStreakWithFiveStreakInAMinute() throws Exception {
        final Player player = PlayerFactory.withFiveStreakInAMinute().build();
        assertEquals(5, (int) player.getKills());
        assertTrue(player.isFiveTimesAward());
        assertEquals(5, (int) player.getBestStreak());
    }

    @Test
    public void testCalculateBestStreakWithOneDeath() throws Exception {
        final Player player = PlayerBuilder.withPlayer(PlayerFactory.withFiveStreakInAMinute().build())
                .addDeath()
                .addKill()
                .build();
        // BestStreak foi interrompido com uma morte, então o resultado é de
        // 6 assassinatos, 1 morte e melhor streak 5.
        assertEquals(5, (int) player.getBestStreak());
        assertEquals(6, (int) player.getKills());
        assertEquals(1, (int) player.getDeaths());
    }

    @Test
    public void testCalculateStreakLoosingFiveStreakAward() throws Exception {
        final Player player = PlayerBuilder.withPlayer(PlayerFactory.withFourStreakInAMinute().build())
                .addKill(now().plus(1L, MINUTES))
                .build();
        assertFalse(player.isFiveTimesAward());
        assertEquals(5, (int) player.getKills());
    }
}