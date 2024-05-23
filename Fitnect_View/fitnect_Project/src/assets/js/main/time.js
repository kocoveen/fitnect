// src/utils/time.js

export const getOperatingStatus = (gym) => {
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
