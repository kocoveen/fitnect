<template>
  <div id="payment-select" ref="paymentSelect">
    <div class="header" style="margin-bottom: 10px">
      <div class="title">수강신청</div>
      <div class="close-icon" @click="closeComponent">X</div>
    </div>

    <fieldset id="prices" style="margin: 10px 0">
      <legend style="color: black; font-weight: bold; margin-bottom: 10px; font-size: 13px">회원권을 선택해주세요</legend>
      <select v-model="totalInfo.priceId" class="form-select" required>
        <option v-for="(price, index) in gym.prices" :key="'price_' + price.priceId" :value="price.priceId">
          {{ price.priceName }} - {{ price.price }}원
        </option>
      </select>
    </fieldset>

    <fieldset id="classes" style="margin: 10px 0">
      <legend style="color: black; font-weight: bold; margin-bottom: 10px; font-size: 13px">강의 목록</legend>
      <select v-model="totalInfo.classId" class="form-select" required>
        <option v-for="(item, index) in gym.classes" :key="'class_' + item.classId" :value="item.classId">
          {{ item.className }} - {{ item.classPrice }}원
        </option>
      </select>
    </fieldset>

    <div class="btn-group" style="width: 100%; margin-top: 15px; height: 50px">
      <button @click="payment" class="btn btn-primary">결제</button>
    </div>
    <div class="modal-wrap" v-show="modalCheck">
      <div class="modal-container">
        <!--  모달창 content  -->
        <iframe :src="next_redirect_pc_url" frameborder="0" width="100%" height="92%" style="z-index: 3"> </iframe>
        <div class="modal-btn">
          <button class="btn-common" @click="modalOpen">결제 취소</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref } from "vue";
import axios from "axios";

const props = defineProps({
  gym: {
    type: Object,
    required: true,
  },
});

const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const userId = loginUser.payload.userId;

const totalInfo = ref({
  userId: userId,
  gymId: props.gym.gymId,
  priceId: null,
  classId: null,
});

const paymentSelect = ref(null);

const closeComponent = () => {
  // paymentSelect 변수에 저장된 DOM 요소의 style 속성을 변경하여 다시 보이지 않게 설정합니다.
  paymentSelect.value.style.display = "none";
};
const cancelSelected = () => {
  totalInfo.priceId = null;
  totalInfo.classId = null;
};

// url
const next_redirect_pc_url = ref("");

// modal
const modalCheck = ref(false);

const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};

const payment = () => {
  console.log(totalInfo.value);
  modalOpen();
  console.log(modalCheck.value);

  axios
    .post(`http://localhost:8080/payment/ready`, totalInfo.value)
    .then((response) => {
      console.log(response.data.data.next_redirect_pc_url);
      next_redirect_pc_url.value = response.data.data.next_redirect_pc_url;
    })
    .then((response) => {
      console.log(response.data.data);
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
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.close-icon {
  cursor: pointer;
}

.items {
  display: flex;
  margin: 1px;
}

.card {
  margin-bottom: 10px;
}

.card-body {
  padding: 0.5rem 0.5rem;
}

.radio-btn {
  margin: 6px;
}

.label-full {
  width: 65%;
}

.label-half-full {
  width: 25%;
}
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
  height: 720px;
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

.btn-common {
  border-radius: 10px;
  height: 50px;
  background-color: #df1451d5;
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
  height: 720px;
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

.btn-common {
  border-radius: 10px;
  height: 50px;
  background-color: #df1451d5;
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
