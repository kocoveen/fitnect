<template>
  <div class="main-body">
    <nav class="sidebar shadow-box">
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
          <a class="find-me" @click="findMe">내 위치</a>
          <a class="detail-button" @click="toggleDetailSearch"> 상세 검색 </a>
        </div>
      </div>
      <transition-group name="slide" tag="div" class="items" :key="renderKey">
        <div v-for="gym in filteredGymList" :key="gym.gymId" class="list-item">
          <div class="item" @click="showInfo(gym)">
            <div class="gym-name">{{ gym.name }}</div>
            <div style="font-size: 12px; color: red">{{ gym.content }}</div>
            <div class="gym-content">
              <div class="rating-area">
                <img src="@/assets/imgs/star.svg" style="width: 20px; height: 20px" />
                {{ gym.rating }}
              </div>
              <div>{{ gym.operatingStatus }}</div>
              <div>{{ gym.type }}</div>
            </div>
          </div>
          <img :src="gym.gymImgUrl || defaultImg" alt="Gym Image" class="gym-image" />
        </div>
      </transition-group>
      <div v-if="filteredGymList.length === 0" class="no-results" :key="renderKey">검색 결과가 없습니다</div>
    </nav>

    <transition name="slide-fade">
      <nav class="gym-info shadow-box" v-if="selectedGym" :key="selectedGym ? selectedGym.gymId : 'default-key'">
        <div>
          <div class="detail-area">
            <div>Information</div>
            <div class="button-area">
              <img @click="hideInfo" src="@/assets/imgs/chevron-left.svg" class="close-icon" />
            </div>
          </div>
          <SimpleInfo :gym="selectedGym" />
        </div>
      </nav>
    </transition>

    <transition name="slide-fade">
      <div class="detail-search-options shadow-box" v-if="selectOption" :key="selectOption">
        <div>
          <div class="detail-area">
            <div>Option</div>
            <div class="button-area" @click="toggleDetailSearch">
              <img src="@/assets/imgs/chevron-left.svg" />
            </div>
          </div>
          <DropDown @sort-change="onSortChange" @filter-type="filterByType" @exercise-change="filterByType" />
        </div>
      </div>
    </transition>

    <div id="map-container">
      <div id="map"></div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onMounted, onBeforeUnmount, ref } from "vue";
import { useRoute } from "vue-router";

import DropDown from "@/assets/components/main/dropdown-menu.vue";
import SimpleInfo from "@/assets/components/main/simple-info.vue";

import defaultImg from "@/assets/imgs/default.png";

const gymList = ref([]);
const filteredGymList = ref([]);
const markers = ref([]);
const keyword = ref("");
const selectOption = ref(false);
const selectedGym = ref(null);
const isSearchFixed = ref(true);
const selectedTypes = ref([]);
const renderKey = ref(0);
const route = useRoute();

const sortList = (sortOption) => {
  filteredGymList.value.sort((a, b) => {
    if (sortOption === "name") {
      return a.name.localeCompare(b.name);
    } else if (sortOption === "price") {
      return a.price - b.price;
    } else if (sortOption === "distance") {
      return a.distance - b.distance;
    } else if (sortOption === "members") {
      return a.members - b.members;
    } else if (sortOption === "reviews") {
      return a.reviews - b.reviews;
    } else if (sortOption === "rating") {
      return b.rating - a.rating;
    }
    return 0;
  });
};

const onSortChange = (sortOption) => {
  sortList(sortOption);
  renderKey.value += 1;
};

const filterByType = (type) => {
  selectedTypes.value = [type];
  isTrueRender();
  renderKey.value += 1;
};

// 현재 시간을 기준으로 체육관의 영업 상태를 계산하는 함수
const getOperatingStatus = (gym) => {
  const now = new Date();
  const currentDay = now.getDay(); // 0: Sunday, 1: Monday, ..., 6: Saturday
  const currentTime = now.getHours() * 100 + now.getMinutes(); // HHMM 형식으로 변환

  if (!gym.operationHours) {
    return "운영 시간 정보 없음";
  }

  const [openTime, closeTime] = gym.operationHours.split(" - ").map((time) => {
    const [hours, minutes] = time.split(":").map(Number);
    return hours * 100 + minutes;
  });

  const closedDays = gym.closedDay ? gym.closedDay.split(",").map(Number) : [];

  if (closedDays.includes(currentDay)) {
    return "오늘 휴무";
  } else if (currentTime < openTime) {
    return "영업 전";
  } else if (currentTime >= openTime && currentTime <= closeTime) {
    return "영업 중";
  } else {
    return "영업 종료";
  }
};

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
  filteredGymList.value = gymList.value
    .filter((gym) => isTrue(gym))
    .map((gym) => ({
      ...gym,
      operatingStatus: getOperatingStatus(gym),
    }));
  renderKey.value += 1;
  showLocationMarker();
};

// 검색 조건에 맞는지 확인
const isTrue = (gym) => {
  const matchesKeyword = !keyword.value || gym.name.includes(keyword.value) || gym.type.includes(keyword.value);
  const matchesType = selectedTypes.value.length === 0 || selectedTypes.value.includes(gym.type);
  return matchesKeyword && matchesType;
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

// 내 위치 찾기
const findMe = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = myLng;
      const lon = myLat;
      console.log(`내 위치: 위도 ${lat}, 경도 ${lon}`); // 내 위치 콘솔 출력
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

let map = null;
let myLocationMarker = null;

// 지도 초기화 함수
const initMap = function () {
  let myCenter = new kakao.maps.LatLng(myLng, myLat);

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = myLng;
      const lon = myLat;
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

// 경로 검색 및 거리 계산 함수
const calculateDistance = async (nearestStation, destination) => {
  const url = `https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json`;
  const headers = {
    appKey: "En2GXwXo2VadpQnX1x94WSIz6DDC3Nj4TofXTKFe",
    "Content-Type": "application/json",
  };
  const data = {
    startX: nearestStation.lng,
    startY: nearestStation.lat,
    endX: destination.lng,
    endY: destination.lat,
    reqCoordType: "WGS84GEO",
    resCoordType: "WGS84GEO",
    startName: "start",
    endName: "end",
  };

  try {
    const response = await axios.post(url, data, { headers });
    const distance = response.data.features[0].properties.totalDistance;
    return distance;
  } catch (error) {
    console.error("Error calculating distance:", error);
    return null;
  }
};

// 가장 가까운 지하철역 찾기 및 거리를 계산하는 함수
const findNearestStation = async (gym) => {
  const url = `https://dapi.kakao.com/v2/local/search/category.json?category_group_code=SW8&x=${gym.longitude}&y=${gym.latitude}&radius=2000&sort=distance`;
  const headers = {
    Authorization: "KakaoAK d00a06f293cd0dd71929545bb8a71c1c",
  };

  try {
    const response = await axios.get(url, { headers });
    if (response.data.documents.length > 0) {
      const stations = await Promise.all(
        response.data.documents.map(async (station) => {
          const nearestStation = {
            name: station.place_name, // 역 이름
            lat: station.y, // 위도
            lng: station.x, // 경도
            address: station.address_name, // 주소
          };
          const distance = await calculateDistance(nearestStation, { lng: gym.longitude, lat: gym.latitude });
          return { ...nearestStation, distance };
        })
      );

      // 거리가 가장 가까운 지하철 역 찾기
      const nearestStation = stations.reduce((prev, curr) => (prev.distance < curr.distance ? prev : curr));
      return nearestStation;
    } else {
      console.error("Nearby subway stations not found.");
      return null;
    }
  } catch (error) {
    console.error("Error finding nearest subway station:", error);
    return null;
  }
};

// 체육관 위치 마커 표시
const showLocationMarker = () => {
  const origin = { lat: myLat, lng: myLng };
  for (let i = 0; i < filteredGymList.value.length; i++) {
    const gym = filteredGymList.value[i];
    const destination = { lat: gym.latitude, lng: gym.longitude };

    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    const marker = new kakao.maps.Marker({
      map,
      position: location,
    });

    kakao.maps.event.addListener(marker, "click", () => {
      keyword.value = gym.name;
      filteredGymList.value = [gym]; // 리스트를 해당 체육관으로 업데이트
      showInfo(gym); // 체육관 정보 표시
      renderKey.value += 1; // 리스트 업데이트를 트리거
    });

    marker.setMap(map);
    markers.value.push(marker);
  }

  // 거리 기준으로 리스트 정렬
  filteredGymList.value.sort((a, b) => a.distance - b.distance);
  renderKey.value += 1;
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

// 마운트 시 실행
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    kakao.maps.load(initMap);
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=947c159c0ec8a7112a79473f83a0fa8a&libraries=services&autoload=false`;
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
      gymList.value = response.data.data.map((gym) => ({
        ...gym,
        operatingStatus: getOperatingStatus(gym),
      }));
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

// 체육관 정보 표시 함수
const showInfo = async (gym) => {
  if (selectedGym.value && selectedGym.value.gymId === gym.gymId) {
    selectedGym.value = null; // 클릭한 체육관이 이미 선택된 경우 닫기
  } else {
    selectedGym.value = gym; // 다른 체육관을 선택한 경우 해당 체육관을 표시
    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    map.setCenter(location);

    // 가장 가까운 지하철역 찾기
    const nearestStation = await findNearestStation(gym);

    // if (nearestStation) {
    //   console.log("show info near : ", nearestStation.lat, nearestStation.lng);
    //   console.log(`Distance from ${nearestStation.name} to ${gym.name}: ${nearestStation.distance} meters`);
    // } else {
    //   console.error("No nearest station found.");
    // }
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
  width: 450px;
  min-width: 450px;
  height: 100vh;
  overflow-y: auto;
  background-color: #ffffff;
  left: 0;
  top: 0;
  z-index: 100; /* 사이드바가 더 앞에 오도록 z-index 설정 */
  border: 0;
}

.search {
  padding: 10px 20px;
  border-bottom: 1px solid #ccc;
  position: fixed;
  top: 70px;
  left: 0;
  width: 450px;
  min-width: 450px;
  height: 115px;
  right: 0;
  background-color: #ffffff;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: top 0.5s ease, opacity 0.5s ease;
}

.search.fixed {
  top: 70px;
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
  padding: 0.4375rem 2.5rem 0.4375rem 2.7rem;
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
  right: 375px;
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
  width: 100%;
  cursor: pointer;
}

.items > div > div {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 0 15px;
  margin: 10px 0;
}
/* items */

/* gym-info */
.gym-info {
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 350px;
  min-width: 350px;
  height: 100%;
  position: absolute;
  left: 450px;
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
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 350px;
  min-width: 350px;
  height: 100%;
  position: absolute;
  left: 450px;
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

/* shadow-box 클래스 추가 */
.shadow-box {
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.3);
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

.detail-area {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 50px;
  font-size: 17px;
  font-weight: bold;
  color: black;
  font-family: "Playfair Display", serif;
  padding: 10px;
  border-bottom: 1px solid #8d8d8d4a;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  height: 64px;
  z-index: 4;
  position: relative;
}

/* 애니메이션 키 프레임 정의 */
@keyframes slideIn {
  0% {
    opacity: 0;
    transform: translateY(-50px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.list-item {
  animation: slideIn-7cfbf8c4 0.5s ease-in-out;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 14px;
  border-bottom: 1px solid #eaeaea;
  background-color: #ffffff;
  height: 360px;
  flex-direction: column;
}

.list-item > img {
  width: 340px;
  height: 195px;
  border-radius: 15px;
}

.inputbox:focus {
  outline: none;
  border-color: #1ed9ec;
}

.inputbox:focus + .inputbox {
  border-color: #25c3d3;
}

.gym-name {
  font-weight: bold;
}

.gym-content {
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  font-size: 15px;
}

.rating-area {
  display: flex;
}

.no-results {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  top: 150px;
  font-size: 20px;
  font-weight: bold;
  color: #353535;
  animation: slideIn-7cfbf8c4 1s ease-in-out;
}
</style>
