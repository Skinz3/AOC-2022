package service;

import java.util.concurrent.Future;

public interface ObserverAsync<T> 
{
	Future<Void> update(T capteur);

}

