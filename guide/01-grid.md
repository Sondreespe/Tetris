forrige &bullet; [oversikt](../README.md#steg-for-steg) &bullet; [neste](./02-tegnrutenett.md)

# 1 Implementer grid

Implementer klassene, `Grid`, `CellPosition` og `GridCell` i pakken *no.uib.inf101.tetris.grid*. Når du er ferdig skal alle testene som ble delt ut sammen med prosjektet passere. Bruk gjerne en av grid-klassene du har sett tidligere (i forelesning eller på laber) som inspirasjon/utgangspunkt og gjør tilpasninger.

 - Opprett en record-klasse `CellPosition` med int'er `row` og `col`.
 

 - Opprett en record-klasse `GridCell` som har en en generisk typeparameter `<E>`. Objekter av denne typen skal ha to feltvariabler:
   * `pos`, som har typen `CellPosition`, og 
   * `value`, som har den generiske typen `E`.

 - Opprett en klasse `Grid` som representerer et grid. Gridet skal ha en generisk typeparameter `<E`>, og må implementere grensesnittet `IGrid<E>` som ligger klart fra før. 
    - Begynn med å opprette alle metoder som kreves av grensesnittet, men som i første omgang returner "dummy" verdier (e.g. 0 eller `null` alt ettersom). Dette kan gjøres ved «quick fix» eller lignende.
    - Se på javadoc-kommentarene i IGrid og se på hvordan testene i `GridTest` er skrevet for å se hvilke metoder og konstruktører du trenger å implementere.


## Iterable&lt;GridCell&lt;E&gt;&gt;

Du legger kanskje merke til at `IGrid` utvider grensesnittet `Iterable<GridCell<E>>`. Den helt klart vanskeligste deloppgaven i dette steget er å implementere metoden *iterator* i Grid-klassen, hvis man gjør det «skikkelig.» Men det finnes også en snarvei som vi anbefaler alle å ta:

 - I metoden `iterator`, opprett et nytt objekt av typen `ArrayList<GridCell<E>>`.
 - Gå deretter igjennom alle koordinater i grid'et med nøstede for-løkker.
 - For hver posisjon: opprett ett *GridCell&lt;E&gt;* med riktig posisjon og element, og legg det til i listen.
 - Avslutt med å returnere iteratoren du får ved å kalle `.iterator()` på ArrayList-objektet.

 Den største ulempen med denne snarveien er at vi okkuperer mer minne enn nødvendig. Men kostnaden er ikke større enn at dette er en fair løsning enn så lenge.

 (PS: Dersom du har tatt utgangspunkt i din løsning på lab 4 vil *iterator* -metoden i denne oppgaven gjøre det samme som *getCells*. Du trenger egentlig bare endre slik at du returnerer svaret på et `.iterator()` -kall på listen)

---

:white_check_mark: Du er ferdig når alle testene i `CellPositionTest`, `GridCellTest` og `GridTest` passerer.
