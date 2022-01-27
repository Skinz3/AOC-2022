package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
		List<Future<Void>> list = new ArrayList<Future<Void>>();

	    for (Canal c: this.canaux) 
	    {
	        list.add(c.update(this.capteur));
	    }  

	}

}
