import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import Profile from 'pages/Profile.vue'

Vue.use(VueRouter)

const routes = [
	{ path: '/', component: MessagesList },
	{ path: '/profile/:id?', component: Profile },
	{ path: '*', component: MessagesList }
]

export default new VueRouter({
  mode: 'history',
  routes 
})