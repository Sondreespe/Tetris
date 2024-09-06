[forrige](./06-droppebrikke.md) &bullet; [oversikt](../README.md#steg-for-steg) &bullet; [neste](./08-timer.md)

# 7 Fjern fulle rekker

Når du er ferdig med dette kapittelet, skal rekker fjernes når vi dropper en brikke slik at en rekke blir full.

[<img src="./pics/clearRows.gif"  width="250">](./pics/clearRows.gif)


Mesteparten av jobben her foregår i klassen `TetrisBoard`. Forslag til nyttige hjelpemetoder du bør implementere:
- En metode som sjekker om et element eksisterer i en rad på brettet.
- En metode som setter alle rutene i en rad til en gitt verdi.
- En metode som kopierer alle verdiene fra én rad inn i en annen. 
> Det kan være fristende å skule til implementasjonsdetaljene i Grid, og trikse til operasjonen med å kopiere en rekke inn i en annen ved å flytte på hele rader om gangen. Dette kan være effektivt og besparende for prosessoren, men kan også føre til alvorlige bugs hvor den samme raden opptrer flere steder samtidig. Det tryggeste er å gjøre dette på den "dumme" måten, og rett og slett kopiere element for element i en for-løkke.

Hovedmetoden vår vil være en metode som fjerner alle fulle rader fra brettet, og returnerer hvor mange rader som ble fjernet. En måte å løse dette på dersom brettet var fysisk, og vi var to personer, kunne vært noe slikt:
- Person `a` har som jobb å *skrive verdier inn i en rad*. Denne personen skal skrive inn verdier i hver eneste rad på brettet.
- Person `b` har som jobb å *velge hvilke rader som skal tas vare på*. Denne personen kan velge å hoppe over en rad som skal forkastes (fjernes fra brettet). Når person `b` har funnet en rad som skal tas vare på, må han vente til person `a` har skrevet ned disse verdiene før han fortsetter.

Begge personene starter ved å stå ved nederste rad på brettet, og gjentar følgende prosedyre:
- Så lenge person `b` står ved en rad som er full: `b` flytter seg til neste rad og teller samtidig at han har forkastet en rad (nemlig den fulle raden).
- Når `b` har stoppet, er han ved en rad som skal taes vare på. Innholdet i raden `b` nå står ved kopieres inn i raden `a` står ved.
- Både person `a` og person `b` går så opp én rad.

Mønsteret gjentar seg helt til person `b` har forlatt brettet. Så fylles resten av radene med tomme ruter.

Scenarioet over kan vi programmere som en løkke hvor `a` og `b` er variabler som refererer til rader, og vi benytter oss av hjelpemetodene nevnt over. I pseudokode blir det noe slikt som:

```java
// Opprett tellevariabel for å telle hvor mange rader som blir forkastet
// La a og b være variabler, begge med verdien 'antall rader - 1' (nederste rad på brettet)
// Så lenge a er en rad på brettet (er større enn eller lik 0):
//   Så lenge b er en rad på brettet og raden b ikke inneholder en blank rute:
//     tell opp at denne rekken ble forkastet
//     la b gå til neste rad (altså minus 1 på b)
//   Hvis b fremdeles er på brettet:
//     kopier rekken b står ved inn i rekken a står ved
//   ellers:
//     fyll rekken a står ved med blanke ruter.
//   La både a og b gå til neste rekke
// Returner antall rekker som ble forkastet
```
Pass på at du ikke prøver å gjøre ting med en rekke som ikke er på brettet, da kan du få `IndexOutOfBoundsException` eller noe tilsvarende.

**Testing**

Fjerning av rekker er spesielt viktig å teste godt med automatiske tester, siden dette tar svært lang tid å teste manuelt.


* Legg til følgende test i `TestTetrisBoard`:

```java
@Test
public void testRemoveFullRows() {
  // Tester at fulle rader fjernes i brettet:
  // -T
  // TT
  // LT
  // L-
  // LL

  TetrisBoard board = new TetrisBoard(5, 2);
  board.set(new CellPosition(0, 1), 'T');
  board.set(new CellPosition(1, 0), 'T');
  board.set(new CellPosition(1, 1), 'T');
  board.set(new CellPosition(2, 1), 'T');
  board.set(new CellPosition(4, 0), 'L');
  board.set(new CellPosition(4, 1), 'L');
  board.set(new CellPosition(3, 0), 'L');
  board.set(new CellPosition(2, 0), 'L');

  assertEquals(3, board.removeFullRows());

  String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "-T",
    "L-"
  });
  assertEquals(expected, board.prettyString());
}
```

* Skriv også dine egne tester for fjerning av rekker:
  * En test der nederste rad må beholdes
  * En test der øverste rad skal fjernes
  * En test med en annen bredde på brettet

Det kan være lurt å opprette en hjelpemetode *getTetrisBoardWithContents* som kan omgjøre en String[] til et TetrisBoard. Da blir det lettere å lese testene, og testene i seg selv blir mindre utsatt for feil fordi vi ikke trenger kommentarer (det er forvirrende og en kilde til feil hvis man har gamle kommentarer liggende som ikke matcher koden). Testen over kan da se slik ut i stedet:

```java
@Test
public void testRemoveFullRows() {
  TetrisBoard board = getTetrisBoardWithContents(new String[] {
    "-T",
    "TT",
    "LT",
    "L-",
    "LL"
  });
  assertEquals(3, board.removeFullRows());
  String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "-T",
    "L-"
  });
  assertEquals(expected, board.prettyString());
}
```

---
**Knyt det sammen**

I `TetrisModel`, gjør et kall på metoden din i TetrisBoard som fjerner fulle rekker like etter at du har limt den fallende brikken til brettet, men før du har hentet en ny fallende brikke.

Returverdien er antall rekker som ble fjernet. Du kan gjerne bruke den til å regne ut en løpende poengsum.

---

:white_check_mark:  Du er ferdig når rekker blir fjernet fra brettet når de blir fulle. Sjekk at det også virker når du får flere fulle rekker på brettet, og at det virker på nederste rad.