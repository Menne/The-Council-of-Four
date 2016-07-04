Passi per testare l'applicazione:
1) Lanciare la classe Server (main/java/server/Server.java)
2a) Se si vuole giocare da CLI lanciare la classe ClientCLI (main/java/client/ClientCLI.java)
2b) Se si vuole giocare da GUI lanciare la classe ClientGUI (main/java/client/ClientGUI.java)

NB se si usa la CLI:
-Seguire le istruzioni stampate dal gioco prestando attenzione a minuscole e maiuscole
-la chat funziona anche da CLI: per mandare un messaggio agli altri giocatori, scrivere "chat:" seguito dal testo del messaggio
-se si vuole abbandonare la partita in un qualsiasi momento, digitare "quit"

Implementazione market:
-il market prevede che un giocatore possa mettere in vendita più di un oggetto nella fase di vendita, mentre può sceglierne solo uno da comprare nella fase di compera

Scelta mappa:
-Da GUI il primo gicatore di ogni partita avrà la facoltà di scegliere la mappa del gioco tra le 8 disponibili, se il timer di inizio parita scade prima che venga selezionata una mappa, il gioco parte con la mappa di default (nr 1)