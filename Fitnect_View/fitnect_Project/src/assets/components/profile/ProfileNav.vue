<template>
  <div class="profile-nav">
    <RouterLink :to="{ name: 'my-info' }">내 정보</RouterLink> |
    <RouterLink :to="{ name: 'my-gym-list' }">등록한 곳</RouterLink>
    |
    <RouterLink :to="{ name: 'my-class-list' }">등록한 강의</RouterLink>
    |
    <RouterLink :to="{ name: 'my-gym-review-list' }"
      >내가 쓴 체육관 리뷰</RouterLink
    >
    |
    <RouterLink :to="{ name: 'my-trainer-review-list' }"
      >내가 쓴 트레이너 리뷰</RouterLink
    >
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
