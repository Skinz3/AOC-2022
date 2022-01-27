# Projet AOC 2022


## Introduction
 
> Le but de projet était de réaliser une simulation d'un service de diffusion des données provenant de capteurs.
 
* Afin de répondre a ce problème, nous utiliseront le patron de conception Active Object qui permet de découpler l'invocation de méthodes de l'objet qui en est propriétaire (en l'occurence, les capteurs).

* Nous travaillons donc dans un contexte asynchrone, ou chaque donnée d'un capteur sera récupérée et affichée de manière parallèle. Les différentes méthodes de diffusion permettent d'ordonner la manière dont les données seront gérés. La valeur des capteur sera modifiée de manière periodique, à intervalerégulier. Leur diffusion sera effectuée sur un Canal (thread) différent ce cannal joue le rôle de proxy, de manière cyclique également mais leur délai de transmission sera défini de manière aléatoire.

Voici donc les différent composants de notre application : 

* Le capteur
* Le canal
* L'Algorithme de diffusion
* L'afficheur

## Detail chronologique

* La fonction ```tick()``` du capteur permet de représenter l'arrivée de données. Lors de la récéption de ces données, à l'appel de cette fonction. Tout les observers sont notifiés, en l'occurence les canaux. Cependant, le traitement de ces données est delayé. En effet, chaque canal possède son propre thread qui est donc desynchronisé du thread principal ou la fonction ```tick()``` est appelée. Ainsi, le canal traitera les données periodiquement et le delay de traitement sera determiné par la fonction ```randomizeDelay()```.

```java

private int randomizeDelay()
{
	return (int)(Random.nextInt((MAX_DELAY - MIN_DELAY) + 1) + MAX_DELAY);
}
```

* L'implémentation du concept "d'opération delayée" sera représenté par Future<T> en java. Cette classe permet de fournir une promesse qu'un resultat de type T sera retourné a la suite du calcul de l'opération. 

* L'outil permettant de prevoir des taches delayés et ```ScheduledExecutorService```. Il est propre a chaque canal, et prend en paramètre un nombre de thread maximal que celui-ci peut allouer pour traiter ces taches.

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(POOL_SIZE);
```
	
* Dans ce projet, deux taches sont delayés : 
	1. ```update()``` :  qui permet d'afficher la valeur que le cannal connait a un moment t.
	2. ```getValue()``` : qui retourne la valeur du capteur.

* L'ordre des opérations, et notemment de l'appel des methodes update() et getValue() est gouverné par la stratégie de diffusion. Il existe trois stratégie de diffusion différentes, voir ci dessous.

## Algorithme de diffusion - Strategy
	
### Diffusion atomique
	
* Dans la diffusion atomique, l'ordre dans lequel les capteur transmettent les valeur doit être pris en compte. De plus, l'algorithme de diffusion doit attendre la fin de la transmission des valeurs du capteur avant de notifier l'afficheur. Nous avons implémenté l'attente de cette manière : 
	
```java 
while(list.stream().anyMatch(x->!x.isDone()))
{
	Thread.sleep(50); // avoid too much CPU usage. 
}
```
	
``` Future<T>.isDone() ``` permet de vérifier qu'une methode est bien été invoquée et n'est plus en attente. Nous avons également remarqué que la consomation CPU était particulièrement élevée car la condition de la boucle ``` list.stream().anyMatch(x->!x.isDone() ``` est appelée trop souvent. Nous avons donc réduit la quantité de calcul en faisant patienter le thread 50ms entre chaque appel.

## Tests

* Il existe plusieurs manière de tester notre programme. Nous n'avions pas d'IHM, nous sommes donc parti sur des test unitaire a l'aide de JUnit. Le cas de tests fonctionnent tous selon le même principe : 
	
	* Tout les composants sont instanciés au démmarage du test (@BeforeEach). Capteurs, Afficheur, Canaux et le Scheduler.
	* Sur le thread principal (thread appellant la fonction de test). On appelle la fonction tick() du capteur et on observe les resulats des afficheurs, en 	     fonction de la stragégie de diffusion choisie.

## Conclusion
	
En résumé, ce projet nous a permit de clarifier notre comphrénsion du parallelisme et des outils que nous proposait java pour implémenter ces concepts.
(Future pour gérer les promesses, Scheduler
