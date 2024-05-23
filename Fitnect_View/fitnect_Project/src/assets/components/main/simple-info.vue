<template>
  <div class="info-cont" style="background-color: rgb(245 245 245); min-height: 780px">
    <div class="simple-info" style="background-color: white">
      <div class="gym-image">
        <img :src="gym.gymImgUrl || defaultImg" alt="Gym Image" />
      </div>
      <div class="detail-info">
        <div class="name-space">
          <div>
            <div class="gym-name">{{ gym.name }}</div>
            <div class="gym-type">{{ gym.type }}</div>
          </div>
          <img class="star" :src="gym.isStarDown ? starDownImg : starUpImg" @click.stop="toggleStar(gym.gymId)" alt="Star" />
        </div>
        <div style="font-size: 13px; color: red; margin-top: 8px">{{ truncateContent(gym.content) }}</div>
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

    <!-- Navigation Bar -->
    <div class="nav-bar">
      <button @click="selectedTab = 'home'">홈</button>
      <button @click="selectedTab = 'facility'">시설</button>
      <button @click="selectedTab = 'lecture'">강의</button>
      <button @click="selectedTab = 'review'">리뷰</button>
    </div>

    <!-- Tabs Content -->
    <div v-if="selectedTab === 'home'" style="display: flex; flex-direction: column; align-items: center">
      <div class="address-care">
        <div>Info</div>
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18" class="DNzQ2" aria-hidden="true">
            <path
              d="M9 1C5.4 1 2.5 3.7 2.5 7.1c0 1.2.4 2.3 1 3.3l5.1 6.1c.2.2.6.2.8 0l5.1-6.1c.7-1 1-2.1 1-3.3C15.5 3.7 12.6 1 9 1zm0 8c-.8 0-1.5-.7-1.5-1.5S8.2 6 9 6s1.5.7 1.5 1.5S9.8 9 9 9z"
            ></path>
          </svg>
          {{ gym.address }}
        </div>
        <div class="station-info">
          <div v-if="lineInfo" style="align-items: center; display: flex; justify-content: flex-start; font-size: 12px">
            <div
              :style="{
                backgroundColor: lineInfo.color,
                display: 'flex',
                borderRadius: '50%',
                width: '15px',
                height: '15px',
                textAlign: 'center',
                lineHeight: '24px',
                marginRight: '5px',
                justifyContent: 'center',
                alignItems: 'center',
                fontSize: '10px',
                color: 'white',
              }"
            ></div>
            <div>{{ gym.nearestStation }}에서 {{ gym.stationDistance }} m</div>
          </div>
        </div>

        <div>
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18" class="DNzQ2" aria-hidden="true">
            <path
              d="M6.9 5.4c-.2.3-.2.6 0 .9.2.2.6.2.8 0l2.7-2.6v5.6H2.5V16c0 .3.3.6.6.6s.6-.3.6-.6v-5.6h7.9V3.6l2.7 2.6c.2.2.6.2.8 0 .2-.2.2-.6 0-.8l-3.9-3.8a.3.3 0 00-.4 0L6.9 5.4z"
            ></path>
          </svg>
          <div class="accordion">
            <button class="accordion-header" @click="toggleAccordion">경로 설명</button>
            <transition name="accordion">
              <div v-if="isAccordionOpen" class="accordion-content">
                <ul>
                  <li v-for="(description, index) in gym.pathDescriptions.split(', ')" :key="index">{{ description }}</li>
                </ul>
              </div>
            </transition>
          </div>
        </div>
        <div style="font-size: 14px; color: black; letter-spacing: -2px">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18" class="DNzQ2" aria-hidden="true">
            <path
              d="M13.8 15.6c-.2.2-.6.3-.9.3A14.8 14.8 0 012.1 5.1c-.1-.3 0-.6.3-.9l1.5-1.5c.4-.4 1-.4 1.4 0l2.5 2.5c.4.4.4 1 0 1.4v.1l-.7.8c-.4.3-.4.8-.2 1.2.8 1.2 1.9 2.2 3.1 2.9 0 0 .8-.7 1.3-1.3.4-.4 1-.4 1.4 0l2.5 2.5c.4.4.4 1 0 1.4l-1.4 1.4z"
            ></path>
          </svg>
          {{ gym.phone }}
        </div>
      </div>
      <FacilityInfo :gym="gym" />
      <LectureInfo :gym="gym" />
    </div>
    <div v-else-if="selectedTab === 'facility'" style="display: flex; flex-direction: column; align-items: center">
      <FacilityInfo :gym="gym" />
    </div>
    <div v-else-if="selectedTab === 'lecture'" style="display: flex; flex-direction: column; align-items: center">
      <LectureInfo :gym="gym" />
    </div>
    <div v-else-if="selectedTab === 'review'" style="display: flex; flex-direction: column; align-items: center"></div>

    <div class="info-footer">
      <div>
        <div>이용약관</div>
        <div>고객센터</div>
        <div>리뷰운영정책</div>
        <div>신고정책</div>
      </div>
      <div style="width: 50px"><img style="width: 100%; height: 100%" src="@/assets/imgs/image.png" /></div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, computed } from "vue";
import { lineColors } from "@/assets/js/LineColors"; // 경로를 맞게 설정해주세요
import SelectPayment from "@/assets/components/main/select-payment.vue";
import FacilityInfo from "@/assets/components/main/facilityInfoView.vue";
import LectureInfo from "@/assets/components/main/lectureInfoView.vue";
import TrainerInfo from "@/assets/components/main/trainerInfoView.vue";

import defaultImg from "@/assets/imgs/default.png";
import starDownImg from "@/assets/imgs/star-down.svg";
import starUpImg from "@/assets/imgs/star-up.svg";

const props = defineProps({
  gym: {
    type: Object,
    required: true,
  },
  toggleStar: {
    type: Function,
    required: true,
  },
  truncateContent: {
    type: Function,
    required: true,
  },
});

const selectedTab = ref("home");
const isAccordionOpen = ref(false);

const toggleAccordion = () => {
  isAccordionOpen.value = !isAccordionOpen.value;
};

const lineInfo = computed(() => {
  // gym.nearestStation에서 호선 이름을 추출하고 lineColors에서 정보를 찾습니다.
  const stationName = props.gym.nearestStation;
  if (!stationName) {
    return null;
  }

  for (const line in lineColors) {
    if (stationName.includes(line)) {
      return lineColors[line];
    }
  }
  return null;
});
</script>

<style scoped>
.simple-info {
  font-family: Arial, sans-serif;
  border-bottom: 1px solid #8d8d8d4a;
}

.simple-info h2 {
  margin-top: 0;
}

.simple-info p {
  margin: 5px 0;
}

.gym-image {
  width: 100%;
  height: 180px;
  display: flex;
  align-items: center;
}

.gym-image > img {
  width: 100%;
  height: 100%;
}

.detail-info {
  margin: 20px;
}

.name-space {
  display: flex;
  justify-content: space-between;
}

.name-space > div {
  display: flex;
  align-items: center;
}

.name-space > div > div:nth-child(1) {
  font-weight: bold;
  font-size: 18px;
  color: rgb(34, 33, 33);
  letter-spacing: -1px;
}

.name-space > div > div:nth-child(2) {
  margin-left: 8px;
  font-size: 15px;
  letter-spacing: -0.3px;
  color: rgb(143, 143, 143);
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
  position: relative;
  top: 1px;
}

div.gym-content > div > div:nth-child(1) > div {
  margin-left: 3px;
  font-weight: bold;
  color: black;
  position: relative;
  top: 1px;
}

.rating-area > div:nth-child(2),
.rating-area > div:nth-child(4),
.rating-area > div:nth-child(6) {
  margin: 0px 7px;
}

.rating-area > div:nth-child(5),
.rating-area > div:nth-child(7) {
  color: black;
  font-size: 12px;
  display: flex;
  align-items: center;
  position: relative;
  top: 1px;
}

/* Navigation Bar */
.nav-bar {
  display: flex;
  justify-content: space-around;
  height: 40px;
}

.nav-bar button {
  background-color: #ffffff;
  border: 1px solid #cccccc;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
  font-size: 12px;
  letter-spacing: -1px;
  color: #cccccc;
  font-weight: bold;
}

.nav-bar button:hover {
  background-color: #eeeeee;
  color: black;
}

.nav-bar button.active {
  background-color: #cccccc;
  color: black;
}

.info-footer {
  background-color: rgb(245 245 245);
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

.info-footer > div:nth-child(1) {
  display: flex;
  justify-content: space-evenly;
  font-size: 12px;
  align-items: center;
  width: 90%;
  margin: 10px;
  margin-top: 20px;
}

.info-footer > div:nth-child(2) {
  margin-bottom: 20px;
}

.address-care {
  width: 95%;
  background-color: white;
  margin-top: 10px;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 1px 1px 1px #adadad54;
}

.address-care > div:nth-child(1) {
  color: black;
  font-weight: bold;
  margin-bottom: 10px;
  font-size: 18px;
}

.address-care > div:nth-child(2) {
  font-size: 14px;
  color: black;
  letter-spacing: -2px;
}

div.address-care > div:nth-child(4) {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin: 20px 0;
}

.accordion-header {
  background-color: #ffffff;
  cursor: pointer;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
  width: 100%;
  border-radius: 5px;
  font-size: 14px;
  color: black;
  letter-spacing: -2px;
  padding: 3px !important;
}

.accordion-content {
  padding: 0 18px;
  background-color: white;
  overflow: hidden;
  border-radius: 5px;
}

.accordion-content ul {
  list-style-type: none;
  padding: 0;
}

.accordion-content li {
  font-size: 14px;
  color: rgb(144, 144, 144);
  letter-spacing: -2px;
  padding: 10px 0;
  border-bottom: 1px solid #ddd;
}

.accordion-content li:last-child {
  border-bottom: none;
}

.accordion-enter-active,
.accordion-leave-active {
  transition: all 0.4s ease;
}

.accordion-enter-from,
.accordion-leave-to {
  max-height: 0;
  opacity: 0;
}

.DNzQ2 {
  display: inline-block;
  vertical-align: top;
  fill: #c5c5c7;
  width: 18px;
  height: 18px;
  margin-top: 1px;
}
</style>
