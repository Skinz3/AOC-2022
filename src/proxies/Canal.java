package proxies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import client.Capteur;
import client.CapteurImpl;
import mi.Update;
import servant.Afficheur;
import servant.ObserverDeCapteur;
import service.CapteurAsync;
import service.ObserverAsync;


public class Canal implements CapteurAsync,ObserverAsync<Capteur>, ObserverDeCapteur<CapteurAsync> {

  private final int MIN_DELAY = 50;
  
  private final int MAX_DELAY = 1000;
  
  private final Logger log = Logger.getGlobal();
  
  private final List<ObserverDeCapteur<CapteurAsync>> observers = new ArrayList<>();

  private ScheduledExecutorService scheduler;

  private Capteur capteur;
  
  private Afficheur afficheur;
  
  private Random Random;
  
  public Canal(ScheduledExecutorService scheduler, CapteurImpl capteur, Afficheur afficheur) 
  {
    this.scheduler = scheduler;
    this.capteur = capteur;
    this.afficheur = afficheur;
    this.Random = new Random();
  }

  @Override
  public Future<Integer> getValue() 
  {
    return scheduler.schedule(() -> this.capteur.getValue(), randomizeDelay(), TimeUnit.MILLISECONDS);
  }
  
  private int randomizeDelay()
  {
	  return (int)(Random.nextInt((MAX_DELAY - MIN_DELAY) + 1) + MAX_DELAY);
  }
  @Override
  public Future<Void> update(Capteur capteur)
  {
	  return this.scheduler.schedule(new Update(this.afficheur,this), randomizeDelay(), TimeUnit.MILLISECONDS);
  }

  public void detach(ObserverDeCapteur<CapteurAsync> observer) 
  {
      this.observers.remove(observer);
  }

  public void attach(ObserverDeCapteur<CapteurAsync> observer)
  {
      this.observers.add(observer);
  }

  @Override
  public void update(CapteurAsync c) 
  {  
	  this.afficheur.update(this);
  }
}
