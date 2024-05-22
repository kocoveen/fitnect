import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_GYM_API = `http://localhost:8080/gym/with-asso`;

export const useGymStore = defineStore("gym", () => {
  const gymAllWithAssoList = ref({});

  const getGymAllWithAssoList = () => {
    const ACCESS_TOKEN = sessionStorage.getItem("accessToken");
    axios({
      url: REST_GYM_API,
      method: "GET",
      headers: {
        "Content-Type": "applcation/json",
        Authorization: `bearer ${ACCESS_TOKEN}`,
      },
    })
      .then((response) => {
        gymAllWithAssoList.value = response.data.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return { gymAllWithAssoList, getGymAllWithAssoList };
});
