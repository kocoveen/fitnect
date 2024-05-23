<template>
  <div class="class-info">
    <div style="color: black; font-weight: bold; margin-bottom: 10px; display: flex; justify-content: space-between">
      <div>강의 정보</div>
      <a @click="togglePayment" style="font-size: 13px; color: #15b9d9; display: flex; justify-content: center; align-items: center; cursor: pointer"
        >수강 신청</a
      >
    </div>

    <div v-show="isPaymentVisible">
      <SelectPayment
        :gym="gym"
        style="padding: 15px; border: 1px solid #a9a9a99c; border-radius: 8px; margin-top: 15px; box-shadow: 1px 1px 1px #4d4d4d2b"
      />
    </div>

    <div v-if="gym.classes && gym.classes.length" style="margin-top: 20px">
      <div v-for="classInfo in gym.classes" :key="classInfo.classId" class="class-item">
        <div class="class-header">
          <div style="color: black; font-weight: bold; margin-bottom: 10px; font-size: 13px">{{ classInfo.className }}</div>
        </div>

        <div class="class-calendar">
          <vue-cal
            class="custom-calendar"
            :events="[{ start: classInfo.startDate, end: classInfo.endDate, title: classInfo.className }]"
            default-view="month"
            :disable-views="['years', 'year', 'week', 'day', 'schedule']"
            :locale="locale"
            :weekdays="weekdays"
            :cell-classes="cellClasses"
          />
        </div>
        <div class="class-details" style="display: flex">
          <class-capacity-chart :current="classInfo.current" :maximum="classInfo.maximum" />
        </div>
        <div
          style="
            display: flex;
            justify-content: flex-end;
            margin-top: 30px;
            color: #313131e8;
            font-weight: bold;
            margin-bottom: 10px;
            font-size: 13px;
          "
        >
          회원권 {{ formatPrice(classInfo.classPrice) }}
        </div>
      </div>
    </div>
    <div v-else>
      <p>등록된 강의가 없습니다.</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref } from "vue";
import ClassCapacityChart from "@/assets/components/main/ClassCapacityBar.vue";
import VueCal from "vue-cal";
import "vue-cal/dist/vuecal.css";
import SelectPayment from "@/assets/components/main/select-payment.vue";

const props = defineProps({
  gym: {
    type: Object,
    required: true,
  },
});

// isPaymentVisible 변수를 생성하여 기본적으로 false로 설정합니다.
const isPaymentVisible = ref(false);

const togglePayment = () => {
  // isPaymentVisible 변수의 값을 토글합니다.
  isPaymentVisible.value = !isPaymentVisible.value;
};
// Locale settings for VueCal
const locale = {
  weekdays: ["월", "화", "수", "목", "금", "토", "일"],
  months: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
  years: "연도",
  year: "년",
  month: "월",
  week: "주",
  day: "일",
  schedule: "일정",
};

const weekdays = ["월", "화", "수", "목", "금", "토", "일"];

const cellClasses = (cell) => {
  return {
    "custom-cell": true,
    "has-event": cell.events.length > 0,
  };
};

// 날짜 형식 변환 함수
const formatDate = (dateString) => {
  const options = { year: "numeric", month: "short", day: "numeric" };
  return new Date(dateString).toLocaleDateString("ko-KR", options);
};

// 가격 형식 변환 함수
const formatPrice = (price) => {
  return new Intl.NumberFormat("ko-KR", { style: "currency", currency: "KRW" }).format(price);
};
</script>

<style scoped>
.class-info {
  font-family: Arial, sans-serif;
  width: 95%;
  background-color: white;
  margin-top: 10px;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 1px 1px 1px #adadad54;
}

.class-info h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.class-item {
  border: 1px solid #ccc;
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.class-header h3 {
  margin: 0;
  font-size: 18px;
}

.class-header p {
  margin: 5px 0 10px;
  color: #888;
}

.class-details p {
  margin: 5px 0;
}

.class-calendar {
  margin-top: 10px;
}

.custom-calendar .vuecal__flex,
.custom-calendar .vuecal__menu {
  display: none;
}

.custom-calendar {
  width: 100%;
  height: auto;
  border-radius: 10px;
  font-size: 11px;
  margin-top: 20px;
}

.custom-calendar .vuecal__cell {
  padding: 2px;
  font-size: 0.8rem;
}

.custom-calendar .vuecal__event {
  font-size: 0.8rem;
  padding: 2px;
}

.custom-calendar .vuecal__weekdays div:nth-child(1)::after {
  content: "월";
}

.custom-calendar .vuecal__weekdays div:nth-child(2)::after {
  content: "화";
}

.custom-calendar .vuecal__weekdays div:nth-child(3)::after {
  content: "수";
}

.custom-calendar .vuecal__weekdays div:nth-child(4)::after {
  content: "목";
}

.custom-calendar .vuecal__weekdays div:nth-child(5)::after {
  content: "금";
}

.custom-calendar .vuecal__weekdays div:nth-child(6)::after {
  content: "토";
}

.custom-calendar .vuecal__weekdays div:nth-child(7)::after {
  content: "일";
}

.vuecal__cell-events-count {
  background-color: red;
}

.vuecal__view-btn .vuecal__view-btn--active {
  font-size: 13px;
}
</style>
