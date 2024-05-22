import { createRouter, createWebHistory } from "vue-router";
import IndexView from "@/views/IndexView.vue";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import MainView from "@/views/MainView.vue";
import ProfileView from "@/views/ProfileView.vue";
import MyInfo from "@/assets/components/profile/MyInfo.vue";
import MyGymList from "@/assets/components/profile/MyGymList.vue";
import MyClassList from "@/assets/components/profile/MyClassList.vue";
import MyGymReviewList from "@/assets/components/profile/MyGymReviewList.vue";
import MyTrainerReviewList from "@/assets/components/profile/MyTrainerReviewList.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "index",
      component: IndexView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/signup",
      name: "signup",
      component: SignupView,
    },
    {
      path: "/main",
      name: "main",
      component: MainView,
    },
    {
      path: "/profile",
      name: "profile",
      component: ProfileView,
      children: [
        {
          path: "",
          redirect: (to) => {
            return {
              name: "my-info",
            };
          },
        },
        {
          path: "my-info",
          name: "my-info",
          component: MyInfo,
        },
        {
          path: "my-gym-list",
          name: "my-gym-list",
          component: MyGymList,
        },
        {
          path: "my-class-list",
          name: "my-class-list",
          component: MyClassList,
        },
        {
          path: "my-gym-review-list",
          name: "my-gym-review-list",
          component: MyGymReviewList,
        },
        {
          path: "my-trainer-review-list",
          name: "my-trainer-review-list",
          component: MyTrainerReviewList,
        },
      ],
    },
  ],
});

export default router;
