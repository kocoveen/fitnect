<template>
  <div id="header">
    <div id="logo">
      <router-link to="/" id="logo" @click.native="scrollToTop">
        <img src="@/assets/imgs/image.png" />
      </router-link>
    </div>
    <div id="navBar">
      <div class="nav-btn" @click="scrollToTop">Home</div>
      <div class="nav-btn2" @click="scrollToAbout">About</div>
      <div class="nav-btn3" @click="scrollToContactUs">Contact Us</div>
      <div id="login" @click="LoginPage">Sign in</div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const isLanding = ref(false);
const router = useRouter();

const LoginPage = () => {
  router.push("/login");
};

// Scroll to top when logo is clicked
const scrollToTop = () => {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
  isLanding.value = true;
};

// About 섹션으로 스크롤하는 함수
const scrollToAbout = () => {
  const AboutSection = document.getElementById("about");
  if (AboutSection) {
    AboutSection.scrollIntoView({ behavior: "smooth" });
  }
};

// Contact Us 섹션으로 스크롤하는 함수
const scrollToContactUs = () => {
  const contactUsSection = document.getElementById("contact-us");
  if (contactUsSection) {
    contactUsSection.scrollIntoView({ behavior: "smooth" });
  }
};

// Scroll event handler
window.addEventListener("scroll", () => {
  // 페이지가 스크롤될 때 헤더에 클래스 추가
  const header = document.getElementById("header");
  if (header) {
    if (window.scrollY > 0) {
      header.classList.add("background-header");
    } else {
      header.classList.remove("background-header");
    }
  }

  // Check the distance from the top of the about section
  const aboutSection = document.getElementById("about");
  if (aboutSection) {
    const aboutPosition = aboutSection.getBoundingClientRect().top;
    // If the about section is near the viewport
    if (aboutPosition < window.innerHeight * 0.5) {
      // Add active class to nav-btn2 and remove from others
      document.querySelectorAll(".nav-btn").forEach((btn) => {
        btn.classList.remove("active");
      });
      document.querySelectorAll(".nav-btn2").forEach((btn) => {
        btn.classList.add("active");
      });
      document.querySelectorAll(".nav-btn3").forEach((btn) => {
        btn.classList.remove("active");
      });
    } else {
      // If not near the about section, remove active class from nav-btn2
      document.querySelectorAll(".nav-btn2").forEach((btn) => {
        btn.classList.remove("active");
      });
    }
  }

  // Check the distance from the top of the contact-us section
  const contactUsSection = document.getElementById("contact-us");
  if (contactUsSection) {
    const contactUsPosition = contactUsSection.getBoundingClientRect().top;
    // If the contact-us section is near the viewport
    if (contactUsPosition < window.innerHeight * 0.5) {
      // Add active class to nav-btn3 and remove from others
      document.querySelectorAll(".nav-btn").forEach((btn) => {
        btn.classList.remove("active");
      });
      document.querySelectorAll(".nav-btn2").forEach((btn) => {
        btn.classList.remove("active");
      });
      document.querySelectorAll(".nav-btn3").forEach((btn) => {
        btn.classList.add("active");
      });
    } else {
      // If not near the contact-us section, remove active class from nav-btn3
      document.querySelectorAll(".nav-btn3").forEach((btn) => {
        btn.classList.remove("active");
      });
    }
  }
});
</script>

<style scoped>
/* Header */
#header {
  background-color: #ffffff;
  display: flex;
  height: 90px;
  min-width: 950px;
  align-items: center;
  justify-content: space-between;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  z-index: 100;
  background: rgba(250, 250, 250, 0.45);
  transition: all 0.5s ease 0s;
}

#logo {
  margin: 0 2%;
}

#logo > a > img {
  height: 70px;
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

.nav-btn {
  color: #25c3d3;
  margin: 0 1.5%;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  justify-content: center;
}

.nav-btn:hover {
  color: white;
}

.nav-btn2 {
  color: white;
  margin: 0 1.5%;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  justify-content: center;
}

.nav-btn2:hover {
  color: #25c3d3;
}

.nav-btn3 {
  color: white;
  margin: 0 1.5%;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  justify-content: center;
}

.nav-btn3:hover {
  color: #25c3d3;
}

#login {
  border-radius: 10px;
  height: 50px;
  background-color: #25c3d3;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  color: white;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

#login:hover {
  background-color: white;
  color: #25c3d3;
}

#login > a {
  text-decoration: none;
  color: white;
}

.background-header {
  background: rgba(250, 250, 250, 0.99) !important;
  height: 90px !important;
  position: fixed !important;
  top: 0px;
  left: 0px;
  right: 0px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.15) !important;
}

.background-header .nav-btn,
.background-header .nav-btn2,
.background-header .nav-btn3 {
  color: black;
}

/* Header */
.nav-btn.active,
.nav-btn2.active,
.nav-btn3.active {
  color: #25c3d3 !important;
}

/* Header Animation */
#header {
  animation: headerFadeIn 0.5s ease forwards;
  opacity: 0;
}

@keyframes headerFadeIn {
  from {
    opacity: 0;
    transform: translateY(-50px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
