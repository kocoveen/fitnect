<template>
  <div>
    <div style="visibility: hidden" id="map"></div>
    <div>
      <div>{{ address.name }}</div>
      <button @click="locateAddress">지도 확인</button>
      <div>{{ address.address }}</div>
      <a :href="address.url" target="_blank">{{ address.url }}</a>
      <!-- 주소 검색 버튼 추가 -->
    </div>
    <!-- <button @click="initMap">내위치</button> -->
    <form>
      <input v-model="searchKey" placeholder="주소 검색" required />
      <button @click="search">검색</button>
    </form>
    <div v-show="searchResult.length > 0">
      <table>
        <tr>
          <td>가게 이름</td>
          <td>가게 주소</td>
        </tr>
        <tr v-for="shop in searchResult" :key="shop.id">
          <td>{{ shop.place_name }}</td>
          <td>
            <a :href="generateMapLink(shop.id)" target="_blank">https://map.kakao.com/link/map/{{ shop.id }}</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, toRaw, computed } from "vue";

let map = null;

const accessToken = sessionStorage.getItem("accessToken");

const x = ref(sessionStorage.getItem("accessToken"));
const y = ref(126.570667);
// 지도 초기화 함수
const initMap = function () {
  let myCenter = new kakao.maps.LatLng(x.value, y.value);
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      myCenter = new kakao.maps.LatLng(lat, lon);
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    });
  }
  const container = document.getElementById("map");
  const options = {
    center: myCenter,
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
};

const address = ref({});
// 주소로 좌표를 검색하고 마커 표시하는 함수
const locateAddress = function () {
  const geocoder = new kakao.maps.services.Geocoder();
  geocoder.addressSearch(address.value.address, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
      const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      const marker = new kakao.maps.Marker({
        map: map,
        position: coords,
      });
      const infowindow = new kakao.maps.InfoWindow({
        content: `<div style="width:150px;text-align:center;padding:6px 0;">${address.value.name}</div>`,
      });
      infowindow.open(map, marker);
      map.setCenter(coords);
    }
  });
  document.getElementById("map").style.visibility = "visible";
};

const searchKey = ref("");
const searchResult = ref([]);
const callback = function (result, status) {
  if (status === kakao.maps.services.Status.OK) {
    console.log(result);
    searchResult.value = result;
  }
};

const search = function () {
  const places = new kakao.maps.services.Places();
  places.keywordSearch(searchKey.value, callback);
  searchKey.value = "";
};

function generateMapLink(id) {
  return `https://map.kakao.com/link/map/${id}`;
}

// 지도 생성 유무
onMounted(() => {
  // 여기에 좌표값 받아오기
  // kakao 객체가 이미 로드된 경우, 바로 지도 초기화 함수를 호출
  if (window.kakao && window.kakao.maps) {
    kakao.maps.load(initMap);
  } else {
    // kakao 객체가 로드되지 않은 경우, 스크립트를 동적으로 로드
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=18ec83912bcf322b790dd648193570e8&libraries=services`;
    document.head.appendChild(script);
    script.onload = () => {
      kakao.maps.load(initMap);
    };
  }
  // address.value = store.place;
});
</script>

<style scoped>
#map {
  width: 500px;
  height: 400px;
}
</style>
