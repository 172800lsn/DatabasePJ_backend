import { createRouter, createWebHistory } from "vue-router";
import UserRegister from "../views/UserRegister.vue";
import UserLogin from "../views/UserLogin.vue";

const routes = [
    { path: "/", redirect: "/login" },
    { path: "/register", component: UserRegister },
    { path: "/login", component: UserLogin },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;