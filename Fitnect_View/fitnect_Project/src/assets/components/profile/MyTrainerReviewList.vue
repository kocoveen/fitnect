<template>
  <div>내 트레이너 리뷰</div>
  <div>{{ myTrainerReview }}</div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";

const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const myTrainerReview = ref({});

axios
  .get(`http://localhost:8080/user/my-trainer-review`, {
    headers: {
      Authorization: `bearer ${accessToken}`,
    },
  })
  .then((response) => {
    console.log(response.data.data);
    myTrainerReview.value = response.data.data;
  })
  .catch((e) => {});

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

<style lang="scss" scoped></style>
