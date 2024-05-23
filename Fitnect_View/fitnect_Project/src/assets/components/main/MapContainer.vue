<template>
  <div id="map-container">
    <div id="map"></div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";

const initMap = () => {
  let myCenter = new kakao.maps.LatLng(37.5665, 126.978); // 기본 중심 위치를 설정합니다.

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lng = position.coords.longitude;
      myCenter = new kakao.maps.LatLng(lat, lng);
      map.setCenter(myCenter);
    });
  }

  const map = new kakao.maps.Map(document.getElementById("map"), {
    center: myCenter,
    level: 3,
  });

  const markerPosition = myCenter;
  const marker = new kakao.maps.Marker({
    position: markerPosition,
  });

  marker.setMap(map);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    kakao.maps.load(initMap);
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=18ec83912bcf322b790dd648193570e8&libraries=services&autoload=false`;
    document.head.appendChild(script);
    script.onload = () => {
      kakao.maps.load(initMap);
    };
  }
});
</script>

<style scoped>
#map-container {
  flex-grow: 1;
  position: relative;
  width: calc(100% - 400px); /* Adjusted to make space for the detail search options */
}

#map {
  width: 100%;
  height: 100%;
}
</style>
