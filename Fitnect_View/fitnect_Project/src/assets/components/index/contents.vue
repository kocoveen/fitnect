<template>
  <div>
    <div>
      <video autoplay="" muted="" loop="" id="bg-video">
        <source src="@/assets/imgs/gym-video.mp4" type="video/mp4" />
      </video>
    </div>
    <div id="body">
      <div class="caption">
        <h6>Fitness + Connect</h6>
        <h2>사람과 <em>건강</em>을 연결하다</h2>
        <div class="main-button scroll-to-section">
          <input type="text" v-model="searchKeyword" @keyup.enter="goToMainView" placeholder="가까운 점포 찾기..." />
          <div class="search-icon" @click="goToMainView">
            <img src="@/assets/imgs/search.svg" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const searchKeyword = ref("");

const goToMainView = () => {
  router.push({ name: "main", query: { keyword: searchKeyword.value } });
};
</script>

<style scoped>
/* Video */
#bg-video {
  min-width: 100%;
  min-height: 100vh;
  max-width: 100%;
  max-height: 100vh;
  object-fit: cover;
}

/* body */
#body {
  position: absolute;
  background-color: rgb(56 62 73 / 80%);
  top: 0;
  left: 0;
  bottom: 7px;
  width: 100%;
  min-width: 950px;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.caption {
  min-width: 950px;
}

.caption h2 {
  margin-top: 10px;
  margin-bottom: 70px;
  font-size: 84px;
  text-transform: uppercase;
  font-weight: 800;
  color: #fff;
  letter-spacing: 1px;
  text-shadow: 6px 7px 8px rgb(0 0 0 / 32%);
}

.caption h2 em {
  font-style: normal;
  color: #1ed9ec;
  font-weight: 900;
  text-shadow: 6px 7px 8px rgb(0 0 0 / 32%);
}

.caption h6 {
  margin-bottom: 0 !important;
  margin-top: 0px;
  font-size: 18px;
  text-transform: uppercase;
  font-weight: 800;
  color: #fff;
  letter-spacing: 0.5px;
  text-shadow: 6px 7px 8px rgb(0 0 0 / 32%);
  display: flex;
  justify-content: center;
  align-items: center;
}

.main-button.scroll-to-section {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  overflow: hidden;
}

.main-button.scroll-to-section > input {
  border-radius: 50px;
  border: none;
  height: 60px;
  width: 60%;
  margin-right: 10px;
  padding-left: 20px;
  font-size: 16px;
  color: black;
  white-space: nowrap;
  overflow: hidden;
  box-shadow: 4px 5px 2px 3px rgb(0 0 0 / 30%);
}

.main-button.scroll-to-section > input::placeholder {
  color: rgb(75, 75, 75);
}

.main-button.scroll-to-section > .search-icon {
  background-color: white;
  width: 60px;
  height: 60px;
  min-width: 50px;
  min-height: 50px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
  box-shadow: 4px 5px 2px 3px rgb(0 0 0 / 30%);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

@keyframes shake {
  0% {
    transform: rotate(-10deg);
  }
  100% {
    transform: rotate(10deg);
  }
}

.main-button.scroll-to-section > .search-icon:hover {
  transform: scale(1.1) rotate(20deg);
  animation: shake 0.2s infinite alternate;
}
</style>
