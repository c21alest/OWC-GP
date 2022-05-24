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

#Lösning
## Recycler view på Förstasidan
För att visa allt innehåll på förstasidan används en Recycler View i kombination med Card View, där Recycler Viewn
visar upp allt innehåll som finns i JSON Datan och Card Viewn är en typ av styling från ett externt bibliotek.

En recycler view gör det möjligt att dynamiskt visa innehåll. Det vill säga att presentera data oberoende av dess längd och storlek. 
För att skapa en recycler view krävs ett antal olika komponenter, där de största är en adapter, view holder och en layout manager. 
Adaptern är den huvudsakliga komponenten som presenterar all data medan en view holder tar hand om varje individuellt element medan 
layout managern arrangerar alla dessa element.

Först av allt skapades en instans av dessa komponenter, kod för detta syns nedan. Man börjar med att skapa de olika variablerna. 
myAdapter blir kopplat mot MainAdapter eftersom java redan vet att det kommer vara en recycler view.
När detta är gjort länkas recyclerviewn mot ett id i layouten där datan kan presenteras. Efter det länkas layout managern till den 
tidigare variabeln som skapades. Sen skapas och kopplas adaptern och här specifiseras den data vi vill skicka med, i detta fall kommer en list
som JsonTask skapats (pratas om längre ner) att skickas med. Slutligen kopplar man både adaptern och layout managern till recycler viewn.

```
    RecyclerView myRecyclerView;
    MainAdapter myAdapter;
    LinearLayoutManager myLayoutManager;
    
    List<gp> gpinfo;
    private gp[] gpInfo;
    
    (...)
    
        // Skapar en recycler view instans
        myRecyclerView = findViewById(R.id.recycler_view);
        // Optimerings parameter
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new MainAdapter(gpinfo, this, sort);
        myRecyclerView.setAdapter(myAdapter);
```
_Figur 2.2 Kod för att instansera en Recycler View och dess komponenter_

Men nu har gpinfo som skickas med i adaptern bara deklareats men aldrig initiseras med någon data därför kommer recycler viewn att vara tom.
Och för att lägga in data i gpinfo görs detta efter att JsonTask är klar, vilket innebär att OnPostExecute kommer att körs. Där kan vi alltså
använda oss av en setter och funktionen notifyDataSetChanged för att uppdatera information i recycler viewn, kod för detta syns nedan. Settern
gör just vad det låter som alltså att sätta ett värde på datan, alltså som en transportväg för att skicka data ifrån mainactivity till mainadapter.
Sedan gör notifyDataSetChanged att adaptern blir notiferad om att den ska uppdatera recycler viewn.

```
    @Override
    public void onPostExecute(String json) {
        Log.d("==>", json);

        // Skapar gson instans från json fil och mountain klass
        Gson gson = new Gson();
        gpInfo = gson.fromJson(json, gp[].class);

        gpinfo = Arrays.asList(gpInfo);

        myAdapter.setRaces(gpinfo, sort);
        myAdapter.notifyDataSetChanged();

    }
```
_Figur 2.3 Kod för att uppdatera Recycler Viewn_
