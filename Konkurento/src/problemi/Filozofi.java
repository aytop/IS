package problemi;

import util.EventCounter;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;

import os.simulation.SimulationContainer;
import os.simulation.SimulationContainerLayout;
import os.simulation.SimulationItem;
import os.simulation.SimulationThread;
import os.simulation.gui.NoAnimationPanel;
import os.simulation.gui.SimulationFrame;
import os.simulation.gui.SimulationPanel;
import os.simulation.gui.swing.SwingSimulationPanel;

public class Filozofi {

	public enum Stanje
	{
		MISLI, JEDE, GLADAN
	};
    private Sto sto = new Sto();
    private class Sto 
    {
    	EventCounter mutex = new EventCounter();
    	AtomicInteger m = new AtomicInteger();
    	Stanje[] stanja = new Stanje[imenaFilozofa.length];
    	EventCounter[] filo = new EventCounter[imenaFilozofa.length];
    	AtomicInteger[] filoai = new AtomicInteger[imenaFilozofa.length];
    	public Sto()
    	{
    		for(int i =0;i < filo.length;i++)
    		{
    			filo[i] = new EventCounter();
    			filoai[i] = new AtomicInteger();
    			stanja[i] = Stanje.MISLI;
    		}
    	}
    	private int levo(int i)
    	{
    		return (i+filo.length-1)%filo.length;
    	}
    	private int desno(int i)
    	{
    		return (i+1)%filo.length;
    	}
    	private void test(int i)
    	{
    		for(int j =0; j< stanja.length;j++)
    		{
    			System.out.println(j+". "+stanja[j]);
    		}
    		if(stanja[i] == Stanje.GLADAN && stanja[levo(i)] != Stanje.JEDE && stanja[desno(i)]!=Stanje.JEDE)
    		{
    				stanja[i] = Stanje.JEDE;
    				filo[i].advance();
    		}
    	}
        public void uzmiViljuske(Filozof filozof) 
        {
        	mutex.awaitUninterruptibly(m.getAndIncrement());
        	stanja[filozof.getId()] = Stanje.GLADAN;
        	test(filozof.getId());
        	mutex.advance();
        	filo[filozof.getId()].awaitUninterruptibly(filoai[filozof.getId()].incrementAndGet());
        	
            // TODO Sinhronizacija
        }

        public void vratiViljuske(Filozof filozof) {
            mutex.awaitUninterruptibly(m.getAndIncrement());
            int i = filozof.getId();
            stanja[i] = Stanje.MISLI;
            test(levo(i));
            test(desno(i));
            mutex.advance();
        }
    }

    protected class Filozof extends SimulationThread {

        private int id;

        @SuppressWarnings("unused")
        private String ime;

        public Filozof(String ime, int id) {
            super(ime);
            this.id = id;
            this.ime = ime;
        }

        public int getId() {
            return id;
        }

        @Override
        protected void run() {
            while (!isStopRequested()) {
                misli();
                waitWhileSuspended();
                sto.uzmiViljuske(this);
                try {
                    jede();
                } finally {
                    sto.vratiViljuske(this);
                }
            }
        }

        protected void misli() {
            setText(TEXT_MISLI);
            setColor(BOJA_MISLI);
            work(DUZINA_MISLI_MIN, DUZINA_MISLI_MAX);
            setText(TEXT_GLADAN);
            setColor(BOJA_GLADAN);
        }

        protected void jede() {
            setText(TEXT_JEDE);
            setColor(BOJA_JEDE);
            work(DUZINA_JEDE_MIN, DUZINA_JEDE_MAX);
            setText(TEXT_JEO);
            setColor(BOJA_JEO);
        }
    }

    // Parametri simulacije
    public static final int       DUZINA_MISLI_MIN       = 1000;
    public static final int       DUZINA_MISLI_MAX       = 5000;
    public static final int       DUZINA_JEDE_MIN        = 2000;
    public static final int       DUZINA_JEDE_MAX        = 4000;

    // Boje
    public static final Color     BOJA_MISLI             = new Color(0xCC, 0xCC, 0xFF);
    public static final Color     BOJA_GLADAN            = new Color(0xFF, 0xCC, 0xCC);
    public static final Color     BOJA_JEDE              = new Color(0xCC, 0xFF, 0xCC);
    public static final Color     BOJA_JEO               = new Color(0xCC, 0xFF, 0xCC);
    public static final Color     BOJA_STO               = new Color(0xFF, 0xFF, 0xFF);
    public static final Color     BOJA_VILJUSKE_SLOBODNA = new Color(0x88, 0xFF, 0x88);
    public static final Color     BOJA_VILJUSKE_ZAUZETA  = new Color(0xFF, 0xFF, 0x88);
    public static final Color     BOJA_VILJUSKE_GRESKA   = new Color(0xFF, 0x88, 0x88);

    // Stringovi
    public static final String    TEXT_MISLI             = "\u041C\u0438\u0441\u043B\u0438";
    public static final String    TEXT_GLADAN            = "\u0413\u043B\u0430\u0434\u0430\u043D";
    public static final String    TEXT_JEDE              = "\u0408\u0435\u0434\u0435";
    public static final String    TEXT_JEO               = "\u0408\u0435\u043E";
    public static final String    TEXT_STO               = "\u0424\u0438\u043B\u043E\u0437\u043E\u0444\u0438";
    public static final String    TEXT_VILJUSKE          = "\u0412\u0438\u0459\u0443\u0448\u043A\u0435";
    public static final String    TEXT_SLOBODNO          = "\u0421\u043B\u043E\u0431\u043E\u0434\u043D\u043E: ";
    private static final String[] imenaFilozofa          = new String[] {
        "\u0410\u0440\u0438\u0441\u0442\u043E\u0442\u0435\u043B",
        "\u0414\u0438\u043E\u0433\u0435\u043D",
        "\u0415\u0443\u043A\u043B\u0438\u0434",
        "\u041F\u0438\u0442\u0430\u0433\u043E\u0440\u0430",
        "\u0422\u0430\u043B\u0435\u0441"
    };

    // Kontrolna klasa
    private class Viljuske extends SimulationItem {

        private Filozof[] filozofi;
        private int max;

        public Viljuske(int max, Filozof... filozofi) {
            super(TEXT_VILJUSKE);
            this.max = max;
            this.filozofi = filozofi;
        }

        private int broj() {
            int broj = 0;
            for (Filozof filozof : filozofi) {
                if (filozof.getText() == TEXT_JEDE) {
                    broj++;
                }
            }
            return broj;
        }

        @Override
        public Color getColor() {
            int slobodno = max - broj();
            if (slobodno > 0) {
                return BOJA_VILJUSKE_SLOBODNA;
            } else if (slobodno == 0) {
                return BOJA_VILJUSKE_ZAUZETA;
            } else {
                return BOJA_VILJUSKE_GRESKA;
            }
        }


        @Override
        public String getText() {
            return TEXT_SLOBODNO + (max - broj());
        }
    }

    // Glavni program
    public static void main(String[] arguments) {
        new Filozofi();
    }

    private SimulationContainer soba = new SimulationContainer(TEXT_STO, BOJA_STO, SimulationContainerLayout.CIRCLE);

    public Filozofi() {

        // Kreiraj prozor
        SimulationPanel panel = new SwingSimulationPanel(soba);
        SimulationFrame frame = SimulationFrame.create("Filozofi", panel, new NoAnimationPanel());
        frame.display();

        // Kreiraj sto, filozofe i viljuske
        Filozof[] filozofi = new Filozof[imenaFilozofa.length];
        for (int i = 0; i < filozofi.length; i++) {
            filozofi[i] = new Filozof(imenaFilozofa[i], i);
        }
        Viljuske[] viljuske = new Viljuske[filozofi.length];
        for (int i = 0; i < viljuske.length; i++) {
            viljuske[i] = new Viljuske(1, filozofi[i], filozofi[(i + 1) % filozofi.length]);
        }
        for (int i = 0; i < filozofi.length; i++) {
            filozofi[i].setContainer(soba);
            viljuske[i].setContainer(soba);
        }

        // Pokreni filozofe
        for (int i = 0; i < filozofi.length; i++) {
            filozofi[i].start();
        }

    }
}
