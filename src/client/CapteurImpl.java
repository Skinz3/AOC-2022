package client;

import java.util.ArrayList;
import java.util.List;

import service.ObserverAsync;
import strategy.AlgoDiffusion;



public class CapteurImpl implements Capteur<ObserverAsync<Capteur>>
{
    private AlgoDiffusion diffusionStrategy;
   
    private List<ObserverAsync<Capteur>> observers;
    
    private int value;
    
    public CapteurImpl() 
    {
        this.value = 0;
        this.observers = new ArrayList<ObserverAsync<Capteur>>();
    }

    @Override
    public int getValue() 
    {
        return this.value;
    }

    @Override
    public void tick() 
    {
        value++;
        notifyAllObserver();
    }

    @Override
    public void setAlgoDiffusion(AlgoDiffusion strategy) 
    {
        this.diffusionStrategy = strategy;
    }

    @Override
    public void attach(ObserverAsync<Capteur> object) 
    {
        observers.add(object);
    }

    @Override
    public void detach(ObserverAsync<Capteur> object) 
    {
        observers.remove(object);
    }

    @Override
    public void notifyAllObserver() 
    {
        diffusionStrategy.execute();
    }

    @Override
    public List<ObserverAsync<Capteur>> getObservers() 
    {
        return observers;
    }

}
