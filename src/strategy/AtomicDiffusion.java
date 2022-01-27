package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import client.CapteurImpl;
import proxies.Canal;

public class AtomicDiffusion implements AlgoDiffusion
{

  private CapteurImpl capteur;
  private Canal[] canaux;

  private final Logger log = Logger.getGlobal();

  public AtomicDiffusion(CapteurImpl capteur, Canal[] canaux) 
  {
	  this.capteur = capteur;
	  this.canaux = canaux;
  }

  @Override
  public void execute() throws InterruptedException 
  {
    List<Future<Void>> list = new ArrayList<Future<Void>>();

    for (Canal c: this.canaux) 
    {
        list.add(c.update(this.capteur));
    }
    
    while(list.stream().anyMatch(x->!x.isDone()))
    {
    	Thread.sleep(50); // avoid too much CPU usage. 
    }

  }
}
