<template>
  <div id="container">
    <!-- header -->
    <div id="header">
      <div id="logo">
        <router-link to="/" id="logo">
          <img src="@/assets/index/image.png" />
        </router-link>
      </div>
      <div id="navBar">
        <div class="nav-btn">Home</div>
        <div class="nav-btn">어쩌구</div>
        <div id="login" @click="LoginPage">로그인</div>
      </div>
    </div>

    <div>
      <div>
        <video autoplay="" muted="" loop="" id="bg-video">
          <source src="@/assets/index/gym-video.mp4" type="video/mp4" />
        </video>
      </div>

      <div id="body">
        <div class="caption">
          <h6>Fitness + Connect</h6>
          <h2>사람과 <em>건강</em>을 연결하다</h2>
          <div class="main-button scroll-to-section">
            <input type="text" placeholder="가까운 점포 찾기..." />
            <div class="search-icon">
              <img src="@/assets/index/search.svg" />
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer Start -->
    <div class="container-fluid footer py-5 wow fadeIn" data-wow-delay="0.2s">
      <div class="container py-5">
        <div class="row g-5">
          <div class="col-md-6 col-lg-6 col-xl-3">
            <div class="footer-item d-flex flex-column">
              <h4 class="text-white mb-4">
                <i class="fas fa-star-of-life me-3"></i>Terapia
              </h4>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                Delectus dolorem impedit eos autem dolores laudantium quia, qui
                similique
              </p>
              <div class="d-flex align-items-center">
                <i class="fas fa-share fa-2x text-white me-2"></i>
                <a
                  class="btn-square btn btn-primary text-white rounded-circle mx-1"
                  href=""
                  ><i class="fab fa-facebook-f"></i
                ></a>
                <a
                  class="btn-square btn btn-primary text-white rounded-circle mx-1"
                  href=""
                  ><i class="fab fa-twitter"></i
                ></a>
                <a
                  class="btn-square btn btn-primary text-white rounded-circle mx-1"
                  href=""
                  ><i class="fab fa-instagram"></i
                ></a>
                <a
                  class="btn-square btn btn-primary text-white rounded-circle mx-1"
                  href=""
                  ><i class="fab fa-linkedin-in"></i
                ></a>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-6 col-xl-3">
            <div class="footer-item d-flex flex-column">
              <h4 class="mb-4 text-white">Quick Links</h4>
              <a href=""><i class="fas fa-angle-right me-2"></i> About Us</a>
              <a href=""><i class="fas fa-angle-right me-2"></i> Contact Us</a>
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Privacy Policy</a
              >
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Terms & Conditions</a
              >
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Our Blog & News</a
              >
              <a href=""><i class="fas fa-angle-right me-2"></i> Our Team</a>
            </div>
          </div>
          <div class="col-md-6 col-lg-6 col-xl-3">
            <div class="footer-item d-flex flex-column">
              <h4 class="mb-4 text-white">Terapia Services</h4>
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> All Services</a
              >
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Physiotherapy</a
              >
              <a href=""><i class="fas fa-angle-right me-2"></i> Diagnostics</a>
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Manual Therapy</a
              >
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Massage Therapy</a
              >
              <a href=""
                ><i class="fas fa-angle-right me-2"></i> Rehabilitation</a
              >
            </div>
          </div>
          <div class="col-md-6 col-lg-6 col-xl-3">
            <div class="footer-item d-flex flex-column">
              <h4 class="mb-4 text-white">Contact Info</h4>
              <a href=""
                ><i class="fa fa-map-marker-alt me-2"></i> 123 Street, New York,
                USA</a
              >
              <a href=""
                ><i class="fas fa-envelope me-2"></i> info@example.com</a
              >
              <a href=""
                ><i class="fas fa-envelope me-2"></i> info@example.com</a
              >
              <a href=""><i class="fas fa-phone me-2"></i> +012 345 67890</a>
              <a href="" class="mb-3"
                ><i class="fas fa-print me-2"></i> +012 345 67890</a
              >
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Back to Top -->
    <a href="#" class="btn btn-primary btn-lg-square back-to-top"
      ><i class="fa fa-arrow-up"></i
    ></a>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const LoginPage = () => {
  router.push("/login");
};
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

/* Header */
#header {
  background-color: #ffffff;
  display: flex;
  height: 110px;
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
  height: 80px;
}

#navBar {
  margin: 0 2%;
  display: flex;
  align-items: center;
  font-size: 20px;
}

.nav-btn {
  color: #208d98;
  margin: 0 1.5%;
  width: 80px;
  font-weight: bold;
  cursor: pointer;
}

.nav-btn:hover {
  color: white;
}

#login {
  border-radius: 10px;
  height: 50px;
  background-color: #208d98;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  color: white;
  font-weight: bold;
  cursor: pointer;
}

#login:hover {
  background-color: white;
  color: #208d98;
}

#login > a {
  text-decoration: none;
  color: white;
}

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
  background-color: rgb(56 67 73 / 75%);
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

/*** Footer Start ***/
.footer {
  background: linear-gradient(rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0.9)),
    url(../img/carousel-2.jpg);
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
}
.footer .footer-item a {
  line-height: 35px;
  color: var(--bs-body);
  transition: 0.5s;
}

.footer .footer-item p {
  line-height: 35px;
}

.footer .footer-item a:hover {
  letter-spacing: 2px;
  color: var(--bs-primary);
}

/*** Footer End ***/

.btn-lg-square {
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: normal;
  border-radius: 50%;
}

.btn.btn-primary {
  box-shadow: inset 0 0 0 0 var(--bs-primary);
}

.btn-lg-square {
  width: 56px;
  height: 56px;
}

.btn {
  font-weight: 600;
  transition: 0.5s;
}

.back-to-top {
  position: fixed;
  right: 30px;
  bottom: 30px;
  display: flex;
  width: 45px;
  height: 45px;
  align-items: center;
  justify-content: center;
  transition: 0.5s;
  z-index: 99;
}

.mx-1 {
  display: flex !important;
}

a {
  text-decoration: none;
}
</style>
