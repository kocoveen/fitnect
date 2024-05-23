<template>
  <transition name="slide-fade">
    <nav class="gym-info" v-if="gym">
      <div>{{ gym.name }}</div>
      <div>{{ gym.address }}</div>
      <button @click="$emit('hideInfo')">맵 닫기</button>
    </nav>
  </transition>
</template>

<script setup>
const props = defineProps({
  gym: Object,
});

const emit = defineEmits(["hideInfo"]);
</script>

<style scoped>
.gym-info {
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 300px;
  height: 100%;
  position: absolute;
  left: 400px;
  z-index: 50; /* 사이드바보다 뒤에 오도록 z-index 설정 */
  transition: transform 0.5s ease, opacity 0.5s ease;
}

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
</style>
