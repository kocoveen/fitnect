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
      <!-- 로그인 상태에 따라 설정 이미지 표시 -->
      <div v-if="loginUser" id="user-info">
        <div style="display: flex; justify-content: center; align-items: center; flex-wrap: wrap">
          <!-- 클릭 이벤트를 toggleUserMenu 메서드에 연결 -->
          <span id="namespace">{{ loginUser.payload.name }}님</span>
          <!-- 설정 이미지 클릭 시 메뉴 표시 -->
          <img src="@/assets/imgs/white-up.svg" id="setting-img" @click="toggleUserMenu" :class="{ 'show-menu': showUserMenu }" />
        </div>
        <!-- 내정보 관리와 로그아웃 버튼 -->
        <div v-if="showUserMenu" id="user-menu">
          <div class="logout-area">{{ loginUser.payload.name }}님, 반갑습니다.</div>
          <hr style="margin: 0.6rem 0" />
          <div class="logout-area btn-area">
            <button @click="goToProfile">My Page</button>
            <button @click="logout">log out</button>
          </div>
        </div>
      </div>
      <!-- 로그인하지 않은 상태에서 로그인 버튼 표시 -->
      <div v-else id="login" @click="LoginPage">Sign in</div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

// 로그인 상태 관리 변수
const loginUser = ref(null);
// 유저 메뉴 표시 여부 관리 변수
const showUserMenu = ref(false);

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

// JWT 디코딩 함수
function base64UrlDecode(str) {
  return decodeURIComponent(
    atob(str.replace(/-/g, "+").replace(/_/g, "/"))
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );
}

function decodeJWT(token) {
  try {
    const [header, payload, signature] = token.split(".");
    const decodedHeader = JSON.parse(base64UrlDecode(header));
    const decodedPayload = JSON.parse(base64UrlDecode(payload));

    return {
      header: decodedHeader,
      payload: decodedPayload,
      signature: signature,
    };
  } catch (error) {
    console.error("Invalid token:", error);
    return null;
  }
}

// 페이지 로드 시 로그인 상태 확인
onMounted(() => {
  const accessToken = sessionStorage.getItem("accessToken");
  if (accessToken) {
    loginUser.value = decodeJWT(accessToken);
  }
});

// 로그아웃 함수
const logout = () => {
  sessionStorage.removeItem("accessToken");
  loginUser.value = null;
  router.replace("/");
};

// 내 정보 보기 페이지로 이동하는 함수
const goToProfile = () => {
  router.push("/profile");
};

// 유저 메뉴 토글 함수
const toggleUserMenu = () => {
  showUserMenu.value = !showUserMenu.value;
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
  height: 60px;
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
.background-header .nav-btn3,
.background-header #namespace {
  color: black;
}

.background-header #setting-img {
  content: url("@/assets/imgs/chevron-up.svg");
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

#namespace {
  color: white;
  font-weight: bold;
}

#setting-img {
  margin-left: 5px;
  transition: content 0.3s;
}

#setting-img.show-menu {
  content: url("@/assets/imgs/white-down.svg");
}

.background-header #setting-img.show-menu {
  content: url("@/assets/imgs/chevron-down.svg");
}

#user-info img {
  margin-left: 10px;
  cursor: pointer;
}

#user-menu {
  position: fixed;
  width: 200px;
  height: 100px;
  font-size: 13px;
  right: 10px;
  top: 70px;
  background-color: white;
  border-radius: 10px;
  padding: 10px;
  display: flex;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.logout-area {
  display: flex;
  justify-content: center;
  align-items: center;
  color: black;
}

.btn-area {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.btn-area button {
  background-color: #2ea0d7;
  color: #fff;
  border: none;
  border-radius: 5px;
  height: 30px;
  width: 80px;
}
.btn-area button:hover {
  background-color: #247aa5;
  transition: 0.7s;
}
</style>
