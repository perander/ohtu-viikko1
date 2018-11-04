package src.test.java.ohtuesimerkki;


import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.java.ohtuesimerkki.Statistics;
import src.main.java.ohtuesimerkki.Reader;
import src.main.java.ohtuesimerkki.Player;

import java.util.List;
import java.util.ArrayList;


public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12)); //points 16
            players.add(new Player("Lemieux", "PIT", 45, 54)); //points 99
            players.add(new Player("Kurri",   "EDM", 37, 53)); //points 90
            players.add(new Player("Yzerman", "DET", 42, 56)); //points 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); //points 124

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void joukkueenJäsenetLöytyvätJoukkueenNimellä() {
        List<Player> teamPlayers = stats.team("EDM");
        List<Player> haetut = new ArrayList<Player>();
        haetut.add(new Player("Semenko", "EDM", 4, 12));
        haetut.add(new Player("Kurri",   "EDM", 37, 53));
        haetut.add(new Player("Gretzky", "EDM", 35, 89));

        assertEquals(teamPlayers, haetut);
    }

    @Test
    public void joukkueenJäsenLöytyyJäsenenNimellä() {
        Player player = stats.search("Semenko");
        Player haettu = new Player("Semenko", "EDM", 4, 12);

        assertEquals(player, haettu);
    }

    @Test
    public void väärälläNimelläEiLöydyKetään() {
        Player player = stats.search("Sementti");

        assertEquals(player, null);
    }

    @Test
    public void huippuPelaajatLöytyvät() {
        List<Player> topPlayers = stats.topScorers(2);
        List<Player> haetut = new ArrayList<Player>();
        haetut.add(new Player("Gretzky", "EDM", 35, 89));
        haetut.add(new Player("Lemieux", "PIT", 45, 54));

        assertEquals(topPlayers, haetut);
    }

}