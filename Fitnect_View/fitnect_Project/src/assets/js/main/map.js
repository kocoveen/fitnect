// src/utils/map.js

let map = null;
let myLocationMarker = null;
const markers = [];

export const initMap = (lat, lng) => {
  const myCenter = new kakao.maps.LatLng(lat, lng);
  const container = document.getElementById("map");
  const options = {
    center: myCenter,
    level: 4,
  };
  map = new kakao.maps.Map(container, options);

  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  return map;
};

export const findMe = (lat, lng) => {
  const myCenter = new kakao.maps.LatLng(lat, lng);
  map.setCenter(myCenter);

  if (myLocationMarker) {
    myLocationMarker.setMap(null);
  }

  myLocationMarker = new kakao.maps.Marker({
    position: myCenter,
    map: map,
  });
  myLocationMarker.setMap(map);
};

export const showLocationMarker = async (gyms, origin, calculateDistance, showInfo) => {
  for (let gym of gyms) {
    const destination = { lat: gym.latitude, lng: gym.longitude };
    try {
      const distance = await calculateDistance(origin, destination);
      gym.distance = distance;

      const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
      const marker = new kakao.maps.Marker({
        map,
        position: location,
      });

      kakao.maps.event.addListener(marker, "click", () => {
        showInfo(gym);
      });

      marker.setMap(map);
      markers.push(marker);
    } catch (error) {
      console.error("Error calculating distance:", error);
    }
  }

  gyms.sort((a, b) => a.distance - b.distance);
};

export const hideMarker = () => {
  for (let marker of markers) {
    marker.setMap(null);
  }
  markers.length = 0;
};
