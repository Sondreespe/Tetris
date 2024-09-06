[forrige](./08-timer.md) &bullet; [oversikt](../README.md#steg-for-steg) &bullet; [SVAR.md](../SVAR.md)

# 9 Flere idéer

Dersom du ønsker, prøv deg gjerne på å forbedre spillet. Vi deler ut noen ekstra premier til de aller mest imponerende variantene. Noen idéer for å komme i gang:

**Enkle:**
- Økende vanskelighetsgrad: legg til nivåer i spillet. Du kan for eksempel ha en feltvariabel for antall rader fjernet totalt som du oppdaterer i metoden som limer en brikke til brettet; så kan du regne ut hva nivået bør være basert på den. Deretter kan du endre metoden som angir hvor lang tid det skal gå mellom hvert klokkeslag slik at returverdien blir basert på nivået.
- Vis poeng ved game over: hver gang man fjerner en eller flere rekker, får man poeng.  Vanligvis får man 100 poeng for å fjerne én linje, 300 poeng for å fjerne to linjer, 500 poeng for å fjerne tre linjer og 800 poeng for å fjerne fire linjer. Poensummen multipliseres med hvilket nivå man er på. Vis score ved game over.
- Vis poeng underveis under eller ved siden av brettet.
- Vis en velkomstskjerm (lignende game over) med "press key down to begin." Etter game over kan et trykk føre tilbake hit.
- En pause-skjerm.
- Rotering i to retninger

**Medium:**
- Vis en "skygge" av brikken der den vil lande dersom man dropper.
- Vis neste brikke som ankommer i en rute ved siden av brettet.
- Mykere flytting av brikken: kombiner keyPressed og keyReleased med en timer som flytter tetrominoen (timeren startes i keyPressed og stoppes i keyReleased, og flytter tetrominoen med et fast intervall).
- Penere tegning av brikkene
- [Wall kicks](https://tetris.fandom.com/wiki/SRS#Wall_Kicks)

**Avansert:**
- Vis en oversikt over beste poengsummer oppnådd etter game over, skriv inn navn i high score-liste (enda bedre: lagret mellom spill)
- Poeng basert på [T-spin](https://tetris.fandom.com/wiki/T-Spin_Guide)
- Ulike game modes (vanskeligst mulige brikke kommer *alltid*, random brikker fjernes eller flytter seg tidvis på brettet etc.)
- To-player på ulike deler av tastaturet -- når en spiller fjerner noen rekker, legges de fjernede rekkene (slik de så ut før de ble fjernet) til *nederst* på motspilleren sitt brett, og dytter de andre brikkene på brettet hens oppover (rekker man har fått av motspiller på denne måten flyttes ikke tilbake igjen, da fjernes de for godt når de blir fjernet).

**For de ambisiøse som kan litt fra før:**
- High score på internett (delt mellom brukere?)
- Smuud animasjon av brikker som flytter seg, animasjoner når rekker fjernes
- To-player over lokalt nettverk.

Eller noe helt annet.