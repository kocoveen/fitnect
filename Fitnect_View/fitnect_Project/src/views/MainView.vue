<template>
  <div class="main-container">
    <nav class="sidebar">
      <div class="search">
        <input class="inputbox" v-model="keyword" placeholder="검색어 입력">
        <div class="search-icon" @click="isTrueRender()">
            <img src="@/assets/imgs/search.svg" />
        </div>
      </div>
      <div class="items">
        <template v-for="gym in filteredGymList" :key="gym.gymId">
          <div class="item" @click="showInfo(gym)">
            {{ gym.name }}
          </div>
        </template>
      </div>
    </nav>
    <nav class="gym-info" v-if="selectedGym">
        <div>{{ selectedGym.name }}</div>
        <div>{{ selectedGym.address }}</div>
        <button @click="hideInfo">맵 닫기</button>
    </nav>
    <div id="map-container">
      <div id="map"></div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from 'axios';

const accessToken = sessionStorage.getItem("accessToken");

const gymList = ref([]);
const filteredGymList = ref([]);
const markers = ref([]); 
const keyword = ref('');

const isTrueRender = () => {
  hideMarker();
  filteredGymList.value = gymList.value.filter(gym => isTrue(gym));
  showLocationMarker();
}

const isTrue = (gym) => {
  if (!keyword.value) {
    return true;
  } else {
    return (gym.name.includes(keyword.value) || gym.type.includes(keyword.value));
  }
}

let map = null;

/*------------------------------------------------------------*/
function base64UrlDecode(str) {
    return decodeURIComponent(atob(str.replace(/-/g, '+').replace(/_/g, '/')).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
}

// Function to decode the JWT
function decodeJWT(token) {
    try {
        const [header, payload, signature] = token.split('.');

        // Decode the header and payload
        const decodedHeader = JSON.parse(base64UrlDecode(header));
        const decodedPayload = JSON.parse(base64UrlDecode(payload));
        
        return {
            header: decodedHeader,
            payload: decodedPayload,
            signature: signature
        };
    } catch (error) {
        console.error('Invalid token:', error);
        return null;
    }
}

const loginUser = decodeJWT(accessToken);
const myLng = Math.round(loginUser.payload.lon * 1000000) / 1000000;
const myLat = Math.round(loginUser.payload.lat * 1000000) / 1000000;
/*------------------------------------------------------------*/

// 지도 초기화 함수
const initMap = function () {
  // let myCenter = new kakao.maps.LatLng(x.value, y.value);
  let myCenter = new kakao.maps.LatLng(myLng, myLat);

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
    level: 4
  };
  map = new kakao.maps.Map(container, options);
  
  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  // showLocationMarker(myCenter, "asdf")
  showLocationMarker();
};

const showLocationMarker = () => {
  for (var i = 0; i < filteredGymList.value.length; i++) {
    const gym = filteredGymList.value[i];
    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    const marker = new kakao.maps.Marker({
      map: map.value,
      position: location
    });
    marker.setMap(map);
    markers.value.push(marker);
  };
}

const hideMarker = () => {
  for (var i = 0; i < markers.value.length; i++) {
    markers.value[i].setMap(null);
  }
  markers.value.length = 0
}

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

  axios
    .get(`http://localhost:8080/gym/with-asso`, {
      headers: {
        "Authorization": `bearer ${sessionStorage.getItem('accessToken')}`,
      },
    })
    .then((response) => {
      gymList.value = response.data.data;
      filteredGymList.value = gymList.value.filter(gym => isTrue(gym));
      isTrueRender();
    })
    .catch((e) => {
      
    });
});

const selectedGym = ref(null);

const showInfo = (gym) => {
  selectedGym.value = gym;
};

const hideInfo = () => {
  selectedGym.value = null;
};

</script>

<style scoped>
.main-container {
  display: flex;
  height: 100vh;
}

.sidebar {
  width: 300px;
  height: 100vh;
  overflow-y: auto;
  background-color: #ffffff;
  left: 0;
  top: 0;
  z-index: 0;
}

.search {
  padding: 10px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.inputbox {
  padding: 0.4375rem 0.875rem;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.53;
  appearance: none;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #d9dee3;
  border-radius: 5px;
  color: #566a7f;
  transition: border-color 0.5s ease;
}

.search-icon {
  background-color: gray;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
}

.gym-info {
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 300px;
  height: 100%;
  position: absolute;
  left: 300px;
  top: 0;
  z-index: 2;
}

.items {
  height: 80%;
  width: 100%;
  overflow-y: auto;
  background-color: #f0f0f0;
}

.item {
  height: 80px;
  width: 100%;
  border-bottom: 1px solid #ccc;
}

#map-container {
  flex-grow: 1;
  position: relative;
  width: calc(100% - 300px);
}

#map {
  width: 100%;
  height: 100%;
}
</style>
