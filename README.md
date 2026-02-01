🎮 Boss Fight – Semestrálna práca

Jednoduchá 2D akčná hra vytvorená ako semestrálna práca v jazyku Java s využitím knižnice ShapesGE.
Hráč ovláda vesmírnu loď, bojuje proti bossovi a vyhýba sa lietajúcim nepriateľom.

⸻

🕹️ Popis hry

Cieľom hry je poraziť bossa, ktorý má viacero fáz správania.
Počas boja boss strieľa rôzne typy striel a v neskorších fázach privoláva ďalších nepriateľov.

Hráč musí využívať pohyb, streľbu a dash, aby sa vyhol útokom a postupne bossa porazil.

Hra obsahuje:
	•	hlavné menu
	•	pauzu
	•	stav výhry
	•	stav prehry
	•	systém životov hráča aj bossa

⸻

🎮 Ovládanie
	•	Šípky (← ↑ ↓ →) – pohyb hráča
	•	Medzerník – streľba
	•	Ľavý Shift – dash
	•	P – pauza / pokračovanie hry

⸻

🧠 Herné mechaniky
	•	Boss má viacero fáz, ktoré sa menia podľa počtu jeho životov
	•	Rôzne typy striel (klasická, zig-zag)
	•	Lietajúci nepriatelia zameraní na hráča
	•	Dočasná nesmrteľnosť hráča po zásahu
	•	HP bar hráča aj bossa
	•	Kolízny systém založený na hitboxoch

⸻

🛠️ Použité technológie
	•	Java
	•	ShapesGE
	•	IntelliJ IDEA
	•	UML diagram
	•	Checkstyle

⸻

▶️ Spustenie hry

Požiadavky:
	•	Nainštalovaná Java (JDK 8 alebo novšia)

Štruktúra priečinkov projektu:

SemestralnaPracaSkukalek
└── Zdrojak
  ├── assets
  ├── src
  └── Hra.jar

DÔLEŽITÉ: Priečinok „assets“ musí zostať v rovnakom priečinku ako súbor „Hra.jar“, inak sa hra nespustí správne.

⸻

Spustenie hry cez terminál (macOS / Linux)
	1.	Otvorte terminál
	2.	Prejdite do priečinka so súbormi hry:
cd SemestralnaPracaSkukalek/Zdrojak
	3.	Spustite hru príkazom:
java -jar Hra.jar

⸻

Spustenie hry cez terminál (Windows)
	1.	Otvorte Command Prompt alebo PowerShell
	2.	Prejdite do priečinka so súbormi hry:
cd SemestralnaPracaSkukalek\Zdrojak
	3.	Spustite hru príkazom:
java -jar Hra.jar
