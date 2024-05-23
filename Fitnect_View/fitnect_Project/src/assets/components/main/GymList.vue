<template>
  <div class="items">
    <div v-for="gym in gyms" :key="gym.gymId">
      <div class="item" @click="$emit('selectGym', gym)">
        {{ gym.name }}
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  gyms: Array,
});

const emit = defineEmits(["selectGym"]);
</script>

<style scoped>
.items {
  width: 100%;
  overflow-y: auto;
  background-color: #f0f0f0;
  margin-top: 115px;
}

.item {
  height: 80px;
  width: 100%;
  border-bottom: 1px solid #ccc;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
