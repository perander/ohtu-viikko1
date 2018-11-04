package ohtu.ohtuvarasto;
import varasto.Varasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto negVarasto;
    Varasto varastoSaldolla;
    Varasto negTilVarastoSaldolla;
    Varasto negSaldoVarastoSaldolla;
    Varasto varastoEiTilaa;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {

        varasto = new Varasto(10);
        negVarasto = new Varasto(-1);

        varastoSaldolla = new Varasto(10, 5); //(tilavuus, saldo)

        negTilVarastoSaldolla = new Varasto(-1, 9); //tilavuus rikki
        negSaldoVarastoSaldolla = new Varasto(5, -2); //saldo rikki
        varastoEiTilaa = new Varasto(5, 10); //ei tilaa koko saldolle

    }

    //Testataan tyhjiä varastoja
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonNegTilavuusNollataan() {
        assertEquals(0, negVarasto.getTilavuus(), vertailuTarkkuus);
    }

    //Testataan varastoja, joihin voidaan asettaa alkusaldo
    @Test
    public void uudellaSaldoVarastollaOikeaTilavuus() {
        assertEquals(10, varastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenSaldoVarastonNegTilavuusNollataan() {
        assertEquals(0, negTilVarastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaSaldoVarastollaOikeaSaldo() {
        assertEquals(5, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudenSaldoVarastonNegSaldoNollataan() {
        assertEquals(0, negSaldoVarastoSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonVainNiinPaljonKuinMahtuu() {
        assertEquals(5, varastoEiTilaa.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negLisaysEiTeeMitaan() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaLisaysTayttaaVaraston() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negOttaminenEiTeeMitaan() {
        double otettu = varasto.otaVarastosta(-1);
        assertEquals(0, otettu, vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liikaOttaminenPalauttaaKokoSaldon() {
        double saatiin = varastoSaldolla.otaVarastosta(6);
        assertEquals(5, saatiin, vertailuTarkkuus);
    }

    @Test
    public void liikaOttaminenNollaaSaldon() {
        varastoSaldolla.otaVarastosta(6);
        assertEquals(0, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeanlaisenMerkkijonon() {
        String halutaan = "saldo = 5.0, vielä tilaa 5.0";
        String tulee = varastoSaldolla.toString();


        System.out.println(halutaan + ", " + tulee);
        assertEquals(halutaan, tulee);
    }

}