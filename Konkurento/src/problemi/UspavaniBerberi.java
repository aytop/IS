package problemi;

import java.awt.Color;

import os.simulation.Action;
import os.simulation.SimulationContainer;
import os.simulation.SimulationContainerLayout;
import os.simulation.SimulationThread;
import os.simulation.gui.NoAnimationPanel;
import os.simulation.gui.SimulationFrame;
import os.simulation.gui.SimulationPanel;
import os.simulation.gui.swing.SwingSimulationPanel;

public class UspavaniBerberi {

    private Salon salon = new Salon();
    private class Salon {

        public void cekajMusteriju() {
            // TODO Sinhronizacija
        }

        public void cekajBerbera() {
            // TODO Sinhronizacija
        }
    }

    private class Berber extends SimulationThread {

        @Override
        protected void run() {
            while (!isStopRequested()) {
                salon.cekajMusteriju();
                sisa();
                waitWhileSuspended();
            }
        }

        protected void sisa() {
            setText(TEXT_SISA);
            workUninterruptibly(DUZINA_SISANJA);
            setText(TEXT_SPAVA);
        }
    }

    private class Musterija extends SimulationThread {

        @Override
        protected void run() {
            dolazi();
            salon.cekajBerbera();
            sisaSe();
            odlazi();
        }

        protected void dolazi() {
            setContainer(cekaonica);
            setText(TEXT_DOLAZI);
            workUninterruptibly(DUZINA_DOLASKA, 2 * DUZINA_DOLASKA);
            setText(TEXT_CEKA);
        }

        protected void sisaSe() {
            setContainer(stolice);
            setText(TEXT_SISA_SE);
            workUninterruptibly(DUZINA_SISANJA);
            setText("");
        }

        protected void odlazi() {
            setText(TEXT_ODLAZI);
            setColor(BOJA_BEZ_KOSE);
            setContainer(cekaonica);
            workUninterruptibly(DUZINA_DOLASKA, 2 * DUZINA_DOLASKA);
        }
    }

    // Parametri simulacije
    public static final int    BROJ_BERBERA   = 2;
    public static final int    DUZINA_DOLASKA = 2000;
    public static final int    DUZINA_SISANJA = 7000;

    // Boje
    public static final Color  BOJA_BERBER    = new Color(0xFF, 0xCC, 0xCC);
    public static final Color  BOJA_MUSTERIJA = new Color(0xCC, 0xCC, 0xFF);
    public static final Color  BOJA_BEZ_KOSE  = new Color(0xCC, 0xFF, 0xCC);
    public static final Color  BOJA_CEKAONICA = new Color(0x00, 0x00, 0x88);
    public static final Color  BOJA_SALON     = new Color(0xFF, 0xFF, 0xFF);

    // Stringovi
    public static final String TEXT_BERBER    = "\u0411\u0435\u0440\u0431\u0435\u0440";
    public static final String TEXT_MUSTERIJA = "\u041c\u0443\u0448\u0442\u0435\u0440\u0438\u0458\u0430";
    public static final String TEXT_CEKAONICA = "\u0427\u0435\u043a\u0430\u043e\u043d\u0438\u0446\u0430";
    public static final String TEXT_SALON     = "\u0421\u0430\u043b\u043e\u043d";
    public static final String TEXT_SISA      = "\u0428\u0438\u0448\u0430";
    public static final String TEXT_SPAVA     = "\u0421\u043f\u0430\u0432\u0430";
    public static final String TEXT_DOLAZI    = "\u0414\u043e\u043b\u0430\u0437\u0438";
    public static final String TEXT_ODLAZI    = "\u041e\u0434\u043b\u0430\u0437\u0438";
    public static final String TEXT_SISA_SE   = "\u0428\u0438\u0448\u0430 \u0441\u0435";
    public static final String TEXT_CEKA      = "\u0427\u0435\u043a\u0430";

    // Pomocna nit
    private class UtilThread extends SimulationThread {

        protected int br = 1;

        @Override
        protected void step() {
            novaMusterija();
            sleep(DUZINA_DOLASKA, 2 * DUZINA_DOLASKA);
            Thread.interrupted();
        }

        @Action(name = TEXT_MUSTERIJA + "++")
        public void novaMusterija() {
            br++;
            Musterija m = new Musterija();
            m.setName(TEXT_MUSTERIJA + " " + br);
            m.setColor(BOJA_MUSTERIJA);
            m.start();
        }
    }

    // Glavni program
    public static void main(String[] a) {
        new UspavaniBerberi();
    }

    // Stanja
    private SimulationContainer cekaonica = new SimulationContainer(TEXT_CEKAONICA, BOJA_CEKAONICA, SimulationContainerLayout.BOX);
    private SimulationContainer stolice = new SimulationContainer(TEXT_SALON, BOJA_SALON, SimulationContainerLayout.BOX);
    private SimulationContainer sve = new SimulationContainer(SimulationContainerLayout.COLUMN, cekaonica, stolice);

    public UspavaniBerberi() {

        // Kreiranje prozora
        SimulationPanel panel = new SwingSimulationPanel(sve);
        SimulationFrame frame = SimulationFrame.create("Uspavani berberi", panel, new NoAnimationPanel());
        frame.display();

        // Kreiranje i pokretanje niti
        for (int i = 1; i <= BROJ_BERBERA; i++) {
            Berber berber = new Berber();
            berber.setName(TEXT_BERBER + " " + i);
            berber.setText(TEXT_SPAVA);
            berber.setColor(BOJA_BERBER);
            berber.setContainer(stolice);
            berber.start();
        }
        new UtilThread().start();

    }
}
