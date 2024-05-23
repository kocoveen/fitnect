<template>
  <div id="main-container">
    <!-- ***** Header Start ***** -->
    <Header />
    <!-- ***** MainBody Start ***** -->
    <MainBody />
    <!-- ***** Footer Start ***** -->
    <Footer />
    <!-- ***** BackToTop Start ***** -->
    <BackToTop />
  </div>
</template>

<script setup>
import Header from "@/assets/components/common/main-header.vue";
import BackToTop from "@/assets/components/common/back-top.vue";
import Footer from "@/assets/components/common/footer.vue";
import MainBody from "@/assets/components/main/main-body.vue";
</script>

<style scoped>
/* 애니메이션 키 프레임 정의 */
@keyframes slideIn {
  0% {
    opacity: 0;
    transform: translateY(-50px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

#main-container {
  animation: slideIn 0.5s ease-in-out; /* 애니메이션 적용 */
}
</style>
