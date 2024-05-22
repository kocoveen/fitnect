<template>
  <div>
    <iframe :src="next_redirect_pc_url" frameborder="0"> </iframe>
  </div>
</template>

<script setup>
import { ref } from "vue";

const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const userId = loginUser.payload.userId;

const next_redirect_pc_url = ref("");

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
      next_redirect_pc_url = response.data.data.next_redirect_pc_url;
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
