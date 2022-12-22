var map = L.map('map').setView([41.994626, 21.430379], 13);
const attribution = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
const tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
const tiles = L.tileLayer(tileUrl, {attribution})
tiles.addTo(map);

let open = false;


function menuClick(){
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

var markers = new Array();

$("#select").change(function () {
    window.location.href = "/locations/select/"+ $(this).val();
    //console.log($(this).val());
});

let selectedUniversity;
function getId(el)
{
    var id = document.getElementById("chosenLoc").value;
    el.href="/locations/add-grade/"+ id+"/"+el.name;
    // var links = document.getElementsByClassName("addG");
    // console.log(links)
    // let i =0;
    // for(let item of links)
    // {
    //     console.log(item)
    //     console.log(i)
    //     item.href = "/locations/add-grade/"+ id+"/"+i;
    //     i++;
    // }
}

async function loadAllLocations(){
    let items = document.getElementById("locs").value;
    let parsed = JSON.parse(items);

    for(let item of parsed){
        let marker = L.marker([item.y, item.x]).bindPopup("<h3>"+item.name+"</h3>");
        markers.push(marker);
        map.addLayer(marker);
        marker.on('click', () => {
            document.getElementById("details").hidden=false;
            document.getElementById("name").innerText=item.name;
            document.getElementById("chosenLoc").value = item.id;
            document.getElementById("commentDiv").hidden=false;
            document.getElementById("commentForm").action="/comments/add-comment/" + item.id;
            document.getElementById("favesForm").action="/favourites/add-location-to-faves/" + item.id;
            let address = document.getElementById("address");
            let hours = document.getElementById("opening-hours");
            let phone = document.getElementById("phone");
            let website = document.getElementById("website");
            let avgGrade = document.getElementById("result");
            console.log(item);

            avgGrade.innerText = item.averageGrade;

            if(item.address!=="") {
                address.innerText = item.address;
                address.parentElement.hidden=false;
            }
            else address.parentElement.hidden=true;
            if(item.phone!=="") {
                phone.innerText = item.phone;
                phone.parentElement.hidden=false;
            }
            else phone.parentElement.hidden=true;
            if(item.website!=="") {
                website.innerText = item.website;
                website.href = item.website;
                website.parentElement.hidden=false;
            }
            else website.parentElement.hidden=true;
            if(item.openingHours!=="") {
                hours.innerText = item.openingHours;
                hours.parentElement.hidden=false;
            }
            else hours.parentElement.hidden=true;
            if(document.getElementById("sidebar").hidden == true){
                menuClick();
            }
        });
    }
}

loadAllLocations();

