import { defineStore } from "pinia";
import axios from "axios";

export const useReviewStore = defineStore("review", {
  state: () => ({
    reviews: {},
  }),
  actions: {
    async fetchReviews(gymId) {
      try {
        const accessToken = sessionStorage.getItem("accessToken");
        const response = await axios.get(`http://localhost:8080/gym/${gymId}/review-with-trainer-review`, {
          headers: {
            Authorization: `bearer ${accessToken}`,
          },
        });
        this.reviews[gymId] = response.data.data; // 데이터를 바로 할당
        console.log(`Fetched reviews for gymId ${gymId}:`, response.data); // 콘솔 출력 추가
      } catch (error) {
        console.error(`Error fetching reviews for gymId: ${gymId}`, error);
      }
    },
    getReviewCount(gymId) {
      const reviews = this.reviews[gymId];
      console.log(`Review count for gymId ${gymId}:`, reviews ? reviews.length : 0); // 리뷰 카운트 콘솔 출력
      return reviews ? reviews.length : 0;
    },
  },
});
