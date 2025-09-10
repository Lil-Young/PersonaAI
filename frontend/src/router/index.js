import UserCreate from "@/components/UserCreate.vue"
import UserLogin from "@/components/UserLogin.vue";
import { createRouter, createWebHistory } from "vue-router"


const routes = [
    {
        path: '/user/create',
        component: UserCreate
    },
    {
        path: '/user/login',
        component: UserLogin
    }
]

const router = createRouter(
    {
        history: createWebHistory(),
        routes
    }
)

export default router;