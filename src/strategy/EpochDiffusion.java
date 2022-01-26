package strategy;

import java.util.concurrent.ExecutionException;

import client.CapteurImpl;
import proxies.Canal;

public class EpochDiffusion implements AlgoDiffusion {

	CapteurImpl capteur;
	Canal[] canaux;
	
	public EpochDiffusion(CapteurImpl capteur, Canal[] canaux)
	{
		this.canaux = canaux;
		this.capteur = capteur;
	}
	
	@Override
	public void execute() 
	{
		for (Canal c : canaux)	
		{
			try 
			{
				c.update(capteur).get(); // no order, chaotic behaviors
			} 
			catch (InterruptedException | ExecutionException e) 
			{
				
				e.printStackTrace();
			}
		}
		
		
	}

}
