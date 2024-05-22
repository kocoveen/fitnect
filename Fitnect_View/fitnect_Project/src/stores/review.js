import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useReviewStore = defineStore("review", () => {
  const reviewAllList = ref({});

  return { reviewAllList };
});
