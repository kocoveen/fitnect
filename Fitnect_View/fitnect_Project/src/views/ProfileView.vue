<template>
  <div class="container">
    <div style="margin: 40px;"></div>
    <div class="row gutters-sm">
      <div class="col-md-4 d-none d-md-block">
        <ProfileNav />
      </div>
      <RouterView />
    </div>
  </div>
</template>

<script setup>
import ProfileNav from "@/assets/components/profile/ProfileNav.vue";
import { ref } from "vue";
import axios from "axios";

const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);

console.log(loginUser);

const myId = loginUser.payload.userId;

const myInfo = ref({});

axios
  .get(`http://localhost:8080/user/${myId}`, {
    headers: {
      Authorization: `bearer ${accessToken}`,
    },
  })
  .then((response) => {
    console.log(response.data);
    // myInfo.value = response;
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

<style scoped>
  .gutters-sm {
      margin-right: -8px;
      margin-left: -8px;
  }
  
  .gutters-sm>.col, .gutters-sm>[class*=col-] {
      padding-right: 8px;
      padding-left: 8px;
  }
</style>
