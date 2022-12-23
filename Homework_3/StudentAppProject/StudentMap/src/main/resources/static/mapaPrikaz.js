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
            document.getElementById("x").innerText=item.x;
            document.getElementById("y").innerText=item.y;
            document.getElementById("type").innerText=item.type;


            let address = document.getElementById("address");
            let editaddress = document.getElementById("editaddress");
            address.innerHTML=""
            editaddress.innerHTML=""
            let hours = document.getElementById("opening-hours");
            hours.innerHTML=""
            let edithours = document.getElementById("editopeningHours");
            edithours.innerHTML=""
            let phone = document.getElementById("phone");
            phone.innerHTML=""
            let editphone = document.getElementById("editphone");
            editphone.innerHTML=""
            let website = document.getElementById("website");
            website.innerHTML=""
            let editwebsite = document.getElementById("editwebsite");
            editwebsite.innerHTML=""
            let avgGrade = document.getElementById("result");
            // console.log(item);

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
            document.getElementById("commentForm").action="/comments/add-comment/" + item.id;
            document.getElementById("favesForm").action="/favourites/add-location-to-faves/" + item.id;
            document.getElementById("editForm").action="/locations/edit-form/" + item.id;
            document.getElementById("deleteForm").action="/locations/delete/" + item.id;
        });
    }
}
function editLocation(){
    let locationId = document.getElementById("chosenLoc").value;
    document.getElementById("id").value = locationId;

    let editx = document.getElementById("editx");
    editx.value = document.getElementById("x").innerText;

    let edity = document.getElementById("edity");
    edity.value = document.getElementById("y").innerText;

    let edittype = document.getElementById("edittype");
    edittype.innerText = document.getElementById("type").innerText;
    edittype.name=document.getElementById("type").innerText;
    edittype.value=document.getElementById("type").innerText;

    let editname = document.getElementById("editname");
    editname.value = document.getElementById("name").innerText;

    let editaddress = document.getElementById("editaddress");
    editaddress.value = document.getElementById("address").innerText;

    let edithours = document.getElementById("editopeningHours");
    edithours.value = document.getElementById("opening-hours").innerText;

    let editphone = document.getElementById("editphone");
    editphone.value = document.getElementById("phone").innerText;

    let editwebsite = document.getElementById("editwebsite");
    editwebsite.value = document.getElementById("website").innerText;

}

loadAllLocations();

