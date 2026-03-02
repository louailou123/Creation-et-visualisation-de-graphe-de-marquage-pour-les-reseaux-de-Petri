# Création et Visualisation de Graphe de Marquage pour les Réseaux de Petri

Projet Java pour la **génération et la visualisation des graphes de marquage d’un réseau de Petri**, permettant d’analyser l’évolution des états du système et de détecter des propriétés dynamiques telles que l’accessibilité des marquages et les chemins entre eux.

---

## Description

Un **réseau de Petri** est un modèle formel utilisé pour représenter le comportement dynamique de systèmes concurrents et distribués. Le **graphe de marquage** associé à un réseau de Petri affiche tous les marquages accessibles à partir d’un état initial, ainsi que les transitions possibles entre eux.

Ce projet permet de :
- Lire un réseau de Petri (places, transitions, arcs et marquage initial),
- Construire le graphe de marquage en explorant tous les états accessibles,
- Visualiser ce graphe sous forme de diagramme (ex. PNG ou autre format graphique),
- Analyser l’évolution du système et ses propriétés dynamiques.

---

## Structure du projet
- ├── README.md
- ├── TVF/
- │ └── ... (code source Java)
- ├── graphe.dot
- ├── graphe.png
- └── .vscode/

---

## ⚙️ Installation

1. **Cloner le dépôt**
```bash
git clone https://github.com/louailou123/Creation-et-visualisation-de-graphe-de-marquage-pour-les-reseaux-de-Petri.git
cd Creation-et-visualisation-de-graphe-de-marquage-pour-les-reseaux-de-Petri
mvn clean install
mvn exec:java -Dexec.mainClass="petriGraphe.App"
