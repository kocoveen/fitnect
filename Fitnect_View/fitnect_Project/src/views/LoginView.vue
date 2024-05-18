<template>
  <div id="login-body" style="background-color: #ddebeab8">
    <div id="card">
      <img id="login-img" src="@/assets/imgs/loginImg.jpg" />
      <div id="info">
        <div id="sign-text">
          <span> Welcom to <span style="color: #1ed9ec">Fit</span>nect </span>
        </div>
        <div id="input-area">
          <span>EMAIL</span>
          <input
            v-model="data.email"
            type="text"
            class="inputbox"
            placeholder="Enter your email"
          />
          <div class="forgot-pass">
            <span>PASSWORD</span>
            <router-link to="/" id="forgot">
              <span>Forgot Password?</span>
            </router-link>
          </div>
          <input
            v-model="data.password"
            type="password"
            class="inputbox"
            placeholder="············"
          />
        </div>
        <div>
          <div>
            <div id="check-area">
              <input type="checkbox" id="check_btn" />
              <label for="check_btn">
                <span>Remember Me</span>
              </label>
            </div>
          </div>

          <div id="nav-area" @click="login">Sign in</div>

          <div id="signup-btn">
            <span>회원이 아니신가요?</span>
            <router-link
              to="/signup"
              id="signup"
              style="text-decoration: underline"
            >
              Create an account
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const data = ref({
  email: "",
  password: "",
});

const login = () => {
  axios
    .post(`http://localhost:8080/user/sign-in`, data.value, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      sessionStorage.setItem("accessToken", response.data.data.accessToken);
      console.log(sessionStorage.getItem("accessToken"));

      router.push("/");
    })
    .catch((e) => {
      alert("로그인 실패");
    });
};
</script>

<style scoped>
body {
  margin: 0 !important;
}

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

/* 카드 요소에 애니메이션 적용 */
#card {
  border: 1px solid white;
  width: 50%;
  height: 60vh;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 1px 10px 34px -10px rgb(0 0 0 / 61%);
  animation: slideIn 1s ease-in-out; /* 애니메이션 적용 */
  min-height: 560px;
  min-width: 960px;
  background-color: white;
}

#info {
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 0 5%;
}

#login-img {
  width: 50%;
  height: 100%;
}

/* 로그인 */
#login-body {
  height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

#sign-text {
  margin: 5% 0;
}

#sign-text > span {
  font-weight: bold;
  color: #566a7f;
  font-size: calc(1.2625rem + 0.15vw);
}

#input-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#input-area > span,
.forgot-pass > span:first-child {
  font-size: 12px;
  margin: 5px 0;
  font-weight: bold;
}

.forgot-pass {
  font-size: 12px;
  margin: 5px 0;
  display: flex;
  justify-content: space-between;
}

#input-area > input {
  min-height: 40px;
  margin-bottom: 5%;
}

.inputbox {
  padding: 0.4375rem 0.875rem;
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
}

.inputbox:focus {
  outline: none;
  border-color: #1ed9ec;
}

.inputbox:focus + .inputbox {
  border-color: #25c3d3;
}

#forgot > span {
  color: #25c3d3 !important;
}

#forgot > span:hover {
  color: #84ccd3 !important;
}

#nav-area {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 50px;
  background-color: #25c3d3;
  color: white;
  margin-bottom: 5%;
  cursor: pointer;
  box-shadow: 0 0.125rem 0.25rem 0 rgba(105, 233, 255, 0.4);
  border-radius: 10px;
  font-weight: bold;
  transition: background-color 0.3s ease, color 0.3s ease, transform 0.3s ease;
}

#nav-area:hover {
  color: #fff !important;
  background-color: #29c9db !important;
  border-color: #29c9db !important;
  transform: translateY(-1px) scale(1.01) !important;
}

#check-area {
  margin-bottom: 5%;
}

#check-area {
  margin-bottom: 5%;
}

#check_btn {
  display: none;
}

#check_btn + label {
  cursor: pointer;
}

#check_btn + label > span {
  vertical-align: middle;
  padding-left: 5px;
  color: #566a7f;
  font-size: 15px;
}

#check_btn + label:before {
  appearance: none;
  content: "";
  display: inline-block;
  width: 17px;
  height: 17px;
  border: 1px solid #c6dadc;
  border-radius: 4px;
  vertical-align: middle;
  transition: background-color 0.5s ease, border-color 0.5s ease;
}

#check_btn:checked + label:before {
  content: "";
  background-color: #1ed9ec;
  border-color: #1ed9ec;
  background-size: cover;
  background-image: url("@/assets/imgs/check_btn.svg");
  background-repeat: no-repeat;
  background-position: center;
  transition: background-color 0.5s ease, border-color 0.5s ease;
}

#signup-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
}

#signup-btn > span {
  margin-right: 1%;
}

#signup {
  color: #25c3d3;
  margin-left: 1%;
}
#signup:hover {
  color: #20daff;
}
</style>
