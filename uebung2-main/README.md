# Allgemein

Dieses Projekt dient als Basis fuer die weiteren Projekte.
In diesem Projekt sind die Qualitaetssicherungstools
- Jacoco
- Pitest

bereits eingebunden, sodass diese nicht extra eingebunden werden muessen.

# Qualitaetssicherung mit Maven 

Mittels dem Befehle 
```./mvnw clean verify```
werden die Tests automatisch gestartet.
Es werden die 
- Unit-Tests
- Integrations-Tests
- Mutations-Tests

ausgefuehrt, falls ein Test in irgendeiner Kategorie fehlschlaegt entsteht ein Fehler.

# Beispiel-Projekt

Ein Beispielprojekt mit einem erweiterten "Hello-World"-Programm ist unter diesem Link zu finden: https://gitlab.com/R4VENN0/example-base-project