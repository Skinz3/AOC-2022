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

## Capteur - Client

* La fonction ```tick()`` du capteur permet de représenter l'arrivée de données. Lors de la récéption de ces données, à l'appel de cette fonction. Tout les observers sont notifiés, en l'occurence les canaux. Cependant, le traitement de ces données est delayé. En effet, chaque canal possède son propre thread qui est donc desynchronisé du thread principal ou la fonction ```tick()``` est appelée. Ainsi, le canal traitera les données periodiquement et le delay de traitement sera determiné par la fonction ```randomizeDelay()```

```java

private int randomizeDelay()
{
	  return (int)(Random.nextInt((MAX_DELAY - MIN_DELAY) + 1) + MAX_DELAY);
}
```
* 
## Canal - Proxy 

## Afficheur - Servant

## Algorithme de diffusion - Strategy

## Tests

## Conclusion
