<template>
  <div class="col-md-8">
    <div class="card">
      <!-- <div class="card-header border-bottom mb-3 d-flex d-md-none"><ul class="nav nav-tabs card-header-tabs nav-gap-x-1" role="tablist"><li class="nav-item"> <a href="#profile" data-toggle="tab" class="nav-link has-icon active"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg></a></li><li class="nav-item"> <a href="#account" data-toggle="tab" class="nav-link has-icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg></a></li><li class="nav-item"> <a href="#security" data-toggle="tab" class="nav-link has-icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shield"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg></a></li><li class="nav-item"> <a href="#notification" data-toggle="tab" class="nav-link has-icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path><path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg></a></li><li class="nav-item"> <a href="#billing" data-toggle="tab" class="nav-link has-icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card"><rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect><line x1="1" y1="10" x2="23" y2="10"></line></svg></a></li></ul></div> -->

      <div class="card-body">
        <div id="sign-text">내 정보</div>

        <div id="input-card">
          <div class="input-item">
            <span class="email">EMAIL</span>
            <input
              id="email"
              type="text"
              v-model="user.email"
              class="inputbox input-margin-right readonly"
              readonly
            />
          </div>

          <div class="input-item">
            <span class="name">NAME</span>
            <input
              type="text"
              v-model="user.name"
              class="inputbox input-margin-right"
              placeholder="Enter your name"
            />
          </div>

          <div class="input-item">
            <span class="phone">PHONE</span>
            <input
              type="text"
              v-model="user.phone"
              class="inputbox input-margin-right"
              placeholder="Enter your number"
            />
          </div>

          <div class="input-item">
            <span class="address">ADDRESS</span>
            <input
              type="text"
              class="inputbox input-margin-right readonly"
              v-model="user.address"
              readonly
            />
            <button
              class="btn-common btn btn-light"
              style="display: inline"
              id="postcode"
              @click="openPostcode"
            >
              주소 찾기
            </button>
          </div>

          <div class="input-item">
            <span class="height">키</span>
            <input
              type="text"
              class="inputbox input-margin-right"
              v-model="user.height"
            />
          </div>

          <div class="input-item">
            <span class="weight">몸무게</span>
            <input
              type="text"
              class="inputbox input-margin-right"
              v-model="user.weight"
            />
          </div>
        </div>

        <div class="btn-group-1">
          <button
            class="btn-common btn btn-light"
            id="changePassword"
            @click="modalOpen"
            type="button"
          >
            패스워드 변경
          </button>
          <button
            class="btn-common btn btn-light"
            id="nav-area"
            @click="changeInfo"
          >
            정보 수정
          </button>
          <button
            class="btn-common btn btn-light"
            id="nav-area"
            @click="deleteInfo"
          >
            회원 탈퇴
          </button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal-wrap" v-show="modalCheck">
    <div class="modal-container">
      <!--  모달창 content  -->
      <span style="margin-left: 30px; margin-right: 30px">변경할 패스워드</span>
      <input
        type="text"
        class="inputbox input-margin-right"
        style="margin-bottom: 20px; height: 40px"
        v-model="userIdAndPassword.password"
      />
      <div class="modal-btn">
        <button class="btn-common" @click="changePassword">
          패스워드 변경
        </button>
        <button class="btn-common" @click="modalOpen">취소</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const user = ref({});

const myId = loginUser.payload.userId;

axios
  .get(`http://localhost:8080/user/${myId}`, {
    headers: {
      Authorization: `bearer ${accessToken}`,
    },
  })
  .then((response) => {
    user.value = response.data.data;
  })
  .catch((e) => {});

const roadAddress = ref("");
const openPostcode = () => {
  new window.daum.Postcode({
    oncomplete: (data) => {
      roadAddress.value = data.roadAddress;
      loadCoords(data.roadAddress);
    },
  }).open();
};

const loadCoords = (address) => {
  axios
    .get(
      `https://dapi.kakao.com/v2/local/search/address.json?query=${address}`,
      {
        headers: {
          Authorization: "KakaoAK d00a06f293cd0dd71929545bb8a71c1c",
        },
      }
    )
    .then((response) => {
      user.value.address = response.data.documents[0]["address_name"]; // 도로명 주소
      user.value.longitude = response.data.documents[0]["y"]; // 위도
      user.value.latitude = response.data.documents[0]["x"]; // 경도
    })
    .catch((e) => {
      alert(e);
    });
};

const modalCheck = ref(false);

const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};

const userIdAndPassword = ref({
  userId: myId,
  password: "",
});

const changePassword = () => {
  axios
    .put(
      `http://localhost:8080/user/change-password`,
      userIdAndPassword.value,
      {
        headers: {
          Authorization: `bearer ${accessToken}`,
        },
      }
    )
    .then((response) => {
      alert("패스워드 변경이 완료되었습니다.");
      router.replace("/profile/my-info");
      modalCheck.value = false;
    });
};

const changeInfo = () => {
  axios
    .put(`http://localhost:8080/user/${myId}`, user.value, {
      headers: {
        Authorization: `bearer ${accessToken}`,
      },
    })
    .then((response) => {
      alert("정보 변경이 완료되었습니다.");
      router.replace("/profile/my-info");
    });
};

const deleteInfo = () => {
  axios
    .delete(`http://localhost:8080/user/${myId}`, {
      headers: {
        Authorization: `bearer ${accessToken}`,
      },
    })
    .then((response) => {
      alert("탈퇴가 완료되었습니다.");
      sessionStorage.removeItem("accessToken");
      router.push("/");
    });
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
</script>

<style scoped>
#sign-text {
  display: flex;
  justify-content: center;
  font-size: xx-large;
  font-weight: 600;
  margin-bottom: 40px;
}

#info {
  display: flex;
  align-items: center;
  flex-direction: column;
}

/* dimmed */
.modal-wrap {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
}
/* modal or popup */
.modal-container {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 550px;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
}

.modal-btn {
  display: flex;
  justify-content: space-evenly;
}

.input-item {
  margin-bottom: 3rem;
}

.input-box {
  border-radius: 10;
}

.input-margin-right {
  width: 50%;
  margin-right: 35px;
  height: 45px;
  padding: 5px;
  padding-left: 10px;
}

.btn-group-1 {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.readonly {
  background-color: #d9d9d9;
  border: #2b2b2b;
}

span {
  display: inline-block;
  height: 1rm;
  width: 125px;
}

.btn-common {
  border-radius: 10px;
  height: 50px;
  background-color: #25c3d3;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;

  border: 0;
  width: 130px;
}
</style>
