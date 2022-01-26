package tests;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.CapteurImpl;
import proxies.Canal;
import servant.Afficheur;
import strategy.AtomicDiffusion;

public class AtomicTests
{
	final int POOL_SIZE = 3;
	
	CapteurImpl capteur;
	
	public AtomicTests()
	{
		
	}
	
	@BeforeEach
	void setUp() 
	{
		capteur = new CapteurImpl();
	        
	       
		Afficheur a1 = new Afficheur("Test1");
		Afficheur a2 = new Afficheur("Test2");
	        
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(POOL_SIZE);
	        
		Canal c1 = new Canal(scheduler, capteur, a1);
		Canal c2 = new Canal(scheduler, capteur, a2);
	        
		capteur.attach(c1);
		capteur.attach(c2);
	        
		c1.attach(a1);
		c2.attach(a2);

	        

		AtomicDiffusion diff = new AtomicDiffusion(capteur, new Canal[]{c1, c2});	
		capteur.setAlgoDiffusion(diff);
	}
	@Test
	public void test1()
	{
		
		for (int i = 0;i <= 3; i++)
		{
			capteur.tick();
		}
	      
	}


}
