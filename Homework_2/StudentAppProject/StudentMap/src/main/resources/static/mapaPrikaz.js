var map = L.map('map').setView([41.994626, 21.430379], 13);
const attribution = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
const tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
const tiles = L.tileLayer(tileUrl, {attribution})
tiles.addTo(map);

let open = false;


function menuClick(){
    console.log("click")
    if(open) {
        document.getElementById("map").style.width = screen.width.toString();
        document.getElementById("map").style.margin = "0px"
        open = false;
        document.getElementById("sidebar").hidden = true;
    }
    else
    {
        document.getElementById("map").style.width = (screen.width - 300).toString();
        document.getElementById("map").style.margin = "0px 0px 0px 320px"
        open = true;
        document.getElementById("sidebar").hidden = false;
    }
}

var uniicon = L.icon({
    iconUrl: 'icon.png',
    iconSize:     [38, 40],
    iconAnchor:   [19, 40],
    popupAnchor:  [0, -40]
});

async function loadAllLocations(){
    let items = document.getElementById("locs").value;
    let parsed = JSON.parse(items);
    console.log(parsed[0])
    //var marker = L.marker([21.435225, 41.999165]).addTo(map);
    // var marker2 = L.marker([41.999165, 21.435225]).addTo(map);
    let i
    for(let item of parsed){
        console.log(item.y);
        let marker = L.marker([item.y, item.x]).addTo(map);
    }
}

loadAllLocations();