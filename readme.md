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

## Canal - Proxy 

## Afficheur - Servant

## Algorithme de diffusion - Strategy

## Tests

## Conclusion
