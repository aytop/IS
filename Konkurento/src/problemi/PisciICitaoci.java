package problemi;

import java.util.concurrent.atomic.*;
import util.EventCounter;
import java.awt.Color;

import os.simulation.SimulationContainer;
import os.simulation.SimulationContainerLayout;
import os.simulation.SimulationThread;
import os.simulation.gui.NoAnimationPanel;
import os.simulation.gui.SimulationFrame;
import os.simulation.gui.SimulationPanel;
import os.simulation.gui.swing.SwingSimulationPanel;

public class PisciICitaoci 
{

    private Baza baza = new Baza();
    private class Baza 
    {
    	int brojC = 0;
    	AtomicInteger mutex = new AtomicInteger(0);
    	EventCounter ec = new EventCounter();
    	
        public void ulaz(boolean ekskluzivan) throws InterruptedException 
        {
            if(ekskluzivan)
            	ec.await(mutex.getAndIncrement());
            else 
            {
            	if(brojC == 0)
            		ec.await(mutex.getAndIncrement());
            	brojC++;
            }
           
        }

        public void ulazUninterruptibly(boolean ekskluzivan) 
        {
        	if(ekskluzivan)
            	ec.awaitUninterruptibly(mutex.getAndIncrement());
            else 
            {
            	if(brojC == 0)
            		ec.awaitUninterruptibly(mutex.getAndIncrement());
            	brojC++;
            }
        }

        public void izlaz(boolean ekskluzivan) 
        {
           if(ekskluzivan)
        	   ec.advance();
           else 
           {
        	   brojC--;
        	   if(brojC == 0)
        		   ec.advance();
           }
        }
    }

    private class Pisac extends SimulationThread 
    {

        @Override
        protected void run() 
        {
            while (!isStopRequested()) 
            {
                step();
                waitWhileSuspended();
            }
        }

        @Override
        // Prekidiva verzija
        protected void step() 
        {
            radiNestoDrugo();
            try 
            {
                baza.ulaz(true);
                try 
                {
                    pise();
                } finally 
                {
                    baza.izlaz(true);
                }
            } 
            catch (InterruptedException e) 
            {
                stopGracefully();
            }
        }

        // Neprekidiva verzija
        @SuppressWarnings("unused")
        protected void stepUninterruptibly() 
        {
            radiNestoDrugo();
            baza.ulazUninterruptibly(true);
            pise();
            baza.izlaz(true);
            stopIfInterrupted();
        }

        protected void pise() 
        {
            setContainer(unutra);
            setText(TEXT_PISE);
            work(DUZINA_PISANJA, 2 * DUZINA_PISANJA);
            setText(TEXT_UPISAO);
        }

        protected void radiNestoDrugo() 
        {
            setContainer(vanPisci);
            setText(TEXT_RADI_NESTO_DRUGO);
            work(DUZINA_NE_PISANJA, 2 * DUZINA_NE_PISANJA);
            setText(TEXT_ZAVRSIO_NESTO_DRUGO);
        }
    }

    private class Citalac extends SimulationThread 
    {

        @Override
        protected void run() 
        {
            while (!isStopRequested()) 
            {
                step();
                waitWhileSuspended();
            }
        }

        @Override
        // Prekidiva verzija
        protected void step() 
        {
            radiNestoDrugo();
            try 
            {
                baza.ulaz(false);
                try 
                {
                    cita();
                } finally {
                    baza.izlaz(false);
                }
            } catch (InterruptedException e) 
            {
                stopGracefully();
            }
        }

        // Neprekidiva verzija
        @SuppressWarnings("unused")
        protected void stepUninterruptibly() 
        {
            radiNestoDrugo();
            baza.ulazUninterruptibly(false);
            try
            {
                cita();
            }
            finally {
                baza.izlaz(false);
            }
            stopIfInterrupted();
        }

        protected void cita() 
        {
            setContainer(unutra);
            setText(TEXT_CITA);
            work(DUZINA_CITANJA, 2 * DUZINA_CITANJA);
            setText(TEXT_PROCITAO);
        }

        protected void radiNestoDrugo() 
        {
            setContainer(vanCitaoci);
            setText(TEXT_RADI_NESTO_DRUGO);
            work(DUZINA_NE_CITANJA, 2 * DUZINA_NE_CITANJA);
            setText(TEXT_ZAVRSIO_NESTO_DRUGO);
        }
    }

    // Parametri simulacije
    public static final int    BROJ_PISACA       = 2;
    public static final int    BROJ_CITALACA     = 5;
    public static final int    DUZINA_PISANJA    = 5000;
    public static final int    DUZINA_CITANJA    = 3000;
    public static final int    DUZINA_NE_PISANJA = 4000;
    public static final int    DUZINA_NE_CITANJA = 4000;

    // Boje
    public static final Color  BOJA_PISACA      = new Color(0xFF, 0xCC, 0xCC);
    public static final Color  BOJA_CITALACA    = new Color(0xCC, 0xCC, 0xFF);
    public static final Color  BOJA_VAN_PISCI   = new Color(0x88, 0x00, 0x00);
    public static final Color  BOJA_VAN_CITAOCI = new Color(0x00, 0x00, 0x88);
    public static final Color  BOJA_UNUTRA      = new Color(0xFF, 0xFF, 0xFF);

    // Stringovi
    public static final String TEXT_PISAC               = "\u041F\u0438\u0441\u0430\u0446";
    public static final String TEXT_CITALAC             = "\u0427\u0438\u0442\u0430\u043B\u0430\u0446";
    public static final String TEXT_VAN_PISCI           = "\u041F\u0438\u0441\u0446\u0438";
    public static final String TEXT_VAN_CITAOCI         = "\u0427\u0438\u0442\u0430\u043E\u0446\u0438";
    public static final String TEXT_UNUTRA              = "\u0411\u0430\u0437\u0430";
    public static final String TEXT_PISE                = "\u041F\u0438\u0448\u0435";
    public static final String TEXT_UPISAO              = "\u0423\u043F\u0438\u0441\u0430\u043E";
    public static final String TEXT_CITA                = "\u0427\u0438\u0442\u0430";
    public static final String TEXT_PROCITAO            = "\u041F\u0440\u043E\u0447\u0438\u0442\u0430\u043E";
    public static final String TEXT_RADI_NESTO_DRUGO    = "\u0420\u0430\u0434\u0438";
    public static final String TEXT_ZAVRSIO_NESTO_DRUGO = "\u0417\u0430\u0432\u0440\u0448\u0438\u043E";

    // Glavni program
    public static void main(String[] a) {
        new PisciICitaoci();
    }

    // Stanja
    private SimulationContainer vanPisci = new SimulationContainer(TEXT_VAN_PISCI, BOJA_VAN_PISCI, SimulationContainerLayout.BOX);
    private SimulationContainer vanCitaoci = new SimulationContainer(TEXT_VAN_CITAOCI, BOJA_VAN_CITAOCI, SimulationContainerLayout.BOX);
    private SimulationContainer unutra = new SimulationContainer(TEXT_UNUTRA, BOJA_UNUTRA, SimulationContainerLayout.BOX);
    private SimulationContainer van = new SimulationContainer(SimulationContainerLayout.ROW, vanPisci, vanCitaoci);
    private SimulationContainer sve = new SimulationContainer(SimulationContainerLayout.COLUMN, van, unutra);

    public PisciICitaoci() {

        // Create frame
        SimulationPanel panel = new SwingSimulationPanel(sve);
        SimulationFrame frame = SimulationFrame.create("Pisci i citaoci", panel, new NoAnimationPanel());
        frame.display();

        // Create threads
        for (int i = 1; i <= BROJ_PISACA; i++) {
            Pisac pisac = new Pisac();
            pisac.setName(TEXT_PISAC + " " + i);
            pisac.setColor(BOJA_PISACA);
            pisac.start();
        }
        for (int i = 1; i <= BROJ_CITALACA; i++) {
            Citalac citalac = new Citalac();
            citalac.setName(TEXT_CITALAC + " " + i);
            citalac.setColor(BOJA_CITALACA);
            citalac.start();
        }

    }
}
