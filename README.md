# 🎮 Boss Fight – Semestrálna práca

Jednoduchá 2D akčná hra vytvorená ako semestrálna práca v jazyku **Java** s využitím knižnice **ShapesGE**.  
Hráč ovláda vesmírnu loď, bojuje proti bossovi a vyhýba sa lietajúcim nepriateľom.

---

## 🕹️ Popis hry

Cieľom hry je poraziť bossa, ktorý má viacero fáz správania.  
Počas boja boss strieľa rôzne typy striel a v neskorších fázach privoláva ďalších nepriateľov.  
Hráč musí využívať pohyb, streľbu a dash, aby sa vyhol útokom a postupne bossa porazil.

Hra obsahuje:
- hlavné menu
- pauzu
- výhru a prehru
- systém životov hráča aj bossa

---

## 🎮 Ovládanie

- **← ↑ ↓ → (šípky)** – pohyb hráča  
- **Medzerník** – streľba  
- **Ľavý Shift** – dash  
- **P** – pauza / pokračovanie hry  

---

## 🧠 Herné mechaniky

- Boss má **viacero fáz**, ktoré sa menia podľa počtu životov
- Rôzne typy striel (klasická, zig-zag)
- Lietajúci nepriatelia, ktorí sa zameriavajú na hráča
- Dočasná nesmrteľnosť hráča po zásahu
- HP bary pre hráča aj bossa

---

## 🛠️ Použité technológie

- **Java**
- **ShapesGE**
- **IntelliJ IDEA**
- **UML diagram**
- **Checkstyle**

---

## ▶️ Spustenie hry

### Požiadavky
- Nainštalovaná **Java (JDK 8 alebo novšia)**

### Spustenie cez terminál

1. Otvorte terminál
2. Prejdite do priečinka so súbormi hry:
   ```bash
   cd SemestralnaPracaSkukalek/Zdrojak
