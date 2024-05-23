<template>
  <div :class="['detail-search-info', { show: isShown }]">
    <h2>상세 검색</h2>
    <div class="input-wrapper">
      <input class="inputbox" v-model="detailKeyword" placeholder="상세 검색어 입력" />
      <button @click="search">검색</button>
    </div>
    <div class="additional-info">
      <div>
        <label for="type">유형:</label>
        <input id="type" v-model="type" placeholder="유형 입력" />
      </div>
      <div>
        <label for="location">위치:</label>
        <input id="location" v-model="location" placeholder="위치 입력" />
      </div>
    </div>
    <button @click="close">닫기</button>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits, watch } from "vue";

const props = defineProps({
  isShown: {
    type: Boolean,
    default: false,
  },
});

const detailKeyword = ref("");
const type = ref("");
const location = ref("");

const emit = defineEmits(["close-detail-search"]);

const search = () => {
  console.log("상세 검색어:", detailKeyword.value);
  console.log("유형:", type.value);
  console.log("위치:", location.value);
};

const close = () => {
  emit("close-detail-search");
};
</script>

<style scoped>
.detail-search-info {
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid #ccc;
  width: 300px;
  height: 100%;
  position: fixed;
  top: 0;
  right: -300px;
  z-index: 2;
  transition: right 0.3s ease;
}

.detail-search-info.show {
  right: 0;
}

.input-wrapper {
  margin-bottom: 10px;
}

.inputbox {
  padding: 0.4375rem;
  font-size: 0.8rem;
  border: 1px solid #d9dee3;
  border-radius: 5px;
  width: 100%;
}

.additional-info > div {
  margin-bottom: 10px;
}
</style>
