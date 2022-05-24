## Om Appen
Denna app är slutprojektet i kursen Programmering av mobila applikationer G1F, 7,5 hp. 
Namnet på appen är formelbilstävlingar, just eftersom den har i uppgift att visa information om några av de största tävlingarna. 
Appen består till mestadels av en Recycler View som får sin data från JSON (figur 1.1). Reycler Viewns innehåll består av 
Card Views som aggerar knappar och vid klick tar en vidare till en mer detaljerad vy som är en ny aktivitet som skapas utifrån en 
intent med intentdata (extras). Även sidan i menyn som kallas sig för om appen är skapad med hjälp av en intent och en WebView. 
Nedan följer först en mer detaljerad beskrivning av applikationens funktioner och sedan en beskrivning av vad för kod som använts 
för att skapa dessa funktioner.

```
[
  {
    "ID": "bana-indianapolis",
    "name": "Indianapolis Motor Speedway",
    "type": "c21alest",
    "company": "Indianapolis 500",
    "location": "Indianapolis, Indiana, USA",
    "category": "oval",
    "size": 4023,
    "cost": 0,
    "auxdata": {
      "img": "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Indianapolis_Oval.svg/640px-Indianapolis_Oval.svg.png",
      "ow21": "Hélio Castroneves",
      "dimg": "https://imageio.forbes.com/specials-images/imageserve/60b912afbc99659e94e3f025/105th-Running-Of-The-Indianapolis-500---Winner-s-Portraits/960x0.jpg?fit=bounds&format=jpg&width=960",
      "age": "47",
      "nat": "Brasilien"
    }
  },
  {
    "ID": "bana-laguna_seca",
    "name": "WeatherTech Raceway Laguna Seca",
    "type": "c21alest",
    "company": "Firestone Grand Prix of Monterey",
    "location": "Monterey, Kalifornien, USA",
    "category": "racebana",
    "size": 3602,
    "cost": 0,
    "auxdata": {
      "img": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Laguna_Seca.svg/1280px-Laguna_Seca.svg.png",
      "ow21": "Josef Newgarden",
      "dimg": "https://beyondtheflag.com/wp-content/uploads/getty-images/2022/04/1390745872.jpeg",
      "age": "31",
      "nat": "Tennessee, USA"
    }
  },
  {
    "ID": "bana-long_beach",
    "name": "Long Beach Grand Prix",
    "type": "c21alest",
    "company": "Acura Grand Prix of Long Beach",
    "location": "Long Beach, Kalifornien, USA",
    "category": "stadsbana",
    "size": 2100,
    "cost": 0,
    "auxdata": {
      "img": "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Long_Beach_Street_Circuit_IndyCar.svg/1920px-Long_Beach_Street_Circuit_IndyCar.svg.png",
      "ow21": "Josef Newgarden",
      "dimg": "https://beyondtheflag.com/wp-content/uploads/getty-images/2022/04/1390745872.jpeg",
      "age": "31",
      "nat": "Tennessee, USA"
    }
  },
  {
    "ID": "bana-monaco",
    "name": "Circuit de Monaco",
    "type": "c21alest",
    "company": "Monacos Grand Prix",
    "location": "Monte Carlo, Monaco",
    "category": "stadsbana",
    "size": 3337,
    "cost": 0,
    "auxdata": {
      "img": "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Monte_Carlo_Formula_1_track_map.svg/1920px-Monte_Carlo_Formula_1_track_map.svg.png",
      "ow21": "Max Verstappen",
      "dimg": "https://boardroom.tv/wp-content/uploads/2022/03/GettyImages-1211696969-e1646244913198-1280x720.jpg",
      "age": "24",
      "nat": "Nederländerna"
    }
  },
  {
    "ID": "bana-spa",
    "name": "Circuit de Spa-Francorchamps",
    "type": "c21alest",
    "company": "Belgiens Grand Prix",
    "location": "Liège, Belgien",
    "category": "racebana",
    "size": 7004,
    "cost": 0,
    "auxdata": {
      "img": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/Spa-Francorchamps_of_Belgium.svg/2560px-Spa-Francorchamps_of_Belgium.svg.png",
      "ow21": "Max Verstappen",
      "dimg": "https://boardroom.tv/wp-content/uploads/2022/03/GettyImages-1211696969-e1646244913198-1280x720.jpg",
      "age": "24",
      "nat": "Nederländerna"
    }
  }
]
```
URL: https://mobprog.webug.se/json-api?login=c21alest
_Figur 1.1 JSON Data_

#Förklaring
## Förstasidan
Varje gång du öppnar appen kommer ett antal race att dyka upp. Vad som visas beror på vilken
filtrering du gjorde senast du stängde appen eftersom detta sparas. Du kan alltid byta detta genom dropdownen som
 finns längst upp till vänster. För att veta mer om ett race klickar du på kortet.

<img src="first_page.png" width="40%">


## Detaljerad vy
<p>När du klickat på det kort du vill veta mer om visas en nya aktivitet likt bilden nedan. Här kan du
skrolla på skärmen för att visa allt innehåll som finns tillgängligt om det specifika racet. Bland annat kan du här
se en bild på föraren som du inte kunde i tidigare vy.</p>

<img src="second_page.png" width="40%">


## Filter
<p>Om du önskar att filtrera racen är det möjligt med dropdownen. Du har där 3 alternativ, antingen visas
bara ovalbanor, stadsbanor, eller racebanor. När du klickar på någon av dessa kommer innehållet att uppdateras. Om
du väljer oval kommer det att se ut likt bilden nedan:</p>

<img src="oval.png" width="40%">


## Video som visar hur appen funkar:

<img src="gif.gif" width="40%">


