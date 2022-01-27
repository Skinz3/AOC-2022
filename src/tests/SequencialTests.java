package tests;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.CapteurImpl;
import proxies.Canal;
import servant.Afficheur;
import strategy.SequencialDiffusion;

class SequencialTests 
{	
	final int POOL_SIZE = 3;
	
	CapteurImpl capteur;
	
	@BeforeEach
	void setUp() 
	{
		capteur = new CapteurImpl();

		Afficheur a1 = new Afficheur("monAfficheur");

		Afficheur a2 = new Afficheur("monBelAfficheur");

	    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(POOL_SIZE);
	    
	    Canal c1 = new Canal(scheduler, capteur, a1);
	    Canal c2 = new Canal(scheduler, capteur, a2);
	    c2.attach(a1);
	    c1.attach(a2);

	    capteur.attach(c2);
	    capteur.attach(c1);

	    SequencialDiffusion strategy = new SequencialDiffusion(capteur,new Canal[] {c1,c2});

	   capteur.setAlgoDiffusion(strategy);
  
	   
	}
	@Test
	public void test1() throws InterruptedException
	{
		capteur.tick();
		capteur.tick();
		
		Thread.sleep(5000);
	}
	
}
