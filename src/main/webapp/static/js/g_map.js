var myCenter=new google.maps.LatLng(38.763372,-9.206806);

function initialize()
{
    var mapProp = {
        center:myCenter,
        zoom:8,
        mapTypeId:google.maps.MapTypeId.ROADMAP
    };

    var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

    var marker=new google.maps.Marker({
        position:myCenter,
    });

    marker.setMap(map);

    google.maps.event.addListener(marker,'click',function() {
        map.setZoom(30);
        map.setCenter(marker.getPosition());
        animation:google.maps.Animation.BOUNCE
    });
}

google.maps.event.addDomListener(window, 'load', initialize);