<template>
  <div class="facility-info">
    <div class="amenities">
      <div style="color: black; font-weight: bold; margin-bottom: 10px">편의시설</div>
      <ul>
        <li v-if="gym.amenity.freeParking">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M12 2C6.48 2 2 6.48 2 12C2 17.52 6.48 22 12 22C17.52 22 22 17.52 22 12C22 6.48 17.52 2 12 2ZM12 19C8.69 19 6 16.31 6 13C6 9.69 8.69 7 12 7C15.31 7 18 9.69 18 13C18 16.31 15.31 19 12 19ZM12 9C10.34 9 9 10.34 9 12C9 13.66 10.34 15 12 15C13.66 15 15 13.66 15 12C15 10.34 13.66 9 12 9ZM13 12H11V10H13V12Z"
              fill="#000000"
            />
          </svg>
          무료 주차
        </li>
        <li v-if="gym.amenity.paidParking">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M12 2C6.48 2 2 6.48 2 12C2 17.52 6.48 22 12 22C17.52 22 22 17.52 22 12C22 6.48 17.52 2 12 2ZM13 17H11V13H9V11H11V7H13V11H15V13H13V17Z"
              fill="#000000"
            />
          </svg>
          유료 주차
        </li>
        <li v-if="gym.amenity.allDay">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M12 4V1L8 5L12 9V6C15.31 6 18 8.69 18 12C18 15.31 15.31 18 12 18C8.69 18 6 15.31 6 12H4C4 16.42 7.58 20 12 20C16.42 20 20 16.42 20 12C20 7.58 16.42 4 12 4ZM11 10V13H13V10H11ZM11 14V16H13V14H11Z"
              fill="#000000"
            />
          </svg>
          24시간 운영
        </li>
        <li v-if="gym.amenity.showerFacilities">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M16 13H8V15H16V13ZM16 17H8V19H16V17ZM12 3V5H14V3H12ZM10 3H12V5H10V3ZM8 3V5H10V3H8ZM6 3H8V5H6V3ZM14 1V3H16V1H14ZM10 1V3H12V1H10ZM8 1V3H10V1H8ZM12 7H10V9H12V7ZM14 7V9H16V7H14ZM6 7V9H8V7H6ZM16 3H14V5H16V3ZM8 7H6V9H8V7ZM8 5H6V7H8V5ZM12 5V7H14V5H12Z"
              fill="#000000"
            />
          </svg>
          샤워 시설
        </li>
        <li v-if="gym.amenity.sportsWear">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M14 2V4H16V6H14V8H10V6H8V4H10V2H14ZM14 6V4H10V6H14ZM20 8H18V20H16V8H14V20H10V8H8V20H6V8H4V20H2V22H22V20H20V8Z" fill="#000000" />
          </svg>
          운동복 대여
        </li>
        <li v-if="gym.amenity.sharedLocker">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M20 4V2H4V4H2V22H4V4H20ZM6 20V6H18V20H6ZM8 8V18H10V8H8ZM14 8V18H16V8H14Z" fill="#000000" />
          </svg>
          공용 라커
        </li>
        <li v-if="gym.amenity.personalLocker">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M6 2V4H18V2H6ZM6 22H18V20H6V22ZM4 6V18H20V6H4ZM8 8H16V16H8V8Z" fill="#000000" />
          </svg>
          개인 라커
        </li>
        <li v-if="gym.amenity.unmanded">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M12 2C9.79 2 8 3.79 8 6H4V8H6V14H4V16H8V18H4V20H8V22H10V20H14V22H16V20H20V18H16V16H20V14H18V8H20V6H16C16 3.79 14.21 2 12 2ZM10 4H14C14.55 4 15 4.45 15 5H9C9 4.45 9.45 4 10 4ZM14 18H10V16H14V18ZM16 14H8V8H16V14Z"
              fill="#000000"
            />
          </svg>
          무인 시스템
        </li>
      </ul>
    </div>

    <div class="gym-machines">
      <div style="color: black; font-weight: bold; margin: 10px 0">기구 & 용품</div>
      <ul>
        <li v-for="machine in gym.gymMachine" :key="machine.infoId">{{ machine.machineName }}: {{ machine.machineCount }}개</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  gym: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.facility-info {
  font-family: Arial, sans-serif;
  width: 95%;
  background-color: white;
  margin-top: 10px;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 1px 1px 1px #adadad54;
}

.facility-info h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.amenities,
.gym-machines {
  margin-bottom: 15px;
}

.amenities ul,
.gym-machines ul {
  list-style-type: none;
  padding: 0;
  display: flex;
  flex-wrap: wrap;
}

.amenities li,
.gym-machines li {
  font-size: 14px;
  color: black;
  letter-spacing: -2px;
  display: flex;
  align-items: center;
  width: 50%; /* 한 줄에 두 개씩 나오게 설정 */
}

svg {
  width: 18px;
  height: 18px;
  margin-right: 8px; /* 아이콘과 텍스트 사이에 여백 추가 */
}

svg > path {
  fill: #c5c5c7;
}
</style>
