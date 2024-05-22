<template>
  <div>
    <div class="modal-wrap" v-show="modalCheck">
      <div class="modal-container">
        <!--  모달창 content  -->
        <span style="margin-left: 30px; margin-right: 30px">모달</span>
        <input
          type="text"
          class="inputbox input-margin-right"
          style="margin-bottom: 20px; height: 40px"
          v-model="totalInfo.gymId"
        />
        <div class="modal-btn">
          <button class="btn-common" @click="payment">결제</button>
        </div>
        <button class="btn-common" @click="modalOpen">취소</button>
      </div>
    </div>
  </div>
</template>

<script setup>
const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const userId = loginUser.payload.userId;

const totalInfo = ref({
  userId,
  gymId: "",
  priceId: "",
  classId: "",
});

const modalCheck = ref(false);

const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};

const payment = () => {
  axios
    .post(`http://localhost:8080/payment/ready`, totalInfo)
    .then((response) => {
      console.log(response.data.data.next_redirect_pc_url);
      const next_redirect_pc_url =
        "https://online-pay.kakao.com/mockup/v1/0ef46cfd82416b4aadab8f2578bbe60befae2d4d910f2b2e609ac07d94ba37fb/info";
      next_redirect_pc_url;
    })
    .catch((e) => {});
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
</style>
