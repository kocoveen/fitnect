<template>
  <div class="main-body">
    <nav class="sidebar">
      <div :class="['search', { fixed: isSearchFixed }]">
        <div class="search-area">
          <div class="input-wrapper">
            <input class="inputbox" v-model="keyword" @keyup.enter="isTrueRender" placeholder="검색어 입력" />
            <div class="search-icon" @click="isTrueRender()">
              <img src="@/assets/imgs/search.svg" />
            </div>
          </div>
        </div>
        <div class="detail-search">
          <a class="find-me">내 위치</a>
          <a class="detail-button" @click="toggleDetailSearch"> 상세 검색 </a>
        </div>
      </div>
      <div class="items">
        <div v-for="gym in filteredGymList" :key="gym.gymId">
          <div class="item" @click="showInfo(gym)">
            {{ gym.name }}
          </div>
        </div>
      </div>
    </nav>

    <transition name="slide-fade">
      <nav class="gym-info" v-if="selectedGym" :key="selectedGym ? selectedGym.gymId : 'default-key'">
        <div class="button-area">
          <img @click="hideInfo" src="@/assets/imgs/chevron-left.svg" class="close-icon" />
        </div>
        <div>{{ selectedGym.name }}</div>
        <div>{{ selectedGym.address }}</div>
      </nav>
    </transition>

    <transition name="slide-fade">
      <div class="detail-search-options" v-if="selectOption" :key="selectOption">
        <div>
          <div class="button-area" @click="toggleDetailSearch">
            <img src="@/assets/imgs/chevron-left.svg" />
          </div>
          <div></div>
        </div>
      </div>
    </transition>

    <div id="map-container">
      <div id="map"></div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, onBeforeUnmount, ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

// 데이터와 상태 변수
const gymList = ref([]);
const filteredGymList = ref([]);
const markers = ref([]);
const keyword = ref("");
const selectOption = ref(false);
const selectedGym = ref(null);
const isSearchFixed = ref(true);

const route = useRoute();

// 페이지 로드 시 URL 파라미터에서 검색어 가져오기
onMounted(() => {
  const queryKeyword = route.query.keyword;
  if (queryKeyword) {
    keyword.value = queryKeyword;
    isTrueRender();
  }
});

// 상세 검색 토글
const toggleDetailSearch = () => {
  selectOption.value = !selectOption.value;
  if (selectOption.value) {
    selectedGym.value = null;
  }
};

// 검색 결과 필터링 및 마커 표시
const isTrueRender = () => {
  hideMarker();
  filteredGymList.value = gymList.value.filter((gym) => isTrue(gym));
  showLocationMarker();
};

// 검색 조건에 맞는지 확인
const isTrue = (gym) => {
  if (!keyword.value) {
    return true;
  } else {
    return gym.name.includes(keyword.value) || gym.type.includes(keyword.value);
  }
};

// JWT 디코딩 함수
function base64UrlDecode(str) {
  return decodeURIComponent(
    atob(str.replace(/-/g, "+").replace(/_/g, "/"))
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );
}

function decodeJWT(token) {
  try {
    const [header, payload, signature] = token.split(".");
    const decodedHeader = JSON.parse(base64UrlDecode(header));
    const decodedPayload = JSON.parse(base64UrlDecode(payload));

    return {
      header: decodedHeader,
      payload: decodedPayload,
      signature: signature,
    };
  } catch (error) {
    console.error("Invalid token:", error);
    return null;
  }
}

// 토큰 및 사용자 위치
const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const myLng = Math.round(loginUser.payload.lon * 1000000) / 1000000;
const myLat = Math.round(loginUser.payload.lat * 1000000) / 1000000;

let map = null;
let myLocationMarker = null;

// 지도 초기화 함수
const initMap = function () {
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
    level: 4,
  };
  map = new kakao.maps.Map(container, options);

  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  showLocationMarker();
};

// 체육관 위치 마커 표시
const showLocationMarker = () => {
  for (var i = 0; i < filteredGymList.value.length; i++) {
    const gym = filteredGymList.value[i];
    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    const marker = new kakao.maps.Marker({
      map,
      position: location,
    });
    marker.setMap(map);
    markers.value.push(marker);
  }
};

// 마커 숨기기
const hideMarker = () => {
  for (var i = 0; i < markers.value.length; i++) {
    markers.value[i].setMap(null);
  }
  markers.value.length = 0;
};

// 스크롤 핸들러
const handleScroll = () => {
  const scrollY = window.scrollY;
  const sidebarHeight = 100;
  isSearchFixed.value = scrollY < sidebarHeight;
};

// 내 위치 찾기
const findMe = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      const myCenter = new kakao.maps.LatLng(lat, lon);
      map.setCenter(myCenter);

      if (myLocationMarker) {
        myLocationMarker.setMap(null);
      }

      myLocationMarker = new kakao.maps.Marker({
        position: myCenter,
        map: map,
      });
      myLocationMarker.setMap(map);
    });
  } else {
    alert("Geolocation is not supported by this browser.");
  }
};

// 마운트 시 실행
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
        Authorization: `bearer ${sessionStorage.getItem("accessToken")}`,
      },
    })
    .then((response) => {
      gymList.value = response.data.data;
      filteredGymList.value = gymList.value.filter((gym) => isTrue(gym));
      isTrueRender();
    })
    .catch((e) => {});

  window.addEventListener("scroll", handleScroll);
});

// 언마운트 시 실행
onBeforeUnmount(() => {
  window.removeEventListener("scroll", handleScroll);
});

// 체육관 정보 표시
const showInfo = (gym) => {
  if (selectedGym.value && selectedGym.value.gymId === gym.gymId) {
    selectedGym.value = null; // 클릭한 체육관이 이미 선택된 경우 닫기
  } else {
    selectedGym.value = gym; // 다른 체육관을 선택한 경우 해당 체육관을 표시
    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    map.setCenter(location); // 클릭한 체육관을 지도의 중심으로 설정
  }
  selectOption.value = false;
};

// 체육관 정보 숨기기
const hideInfo = () => {
  selectedGym.value = null;
};
</script>

<style scoped>
.main-body {
  display: flex;
  height: 100vh;
}

/* search area */
.sidebar {
  width: 360px;
  min-width: 360px;
  height: 100vh;
  overflow-y: auto;
  background-color: #ffffff;
  left: 0;
  top: 0;
  z-index: 100; /* 사이드바가 더 앞에 오도록 z-index 설정 */
}

.search {
  padding: 10px 20px;
  border-bottom: 1px solid #ccc;
  position: fixed;
  top: 110px;
  left: 0;
  width: 360px;
  min-width: 360px;
  height: 115px;
  right: 0;
  background-color: #ffffff;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: top 0.5s ease, opacity 0.5s ease;
}

.search.fixed {
  top: 110px;
  opacity: 1;
}

.search:not(.fixed) {
  top: 130px;
  opacity: 0;
}

.search-area {
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 70%;
  align-items: center;
}

.input-wrapper {
  position: relative;
  width: 100%;
}

.inputbox {
  padding: 0.4375rem 2.5rem 0.4375rem 0.875rem;
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
  width: 100%;
  height: 55px;
}

.search-icon {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  width: 24px; /* 아이콘 크기 조정 */
  height: 24px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}
/* search area */

/* detail-search */
.detail-search {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 30%;
  font-size: 13px;
  justify-content: space-between;
}

.detail-button {
  color: #44b7ce;
  cursor: pointer;
}
/* detail-search */

/* items */
.items {
  width: 100%;
  overflow-y: auto;
  background-color: #f0f0f0;
  margin-top: 115px;
}

.item {
  height: 80px;
  width: 100%;
  border-bottom: 1px solid #ccc;
  cursor: pointer;
}

.items > div > div {
  display: flex;
  justify-content: center;
  align-items: center;
}
/* items */

/* gym-info */
.gym-info {
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 450px;
  min-width: 450px;
  height: 100%;
  position: absolute;
  left: 360px;
  z-index: 50; /* 사이드바보다 뒤에 오도록 z-index 설정 */
  transition: transform 0.5s ease, opacity 0.5s ease;
}

.close-icon {
  width: 24px;
  height: 24px;
  cursor: pointer;
}
/* gym-info */

.find-me {
  color: #787878;
  cursor: pointer;
}
/* detail-search-options */
.detail-search-options {
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 450px;
  min-width: 450px;
  height: 100%;
  position: absolute;
  left: 360px;
  z-index: 50; /* 사이드바보다 뒤에 오도록 z-index 설정 */
  transition: transform 0.5s ease, opacity 0.5s ease;
}

.option-item {
  padding: 10px;
  border-bottom: 1px solid #ccc;
  cursor: pointer;
}

.option-item:hover {
  background-color: #f0f0f0;
}
/* detail-search-options */

/* slide-fade transition */
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.5s ease;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(-100%);
  opacity: 0;
}

.button-area {
  display: flex;
  flex-direction: row-reverse;
}

/* map */
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
