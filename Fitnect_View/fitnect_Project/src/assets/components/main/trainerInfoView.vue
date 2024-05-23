<template>
  <div>
    <h2>강의 정보</h2>
    <!-- 강의 정보 내용 -->
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
