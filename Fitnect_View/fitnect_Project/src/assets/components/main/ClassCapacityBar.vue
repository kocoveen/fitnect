<template>
  <div class="bar-container">
    <div class="bar-background">
      <div class="bar-foreground" :style="{ width: barWidth + '%' }"></div>
    </div>
    <div class="bar-label">{{ current }}/{{ maximum }}</div>
  </div>
</template>

<script setup>
import { defineProps, computed } from "vue";

const props = defineProps({
  current: {
    type: Number,
    required: true,
  },
  maximum: {
    type: Number,
    required: true,
  },
});

const barWidth = computed(() => (props.current / props.maximum) * 100);
</script>

<style scoped>
.bar-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 10px;
  width: 100%;
}

.bar-background {
  background-color: #e0e0e0;
  border-radius: 5px;
  width: 80%;
  height: 20px;
  position: relative;
}

.bar-foreground {
  background-color: #4caf50;
  height: 100%;
  border-radius: 5px;
}

.bar-label {
  margin-left: 10px;
  font-size: 14px;
  color: #555;
}
</style>
