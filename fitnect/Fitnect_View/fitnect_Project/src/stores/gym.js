import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useGymStore = defineStore('gym', () => {

  const gymAllList = ref({});

  return { gymAllList };
})
