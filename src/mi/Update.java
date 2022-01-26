package mi;

import java.util.concurrent.Callable;

import proxies.Canal;
import servant.Afficheur;

public class Update implements Callable<Void> 
{

  Canal canal;
  
  Afficheur afficheur;
   
  public Update(Afficheur afficheur, Canal canal) 
  {
    this.afficheur = afficheur;
    this.canal = canal;
  }

  @Override
  public Void call() 
  {
    this.afficheur.update(this.canal);
	return null;
  }
}
