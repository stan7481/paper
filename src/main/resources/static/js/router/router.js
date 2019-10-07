import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'components/messages/MessageList.vue'
import Profile from 'components/messages/Profile.vue'

Vue.use(VueRouter)

const routes = [
	{ path: '/', component: MessagesList },
	{ path: '/profile', component: Profile },
	{ path: '*', component: MessagesList }
]

export default new VueRouter({
  mode: 'history',
  routes // сокращённая запись для `routes: routes`
})