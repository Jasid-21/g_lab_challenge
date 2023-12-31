import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView'
import ReserveView from '@/views/ReserveView'
import DashboardView from '@/views/DashboardView'

const routes = [
  {
    path: '/',
    name: 'client_home',
    component: ReserveView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: DashboardView
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
