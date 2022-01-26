package mi;

import java.util.concurrent.Callable;

import client.Capteur;

public class GetValue implements Callable<Integer> 
{
  private Capteur capteur;
  
  public GetValue(Capteur c)
  {
	  this.capteur = c;
  }
  @Override
  public Integer call() 
  {
	  return capteur.getValue();
  }
}
