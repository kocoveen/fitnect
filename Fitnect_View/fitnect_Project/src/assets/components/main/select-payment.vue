<template>
  <div>
    <!-- START -->
    <br />
    <hr />
    <div id="payment-select">
      <fieldset id="prices">
        <legend>가격</legend>
        <div
          v-for="(price, index) in gym.prices"
          :key="'price_' + price.priceId"
          class="card"
        >
          <div class="card-body">
            <input
              name="prices"
              type="radio"
              :id="'price_' + price.priceId"
              v-model="totalInfo.priceId"
              :value="price.priceId"
              class="radio-btn"
            />
            <label
              :for="'price_' + price.priceId"
              class="form-check-label label-full"
              >{{ price.priceName }}</label
            >
            <label
              :for="'class_' + price.priceId"
              class="form-check-label label-half-full"
              >{{ price["price"] }}원</label
            >
          </div>
        </div>
      </fieldset>

      <fieldset id="classes">
        <legend>강의</legend>
        <div
          v-for="(item, index) in gym.classes"
          :key="'class_' + item.classId"
          class="card"
        >
          <div class="card-body">
            <input
              name="classes"
              type="radio"
              :id="'class_' + item.classId"
              v-model="totalInfo.classId"
              :value="item.classId"
              class="radio-btn"
            />
            <label
              :for="'class_' + item.classId"
              class="form-check-label label-full"
              >{{ item.className }}</label
            >
            <label
              :for="'class_' + item.classId"
              class="form-check-label label-half-full"
              >{{ item.classPrice }}원</label
            >
          </div>
        </div>
      </fieldset>

      <div class="btn-group">
        <button @click="payment" class="btn btn-primary">결제</button>
        <button @click="cancelSelected" class="btn btn-danger">선택취소</button>
      </div>
    </div>
    <!-- END -->

    <div class="modal-wrap" v-show="modalCheck">
      <div class="modal-container">
        <!--  모달창 content  -->
        <iframe
          :src="next_redirect_pc_url"
          frameborder="0"
          width="100%"
          height="92%"
          style="z-index: 3"
        >
        </iframe>
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
