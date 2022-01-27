package servant;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.logging.Logger;

import service.CapteurAsync;

public class Afficheur implements ObserverDeCapteur<CapteurAsync> 
{
	Logger logger = Logger.getGlobal();
	
    private String name;
    private Integer value;

    public Afficheur(String name) 
    {
        this.name = name;
        this.value = 0;
    }

   
    public int getValue() 
    {
        return this.value;
    }

  
    @Override
    public void update(CapteurAsync canal) 
    {	
        try
        {
            this.value = canal.getValue().get();
           
        } 
        catch (Exception e) 
        {
        	logger.info("Impossible de récupérer la valeur du capteur a partir de l'afficheur "+this.name);
            e.printStackTrace();
        }
        finally 
        {
        	logger.info("Afficheur ("+this.name+") ---> : "+this.value);
        }
        
       
    }

}
