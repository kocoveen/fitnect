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
          <div>
            <img src="@/assets/imgs/origin.svg" style="width: 20px; height: 20px" />
            <a class="find-me" @click="findMe">내 위치</a>
          </div>
          <a class="detail-button" @click="toggleDetailSearch"> 상세 검색 </a>
        </div>
      </div>
      <transition-group name="slide" tag="div" class="items" :key="renderKey">
        <div v-for="gym in filteredGymList" :key="gym.gymId" class="list-item">
          <img :src="gym.gymImgUrl || defaultImg" alt="Gym Image" class="gym-image" />
          <div class="item" @click="showInfo(gym)">
            <div class="gym-name-info">
              <div>
                <div class="gym-name">{{ gym.name }}</div>
                <div>{{ gym.type }}</div>
              </div>
              <img class="star" :src="gym.isStarDown ? starDownImg : starUpImg" @click.stop="toggleStar(gym.gymId)" alt="Star" />
            </div>
            <div style="font-size: 12px; color: red">{{ truncateContent(gym.content) }}</div>
            <div class="gym-content">
              <div class="rating-area">
                <div>
                  <img src="@/assets/imgs/star.svg" style="width: 20px; height: 20px" />
                  <div>
                    {{ gym.rating }}
                  </div>
                </div>
                <div><img src="@/assets/imgs/circle.svg" style="width: 4px; height: 4px" /></div>
                <div>{{ gym.operatingStatus }}</div>
                <div><img src="@/assets/imgs/circle.svg" style="width: 4px; height: 4px" /></div>
                <div>리뷰 {{ gym.reviewCount }}</div>
                <div><img src="@/assets/imgs/circle.svg" style="width: 4px; height: 4px" /></div>
                <div>{{ gym.formattedDistance }}</div>
              </div>
            </div>
          </div>
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
import { useReviewStore } from "@/stores/review";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

import DropDown from "@/assets/components/main/dropdown-menu.vue";
import SimpleInfo from "@/assets/components/main/simple-info.vue";
import defaultImg from "@/assets/imgs/default.png";

const reviewStore = useReviewStore();
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

const truncateContent = (content) => {
  const maxLength = 30;
  if (content.length > maxLength) {
    return content.substring(0, maxLength) + "...";
  }
  return content;
};

import starDownImg from "@/assets/imgs/star-down.svg";
import starUpImg from "@/assets/imgs/star-up.svg";

// 체육관 데이터에 isStarDown 속성 추가
gymList.value = gymList.value.map((gym) => ({
  ...gym,
  isStarDown: true, // 기본값 설정
}));

// 즐겨찾기 토글
const toggleStar = (gymId) => {
  const gymIndex = gymList.value.findIndex((g) => g.gymId === gymId);
  if (gymIndex !== -1) {
    gymList.value[gymIndex].isStarDown = !gymList.value[gymIndex].isStarDown;
    // filteredGymList도 업데이트
    const filteredGymIndex = filteredGymList.value.findIndex((g) => g.gymId === gymId);
    if (filteredGymIndex !== -1) {
      filteredGymList.value[filteredGymIndex].isStarDown = gymList.value[gymIndex].isStarDown;
    }
  }
};

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

const getReviewCount = (gymId) => {
  const reviews = reviewStore.reviews[gymId] || [];
  console.log(`Review count for gymId ${gymId}:`, reviews.length); // 리뷰 카운트 콘솔 출력
  return reviews.length;
};

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
  console.log("After filtering, filteredGymList:", filteredGymList.value); // 필터링 후 filteredGymList 확인
  renderKey.value += 1;

  // Kakao Maps SDK가 로드된 후에만 마커를 표시
  if (window.kakao && window.kakao.maps) {
    showLocationMarker();
  } else {
    console.error("Kakao Maps SDK is not loaded");
  }
};

// 검색 조건에 맞는지 확인
const isTrue = (gym) => {
  const matchesKeyword = !keyword.value || gym.name.includes(keyword.value) || gym.type.includes(keyword.value);
  const matchesType = selectedTypes.value.length === 0 || selectedTypes.value.includes(gym.type);
  console.log("matchesKeyword:", matchesKeyword, "matchesType:", matchesType, "gym:", gym);
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
      map.setLevel(4); // 지도 확대 수준 초기화

      createMyLocationMarker(myCenter); // 내 위치 마커 생성

      // 마커 클릭 시 findMe 함수 호출
      kakao.maps.event.addListener(myLocationMarker, "click", findMe);

      // 기존 경로 애니메이션 중단 및 제거
      if (polyline) {
        polyline.setMap(null);
        polyline = null; // 기존 polyline 객체 제거
      }

      // 기존 오버레이 제거
      if (customOverlay) {
        customOverlay.setMap(null);
        customOverlay = null; // 기존 customOverlay 객체 제거
      }
    });
  } else {
    alert("Geolocation is not supported by this browser.");
  }
};

// 내 위치 마커 설정 함수
const createMyLocationMarker = (position) => {
  const markerImage = new kakao.maps.MarkerImage(
    "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
    new kakao.maps.Size(24, 35),
    {
      offset: new kakao.maps.Point(12, 35),
      alt: "내 위치", // 마커 이미지에 마우스를 올릴 때 나타나는 대체 텍스트
    }
  );

  if (myLocationMarker) {
    myLocationMarker.setMap(null);
  }

  myLocationMarker = new kakao.maps.Marker({
    position,
    image: markerImage,
    map,
  });

  myLocationMarker.setMap(map);
};

let map = null;
let myLocationMarker = null;
let polyline = null; // 전역 변수로 선언

// Kakao Maps SDK 로드 함수
const loadKakaoMap = () => {
  return new Promise((resolve, reject) => {
    if (window.kakao && window.kakao.maps) {
      resolve(); // Kakao Maps SDK가 이미 로드된 경우
    } else {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=947c159c0ec8a7112a79473f83a0fa8a&libraries=services&autoload=false`;
      script.onload = () => {
        if (window.kakao && window.kakao.maps) {
          kakao.maps.load(() => {
            resolve();
          });
        } else {
          reject(new Error("Kakao Maps SDK failed to load"));
        }
      };
      script.onerror = (error) => {
        reject(error);
      };
      document.head.appendChild(script);
    }
  });
};

// 지도 초기화 함수
const initMap = function () {
  if (!window.kakao || !window.kakao.maps) {
    console.error("Kakao Maps SDK is not loaded");
    return;
  }

  const myCenter = new kakao.maps.LatLng(myLng, myLat);

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

  // 내 위치 마커 생성
  createMyLocationMarker(myCenter);
  showLocationMarker();
};

// 체육관 위치 마커 표시
const showLocationMarker = () => {
  // 수정된 부분: Kakao Maps SDK가 로드되었는지 확인 후 마커 표시
  if (!window.kakao || !window.kakao.maps) {
    console.error("Kakao Maps SDK is not loaded");
    return;
  }

  const origin = { lat: myLat, lng: myLng };
  for (let i = 0; i < filteredGymList.value.length; i++) {
    const gym = filteredGymList.value[i];
    const destination = { lat: gym.latitude, lng: gym.longitude };

    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    const markerImageSrc = getMarkerImageByType(gym.type); // 타입에 따른 마커 이미지 가져오기
    const markerImage = new kakao.maps.MarkerImage(markerImageSrc, new kakao.maps.Size(24, 35));

    const marker = new kakao.maps.Marker({
      map,
      position: location,
      image: markerImage, // 마커 이미지 설정
    });

    kakao.maps.event.addListener(marker, "click", () => {
      showInfo(gym); // 체육관 정보 표시
    });

    marker.setMap(map);
    marker.gymId = gym.gymId; // 마커 객체에 체육관 ID 저장
    markers.value.push(marker);
  }
  // 거리 기준으로 리스트 정렬
  filteredGymList.value.sort((a, b) => a.distance - b.distance);
  renderKey.value += 1;
};

// 체육관 타입에 따른 마커 이미지 설정 함수
const getMarkerImageByType = (type) => {
  const images = {
    헬스: "src/assets/imgs/star.svg",
    필라테스: "src/assets/imgs/star.svg",
    요가: "src/assets/imgs/star.svg",
    // 다른 체육관 타입에 대한 이미지를 추가하세요.
  };
  return images[type] || "src/assets/imgs/star.svg"; // 기본 이미지 설정
};

// 체육관 정보 표시 함수
const showInfo = async (gym) => {
  if (selectedGym.value && selectedGym.value.gymId === gym.gymId) {
    selectedGym.value = null; // 클릭한 체육관이 이미 선택된 경우 닫기
  } else {
    selectedGym.value = gym; // 다른 체육관을 선택한 경우 해당 체육관을 표시
    const location = new kakao.maps.LatLng(gym.latitude, gym.longitude);
    map.setCenter(location);
    map.setLevel(4);

    // 기존 경로 애니메이션 중단 및 제거
    if (polyline) {
      polyline.setMap(null);
      polyline = null; // 기존 polyline 객체 제거
    }

    // 가장 가까운 지하철역 찾기
    const nearestStation = await findNearestStation(gym);

    if (nearestStation) {
      console.log(gym.latitude, gym.longitude);
      console.log("nearestStation : ", nearestStation.name);
      console.log("show info near : ", nearestStation.lat, nearestStation.lng);
      console.log(`Distance from ${nearestStation.name} to ${gym.name}: ${nearestStation.distance} meters`);

      // 새로운 경로 표시
      const { path, distance } = await calculateDistance(nearestStation, { lng: gym.longitude, lat: gym.latitude });
      if (path.length > 0) {
        drawPath(path, nearestStation);
      } else {
        console.error("No path found");
      }
    } else {
      console.error("No nearest station found.");
    }
  }
  selectOption.value = false;
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
      const nearestStation = {
        name: response.data.documents[0].place_name,
        lat: response.data.documents[0].y,
        lng: response.data.documents[0].x,
        address: response.data.documents[0].address_name,
        line: response.data.documents[0].category_name.split(" > ")[1], // 카테고리 이름에서 호선 번호 추출
      };
      const distance = await calculateDistance(nearestStation, { lng: gym.longitude, lat: gym.latitude });
      return { ...nearestStation, distance };
    } else {
      console.error("Nearby subway stations not found.");
      return null;
    }
  } catch (error) {
    console.error("Error finding nearest subway station:", error);
    return null;
  }
};

let customOverlay = null; // 전역 변수로 선언하여 오버레이를 추적

const drawPath = (path, nearestStation) => {
  if (!nearestStation) {
    console.error("Nearest station is not provided.");
    return;
  }

  const linePath = path.map((point) => new kakao.maps.LatLng(point.lat, point.lng));

  if (polyline) {
    polyline.setMap(null);
  }

  polyline = new kakao.maps.Polyline({
    path: linePath,
    strokeWeight: 5,
    strokeColor: "#FF0000",
    strokeOpacity: 0.7,
    strokeStyle: "solid",
  });
  polyline.setMap(map);

  // 기존 오버레이 제거
  if (customOverlay) {
    customOverlay.setMap(null);
  }

  // 지하철역 호선 정보 추출
  const stationName = nearestStation.name;
  const lineMatch = stationName.match(/(\d+호선|수인분당선)/);
  const line = lineMatch ? lineMatch[1] : "Unknown";

  // 호선 번호를 표시하는 커스텀 오버레이
  const lineColor = getLineColor(line); // 호선 번호에 따라 색상 선택
  const displayLine = line === "수인분당선" ? "수인분당" : line.replace("호선", "");
  customOverlay = new kakao.maps.CustomOverlay({
    map: map,
    position: new kakao.maps.LatLng(nearestStation.lat, nearestStation.lng),
    content: `<div class="station-label" style="border: 3px solid ${lineColor}; color: ${lineColor}; background-color: white; padding: 5px; border-radius: 15px; width: 60px; height: 30px; display: flex; justify-content: center; align-items: center; font-weight: bold; position: relative; font-size: 11px;">${displayLine}</div>`,
    yAnchor: 1,
  });
};

// 호선 번호에 따른 색상을 반환하는 함수
const getLineColor = (line) => {
  const colors = {
    "1호선": "#0052A4",
    "2호선": "#009D3E",
    "3호선": "#EF7C1C",
    "4호선": "#00A5DE",
    "5호선": "#996CAC",
    "6호선": "#CD7C2F",
    "7호선": "#747F00",
    "8호선": "#EA545D",
    "9호선": "#BDB092",
    수인분당선: "#FABE00",
    // 다른 호선에 대한 색상을 추가할 수 있습니다.
  };
  return colors[line] || "#000000"; // 기본 색상은 검정색
};

// 전체 경로를 계산하고 애니메이션을 시작하는 함수
const calculateDistance = async (nearestStation, destination) => {
  const url = `https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json`;
  const headers = {
    "Content-Type": "application/json",
    appKey: "wgc6OHSHci6FKr6bBMenY3y6vkQnVx633QkglQgN",
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
    const features = response.data.features;
    const distance = features[0].properties.totalDistance;

    let path = [];

    // 모든 피처의 지오메트리를 반복하여 경로 생성
    features.forEach((feature) => {
      const geometry = feature.geometry;

      if (geometry.type === "Point") {
        // 'Point' 타입의 경우, 'coordinates'는 [경도, 위도] 형식의 배열
        path.push({
          lng: geometry.coordinates[0],
          lat: geometry.coordinates[1],
        });
      } else if (geometry.type === "LineString") {
        // 'LineString' 타입의 경우, 'coordinates'는 [[경도, 위도], [경도, 위도], ...] 형식의 2차원 배열
        geometry.coordinates.forEach((coord) => {
          path.push({
            lng: coord[0],
            lat: coord[1],
          });
        });
      } else {
        console.error("지원되지 않는 지오메트리 타입:", geometry.type);
      }
    });

    return { path, distance }; // 경로와 거리를 반환
  } catch (error) {
    console.error("거리 계산 오류:", error);
    return { path: [], distance: 0 }; // 오류 발생 시 빈 배열과 거리 0 반환
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

const formatDistance = (distance) => {
  if (distance < 1000) {
    return `${Math.round(distance)} m`;
  } else {
    return `${(distance / 1000).toFixed(1)} km`;
  }
};

onMounted(async () => {
  const queryKeyword = route.query.keyword;
  if (queryKeyword) {
    keyword.value = queryKeyword;
  }

  try {
    const response = await axios.get(`http://localhost:8080/gym/with-asso`, {
      headers: {
        Authorization: `bearer ${sessionStorage.getItem("accessToken")}`,
      },
    });

    gymList.value = response.data.data.map((gym) => ({
      ...gym,
      isStarDown: true, // 기본값 설정
      operatingStatus: getOperatingStatus(gym),
    }));

    console.log("gymList before review fetch:", gymList.value); // gymList가 올바르게 설정되었는지 확인

    // 리뷰 데이터를 가져와서 gymList에 리뷰 카운트 추가
    await Promise.all(
      gymList.value.map(async (gym) => {
        await reviewStore.fetchReviews(gym.gymId);
        console.log(`Reviews for gymId ${gym.gymId}:`, reviewStore.reviews[gym.gymId]);
        gym.reviewCount = getReviewCount(gym.gymId); // 리뷰 카운트 추가
        console.log("gym.reviewCount : ", gym.reviewCount);
      })
    );

    // 거리 계산 및 포맷팅
    await Promise.all(
      gymList.value.map(async (gym) => {
        const nearestStation = { lat: myLng, lng: myLat };
        const destination = { lat: gym.latitude, lng: gym.longitude };
        const { path, distance } = await calculateDistance(nearestStation, destination);
        gym.distance = distance;
        gym.formattedDistance = formatDistance(distance);
      })
    );

    // 리뷰 개수가 추가된 후 gymList와 filteredGymList 업데이트
    gymList.value = [...gymList.value]; // 트리거를 위해 새로운 배열 할당
    filteredGymList.value = gymList.value.filter((gym) => isTrue(gym));
    console.log("filteredGymList after review fetch:", filteredGymList.value); // filteredGymList가 올바르게 설정되었는지 확인

    // Kakao Maps 로드 완료 후 마커 표시
    await loadKakaoMap();
    initMap();
    isTrueRender(); // Kakao Maps SDK 로드 후에 isTrueRender 호출
  } catch (error) {
    console.error("Error fetching gyms:", error);
  }

  window.addEventListener("scroll", handleScroll);
});

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
  width: 315px;
  min-width: 315px;
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
  width: 315px;
  min-width: 315px;
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
  background: #3787dd69;
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

.inputbox::placeholder {
  color: #a0a0a0;
  opacity: 0.7;
}

.search-icon {
  position: absolute;
  top: 50%;
  right: 237px;
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
  color: #ffffff;
  cursor: pointer;
  transition: color 0.2s ease;
  font-size: 14px;
}

.detail-button:hover {
  color: #ffffff8a;
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
  align-items: flex-start;
  flex-direction: column;
  padding: 0 15px;
  background-color: #f5f5f5;
  padding: 8px 15px;
  border-radius: 15px;
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
  left: 315px;
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
  color: #ffffff;
  cursor: pointer;
  margin-left: 1px;
  position: relative;
  top: 1px;
  font-size: 14px;
}
/* detail-search-options */
.detail-search-options {
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 350px;
  min-width: 350px;
  height: 100%;
  position: absolute;
  left: 315px;
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

div.search.fixed > div.detail-search > div {
  display: flex;
  justify-content: center;
  align-items: center;
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
  height: 320px;
  flex-direction: column;
  transition: background-color 0.3s ease;
}

.list-item:hover {
  background-color: #f0f8ff; /* 호버 시 색상 변경 */
}

.list-item:focus {
  background-color: #f0f8ff; /* 포커스 시 색상 변경 */
}

.list-item > img {
  width: 280px;
  height: 180px;
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
  font-size: 14px;
  color: rgb(0, 104, 195);
  letter-spacing: -1px;
}

.gym-name-info {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin: 5px 0;
}
.gym-name-info > div:nth-child(1) {
  display: flex;
  align-items: center;
  justify-content: center;
}

div.gym-name-info > div > div:nth-child(2) {
  margin-left: 5px;
  font-size: 12px;
  letter-spacing: -0.3px;
  color: rgb(143, 143, 143);
}

.gym-content {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  font-size: 15px;
}

.rating-area {
  display: flex;
  width: 100%;
  height: 25px;
  color: black;
}
.rating-area > div:nth-child(3) {
  font-size: 12px;
  letter-spacing: -1px;
  display: flex;
  align-items: center;
  position: relative;
  top: 1px;
}

.rating-area > div:nth-child(1) {
  display: flex;
  align-items: center;
  font-size: 13px;
  letter-spacing: -1px;
}

div.gym-content > div > div:nth-child(1) > div {
  margin-left: 3px;
  font-weight: bold;
  color: black;
}

.rating-area > div:nth-child(2),
.rating-area > div:nth-child(4),
.rating-area > div:nth-child(6) {
  margin: 0px 10px;
}

.rating-area > div:nth-child(5),
.rating-area > div:nth-child(7) {
  color: black;
  font-size: 12px;
  display: flex;
  align-items: center;
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

/* Loading */
.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.loading-text {
  font-size: 24px;
  font-weight: bold;
  color: #44b7ce;
}
</style>
