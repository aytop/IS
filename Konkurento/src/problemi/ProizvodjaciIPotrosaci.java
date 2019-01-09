package problemi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import os.simulation.SimulationContainer;
import os.simulation.SimulationContainerLayout;
import os.simulation.SimulationItem;
import os.simulation.SimulationThread;
import os.simulation.gui.NoAnimationPanel;
import os.simulation.gui.SimulationFrame;
import os.simulation.gui.SimulationPanel;
import os.simulation.gui.swing.SwingSimulationPanel;

public class ProizvodjaciIPotrosaci {

    private Bafer bafer = new Bafer();
    private class Bafer {

        private List<Element> lista = new ArrayList<>();

        public void stavi(Element o) {
            // TODO Sinhronizacija
            lista.add(o);
            elementi.addItem(o);
            // TODO Sinhronizacija
        }

        public Element uzmi() {
            // TODO Sinhronizacija
            Element result = lista.remove(0);
            elementi.removeItem(result);
            // TODO Sinhronizacija
            return result;
        }
    }

    private class Proizvodjac extends SimulationThread {

        private int id;
        private int br;

        public Proizvodjac(int id) {
            this.id = id;
            this.br = 0;
        }

        @Override
        protected void run() {
            while (!isStopRequested()) {
                Element o = proizvedi();
                bafer.stavi(o);
                waitWhileSuspended();
            }
        }

        protected Element proizvedi() {
            setText(TEXT_PROIZVODI);
            workUninterruptibly(DUZINA_PROIZVODNJE, 2 * DUZINA_PROIZVODNJE);
            setText(TEXT_CEKA);
            return new Element(id + "x" + ++br);
        }
    }

    private class Potrosac extends SimulationThread {

        @SuppressWarnings("unused")
        private int id;

        public Potrosac(int id) {
            this.id = id;
        }

        @Override
        protected void run() {
            while (!isStopRequested()) {
                Element o = bafer.uzmi();
                trosi(o);
                waitWhileSuspended();
            }
        }

        protected void trosi(Element o) {
            setText(TEXT_TROSI + " " + o);
            workUninterruptibly(DUZINA_POTROSNJE, 2 * DUZINA_POTROSNJE);
            setText(TEXT_CEKA);
        }
    }

    // Parametri simulacije
    public static final int    VELICINA_BAFERA    = 12;
    public static final int    BROJ_PROIZVODJACA  = 4;
    public static final int    BROJ_POTROSACA     = 4;
    public static final int    DUZINA_PROIZVODNJE = 2000;
    public static final int    DUZINA_POTROSNJE   = 5000;

    // Boje
    public static final Color  BOJA_PROIZVODJAC  = new Color(0xCC, 0xCC, 0xFF);
    public static final Color  BOJA_POTROSAC     = new Color(0xFF, 0xCC, 0xCC);
    public static final Color  BOJA_PROIZVODJACI = new Color(0x00, 0x00, 0x88);
    public static final Color  BOJA_POTROSACI    = new Color(0x88, 0x00, 0x00);
    public static final Color  BOJA_BAFER        = new Color(0xFF, 0xFF, 0xFF);
    public static final Color  BOJA_ELEMENT_OK   = new Color(0xFF, 0xFF, 0x88);
    public static final Color  BOJA_ELEMENT      = new Color(0xFF, 0x88, 0x88);

    // Stringovi
    public static final String TEXT_PROIZVODJAC  = "\u041F\u0440\u043E\u0438\u0437\u0432.";
    public static final String TEXT_POTROSAC     = "\u041F\u043E\u0442\u0440\u043E\u0448\u0430\u0447";
    public static final String TEXT_PROIZVODJACI = "\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0452\u0430\u0447\u0438";
    public static final String TEXT_POTROSACI    = "\u041F\u043E\u0442\u0440\u043E\u0448\u0430\u0447\u0438";
    public static final String TEXT_BAFER        = "\u0411\u0430\u0444\u0435\u0440";
    public static final String TEXT_PROIZVODI    = "\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438";
    public static final String TEXT_TROSI        = "\u0422\u0440\u043E\u0448\u0438";
    public static final String TEXT_CEKA         = "\u0427\u0435\u043A\u0430";

    // Elementi bafera
    private class Element extends SimulationItem {

        public Element(String value) {
            super(value, BOJA_ELEMENT);
        }

        private int getIndex() {
            SimulationItem[] svi = elementi.listItems();
            for (int i = 0; i < svi.length; i++) {
                if (svi[i] == this) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public Color getColor() {
            int index = getIndex(); 
            if ((index >= 0) && (index < VELICINA_BAFERA)) {
                return BOJA_ELEMENT_OK;
            } else {
                return BOJA_ELEMENT;
            }
        }

        @Override
        public String getText() {
            return TEXT_BAFER + "[" + getIndex() + "]";
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    // Glavni program
    public static void main(String[] a) {
        new ProizvodjaciIPotrosaci();
    }

    // Stanja
    private SimulationContainer proizvodjaci = new SimulationContainer(TEXT_PROIZVODJACI, BOJA_PROIZVODJACI, SimulationContainerLayout.BOX);
    private SimulationContainer potrosaci = new SimulationContainer(TEXT_POTROSACI, BOJA_POTROSACI, SimulationContainerLayout.BOX);
    private SimulationContainer elementi = new SimulationContainer(TEXT_BAFER, BOJA_BAFER, SimulationContainerLayout.BOX);
    private SimulationContainer sve = new SimulationContainer(SimulationContainerLayout.ROW, proizvodjaci, elementi, potrosaci);

    public ProizvodjaciIPotrosaci() {

        // Kreiranje prozora
        SimulationPanel panel = new SwingSimulationPanel(sve);
        SimulationFrame frame = SimulationFrame.create("Proizvodjaci i potrosaci", panel, new NoAnimationPanel());
        frame.display();

        // Kreiranje i pokretanje niti
        for (int i = 1; i <= BROJ_PROIZVODJACA; i++) {
            Proizvodjac proizvodjac = new Proizvodjac(i);
            proizvodjac.setName(TEXT_PROIZVODJAC + " " + i);
            proizvodjac.setText(TEXT_CEKA);
            proizvodjac.setColor(BOJA_PROIZVODJAC);
            proizvodjac.setContainer(proizvodjaci);
            proizvodjac.start();
        }
        for (int i = 1; i <= BROJ_POTROSACA; i++) {
            new Potrosac(i).start();
            Potrosac potrosac = new Potrosac(i);
            potrosac.setName(TEXT_POTROSAC + " " + i);
            potrosac.setText(TEXT_CEKA);
            potrosac.setColor(BOJA_POTROSAC);
            potrosac.setContainer(potrosaci);
            potrosac.start();
        }

    }
}
