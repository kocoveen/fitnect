<template>
  <div id="header" :class="{ 'background-header': isScrolled }">
    <div id="logo">
      <a href="/">
        <img src="@/assets/imgs/image.png" />
      </a>
    </div>
    <div id="navBar"></div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const isScrolled = ref(false);

// Scroll event handler
window.addEventListener("scroll", () => {
  // 페이지가 스크롤될 때 헤더에 클래스 추가
  isScrolled.value = window.scrollY > 0;
});
</script>

<style scoped>
/* Header */
#header {
  background-color: #000000bd;
  display: flex;
  height: 70px;
  min-width: 950px;
  align-items: center;
  justify-content: space-between;
  transition: all 0.5s ease 0s;
  position: relative; /* Add this line to make z-index work */
  z-index: 1000;
}

#logo {
  margin: 0 20px;
}

#logo > a > img {
  height: 50px;
}

#navBar {
  margin: 0 2%;
  display: flex;
  align-items: center;
  font-size: 15px;
  justify-content: space-between;
  min-width: 400px;
  width: auto;
}

.background-header {
  background-color: #0000004b !important;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
  z-index: 999; /* 필요한 경우 z-index 값 조정 */
}
</style>
