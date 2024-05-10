<template>
  <div id="login-body">
    <div id="card">
      <img src="@/assets/index/카리나.jpg" style="width: 50%; height: 100%" />
      <div id="info">
        <div style="margin: 5% 0">
          <span style="font-size: 24px; font-weight: bold"> Sign In </span>
          <div>
            <img />
          </div>
        </div>
        <div id="input-area">
          <span>이메일</span>
          <input type="text" placeholder="아이디" />
          <span>패스워드</span>
          <input type="password" placeholder="비밀번호" />
        </div>
        <div>
          <div
            style="
              display: flex;
              align-items: center;
              justify-content: center;
              min-height: 50px;
              background-color: #22ad75;
              color: white;
              margin-bottom: 5%;
            "
          >
            <a>로그인</a>
          </div>
          <div>
            <div
              style="
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                margin-bottom: 10%;
              "
            >
              <div>
                <input type="checkbox" />
                <span>Remember Me</span>
              </div>
              <span>Forget Password</span>
            </div>
          </div>
          <div
            style="display: flex; align-items: center; justify-content: center"
          >
            <span>회원이 아니십니까?</span>
            <a>가입하쇼~</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
body {
  margin: 0 !important;
}

#login-body {
  height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
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
}

#info {
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 0 5%;
}

#input-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#input-area > span {
  font-size: 12px;
  margin: 5px 0;
}

#input-area > input {
  min-height: 40px;
  margin-bottom: 5%;
}
</style>
