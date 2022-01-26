package client;

import java.util.List;

import strategy.AlgoDiffusion;

public interface Capteur<T> 
{
  
 /**
 * @return Retourne la valeur courante du capteur
 */
int getValue();

 /**
 * Simule l'arivée d'une nouvelle valeur
 */
void tick();

  /**
 * @param Définit la stratégie de diffusion de la valeur du capteur
 */
void setAlgoDiffusion(AlgoDiffusion algo);

  /**
 * @param Attache un observer (canal) qui est notifié en cas
 * de changement de valeur du capteur.
 */
void attach(T object);

  /**
 * @param Detache un observer.
 */
void detach(T object);

  /**
 * Notifie tout les observer courant de la valeur actuelle du capteur
 */
void notifyAllObserver();

  /**
 * @return La liste des observers attachés a ce capteur
 */
List<T> getObservers();


}
