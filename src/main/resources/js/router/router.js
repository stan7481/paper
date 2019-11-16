import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import Profile from 'pages/Profile.vue'
import EditArticle from 'pages/EditArticle.vue'
import Article from 'pages/Article.vue'

Vue.use(VueRouter)

const routes = [
	{ path: '/', component: MessagesList },
	{ path: '/profile/:id?', component: Profile },
	{ path: '/editarticle/:id?', component: EditArticle },
	{ path: '/article/:id', component: Article },
	{ path: '*', component: MessagesList }
]

export default new VueRouter({
  mode: 'history',
  routes 
})