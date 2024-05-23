<template>
  <div class="search">
    <div class="search-area">
      <div class="input-wrapper">
        <input class="inputbox" v-model="localKeyword" placeholder="검색어 입력" @keyup.enter="handleSearch" />
        <div class="search-icon" @click="handleSearch">
          <img src="@/assets/imgs/search.svg" />
        </div>
      </div>
    </div>
    <div class="detail-search">
      <a class="detail-button" @click="$emit('toggleDetailSearch')">상세 검색</a>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  keyword: String,
});

const emit = defineEmits(["search", "toggleDetailSearch"]);

const localKeyword = ref(props.keyword);

watch(localKeyword, (newKeyword) => {
  emit("search", newKeyword);
});

const handleSearch = () => {
  emit("search", localKeyword.value);
};
</script>

<style scoped>
.search {
  padding: 10px 20px;
  border-bottom: 1px solid #ccc;
  position: fixed;
  top: 110px;
  left: 0;
  width: 400px;
  min-width: 400px;
  height: 115px;
  right: 0;
  background-color: #ffffff;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
  padding: 0.4375rem 2.5rem 0.4375rem 0.875rem;
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
  right: 10px;
  transform: translateY(-50%);
  width: 24px;
  height: 24px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

.detail-search {
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
  height: 30%;
  font-size: 13px;
}

.detail-button {
  color: #44b7ce;
  cursor: pointer;
}
</style>
