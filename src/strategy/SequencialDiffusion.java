package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import client.CapteurImpl;
import proxies.Canal;

public class SequencialDiffusion implements AlgoDiffusion
{
	CapteurImpl capteur;
	Canal[] canaux;
	
	// add Future<void> action list as argument
	// and check remove all from list that have been executed. (isDone())
	public SequencialDiffusion(CapteurImpl capteur, Canal[] canaux)
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
	    
	    
	    if(!list.stream().allMatch(x->x.isDone()))
	    {
	    	for (Future<Void> promise : list)
	    	{
	    		try 
	    		{
					promise.get();
				} 
	    		catch (InterruptedException e) 
	    		{
					
					e.printStackTrace();
				} catch (ExecutionException e) 
	    		{
					 
					e.printStackTrace();
				}
	    	}
           
        }
	
	
	}

}
