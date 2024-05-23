import { createApp } from "vue";
import { createPinia } from "pinia";
import axios from "axios";

import App from "./App.vue";
import router from "./router";

const app = createApp(App);

// pinia & router
app.use(createPinia());
app.use(router);

// fortawesome
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faSpinner } from "@fortawesome/free-solid-svg-icons";

app.component("font-awesome-icon", FontAwesomeIcon);

// axios를 Vue 앱에 전역으로 등록
app.config.globalProperties.$axios = axios;

app.mount("#app");
