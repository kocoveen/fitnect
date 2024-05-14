<template>
  <div id="container" :class="{ landing: isLanding }">
    <!-- ***** Header Starts ***** -->
    <Header />

    <!-- ***** MainConetents Starts ***** -->
    <MainConetents />

    <!-- ***** about Starts ***** -->
    <section class="section" id="about"><About /></section>

    <!-- ***** Contact Us Area Starts ***** -->
    <section class="section" id="contact-us"><Contact /></section>

    <!-- ***** Footer Start ***** -->
    <Footer />

    <!-- ***** BackToTop Start ***** -->
    <BackToTop />
  </div>
</template>

<script setup>
import Header from "@/assets/components/common/header.vue";
import MainConetents from "@/assets/components/index/contents.vue";
import About from "@/assets/components/index/about.vue";
import Contact from "@/assets/components/index/contact.vue";
import Footer from "@/assets/components/common/footer.vue";
import BackToTop from "@/assets/components/common/back-top.vue";
</script>

<style scoped>
/* Overall Layout */
#app {
  position: relative;
  overflow-x: hidden;
}

#container {
  min-width: 950px;
  min-height: 30vh;
}

/* contact us section*/
#contact-us {
  height: 613px;
  margin-top: 110px;
  display: flex;
  align-items: flex-end;
}
section {
  display: block;
}

a {
  text-decoration: none;
}
</style>
