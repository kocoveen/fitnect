<template>
  <div id="container" :class="{ landing: isLanding }">
    <!-- header -->
    <div id="header">
      <div id="logo">
        <router-link to="/" id="logo" @click.native="scrollToTop">
          <img src="@/assets/imgs/image.png" />
        </router-link>
      </div>
      <div id="navBar">
        <div class="nav-btn" @click.native="scrollToTop">Home</div>
        <div class="nav-btn2" @click="scrollToAbout">About</div>
        <div class="nav-btn3" @click="scrollToContactUs">Contact Us</div>
        <div id="login" @click="LoginPage">Sign in</div>
      </div>
    </div>

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
            <input type="text" placeholder="가까운 점포 찾기..." />
            <div class="search-icon">
              <img src="@/assets/imgs/search.svg" />
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- ***** Testimonials Starts ***** -->
    <section class="section" id="about">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 offset-lg-3">
            <div class="section-heading">
              <h2>Expert <em>Trainers</em></h2>
              <img src="@/assets/imgs/line-dec.png" alt="" />
              <p>
                Nunc urna sem, laoreet ut metus id, aliquet consequat magna. Sed
                viverra ipsum dolor, ultricies fermentum massa consequat eu.
              </p>
            </div>
          </div>
        </div>
        <div class="row">
          <div style="display: flex; justify-content: space-around">
            <div class="col-lg-4">
              <div class="trainer-item">
                <div class="image-thumb">
                  <img src="@/assets/imgs/climing.jpg" alt="" />
                </div>
                <div class="down-content">
                  <span>Strength Trainer</span>
                  <h4>Jin S. OCK</h4>
                  <p>
                    Bitters cliche tattooed 8-bit distillery mustache. Keytar
                    succulents gluten-free vegan church-key pour-over seitan
                    flannel.
                  </p>
                  <ul class="social-icons">
                    <li>
                      <a href="#"><i class="fa fa-facebook"></i></a>
                    </li>
                    <li>
                      <a href="#"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li>
                      <a href="#"><i class="fa fa-linkedin"></i></a>
                    </li>
                    <li>
                      <a href="#"><i class="fa fa-behance"></i></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-lg-4">
              <div class="trainer-item">
                <div class="image-thumb">
                  <img src="@/assets/imgs/ungki.jpg" alt="" />
                </div>
                <div class="down-content">
                  <span>Muscle Trainer</span>
                  <h4>UNGKI JANG</h4>
                  <p>
                    Bitters cliche tattooed 8-bit distillery mustache. Keytar
                    succulents gluten-free vegan church-key pour-over seitan
                    flannel.
                  </p>
                  <ul class="social-icons">
                    <li>
                      <a href="#"><i class="fa fa-facebook"></i></a>
                    </li>
                    <li>
                      <a href="#"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li>
                      <a href="#"><i class="fa fa-linkedin"></i></a>
                    </li>
                    <li>
                      <a href="#"><i class="fa fa-behance"></i></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- ***** Testimonials Ends ***** -->

    <!-- ***** Contact Us Area Starts ***** -->
    <section class="section" id="contact-us">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-6 col-md-6 col-xs-12">
            <div id="map">
              <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.2811766693585!2d127.03702261207383!3d37.50128597193896!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca3ff67128961%3A0x55a56e8ffc5bc5d!2z66mA7Yuw7Lqg7Y287IqkIOyXreyCvA!5e0!3m2!1sko!2sus!4v1715501936405!5m2!1sko!2sus"
                width="600"
                height="450"
                style="border: 0"
                allowfullscreen=""
                loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"
              ></iframe>
            </div>
          </div>
          <div class="col-lg-6 col-md-6 col-xs-12">
            <div class="contact-form">
              <form id="contact" action="" method="post">
                <div class="row">
                  <div class="col-md-6 col-sm-12">
                    <fieldset>
                      <input
                        name="name"
                        type="text"
                        id="name"
                        placeholder="Your Name*"
                        required=""
                      />
                    </fieldset>
                  </div>
                  <div class="col-md-6 col-sm-12">
                    <fieldset>
                      <input
                        name="email"
                        type="text"
                        id="email"
                        pattern="[^ @]*@[^ @]*"
                        placeholder="Your Email*"
                        required=""
                      />
                    </fieldset>
                  </div>
                  <div class="col-md-12 col-sm-12">
                    <fieldset>
                      <input
                        name="subject"
                        type="text"
                        id="subject"
                        placeholder="Subject"
                      />
                    </fieldset>
                  </div>
                  <div class="col-lg-12">
                    <fieldset>
                      <textarea
                        name="message"
                        rows="6"
                        id="message"
                        placeholder="Message"
                        required=""
                      ></textarea>
                    </fieldset>
                  </div>
                  <div class="col-lg-12">
                    <fieldset>
                      <button
                        type="submit"
                        id="form-submit"
                        class="main-button"
                      >
                        Send Message
                      </button>
                    </fieldset>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- ***** Contact Us Area Ends ***** -->

    <!-- Footer Start -->
    <div class="container-fluid footer py-5 wow fadeIn" data-wow-delay="0.2s">
      <div class="container py-5">
        <div class="row g-5">
          <div class="col-md-6 col-lg-6 col-xl-3">
            <div class="footer-item d-flex flex-column">
              <h4 class="text-white mb-4">
                <i class="fas fa-star-of-life me-3"></i>Fitnect
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
              <h4 class="mb-4 text-white">Fitnect Services</h4>
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
                ><i
                  class="fa fa-map-marker-alt me-2"
                  style="
                    font-weight: 900;
                    font-family: 'Font Awesome 5 Free';
                    font-style: normal;
                    font-variant: normal;
                    line-height: 1;
                  "
                ></i>
                123 Street, New York, USA</a
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
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const isLanding = ref(false);

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
  background-color: rgb(56 67 73 / 65%);
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

/* about */
.section-heading {
  text-align: center;
  margin-top: 140px;
  margin-bottom: 80px;
}

.section-heading h2 {
  font-size: 28px;
  font-weight: 800;
  color: #232d39;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-top: 0px;
  margin-bottom: 0px;
}

.section-heading img {
  margin: 20px auto;
}

p {
  font-size: 14px;
  line-height: 25px;
  color: #7a7a7a;
}

.trainer-item {
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.trainer-item img {
  width: 100%;
  border-radius: 5px;
}

.trainer-item span {
  font-size: 13px;
  font-weight: 500;
  color: #ed563b;
  display: inline-block;
  margin-top: 25px;
  margin-bottom: 10px;
}

.trainer-item h4 {
  font-size: 19px;
  font-weight: 600;
  color: #232d39;
  letter-spacing: 0.5px;
  margin-bottom: 18px;
}

.trainer-item p {
  margin-bottom: 20px;
  font-size: 14px;
  line-height: 25px;
  color: #7a7a7a;
}

ul {
  margin-bottom: 0px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.trainer-item ul.social-icons li {
  display: inline-block;
  margin-right: 12px;
}

.trainer-item ul.social-icons li a {
  color: #232d39;
  transition: all 0.3s;
  text-decoration: none !important;
}

/* contact us */
#contact-us {
  height: 613px;
  margin-top: 110px;
  display: flex;
  align-items: flex-end;
}
section {
  display: block;
}

#contact-us .container-fluid .col-lg-6 {
  padding: 0px;
}

@media (max-width: 992px) {
  #contact-us #map {
    margin-bottom: -7px;
  }
}

#map > iframe {
  width: 100%;
  height: 100%;
}

@media (max-width: 992px) {
  #contact-us .contact-form {
    padding: 30px;
  }
}

#contact-us .contact-form {
  padding: 80px;
  background-image: url(@/assets/imgs/contact-bg.jpg);
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
}

#contact-us .contact-form #contact {
  background-color: #fff;
  padding: 40px;
  border-radius: 5px;
}

.contact-form input,
.contact-form textarea {
  color: #7a7a7a;
  font-size: 13px;
  border: 1px solid #ddd;
  background-color: #fff;
  width: 100%;
  height: 40px;
  outline: none;
  line-height: 40px;
  padding: 0px 10px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  margin-bottom: 30px;
}

.contact-form button {
  display: inline-block;
  font-size: 13px;
  padding: 11px 17px;
  background-color: #25c3d3;
  color: #fff;
  text-align: center;
  font-weight: 400;
  text-transform: uppercase;
  transition: all 0.3s;
  border: none;
  outline: none;
  margin-top: -8px;
  font-weight: bold;
}

#map {
  height: 100%;
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
