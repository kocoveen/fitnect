<template>
  <div class="simple-info">
    <div class="name-space">
      <div class="gym-name">{{ gym.name }}</div>
      <div class="gym-type">{{ gym.type }}</div>
    </div>
    <p>{{ gym.address }}</p>
    <p>{{ gym.operatingStatus }}</p>
    <p>Rating: {{ gym.rating }}</p>
    <p>Reviews: {{ gym.reviews }}</p>
    <p>Type: {{ gym.type }}</p>
    <p>Members: {{ gym.members }}</p>
    <p>Price: {{ gym.price }}</p>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  gym: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.simple-info {
  padding: 20px;
  font-family: Arial, sans-serif;
}

.simple-info h2 {
  margin-top: 0;
}

.simple-info p {
  margin: 5px 0;
}
</style>
