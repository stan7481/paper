import Vue from 'vue'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import { connect } from './util/ws'

connect()



new Vue({
    el: '#app',
    router,
    render: a => a(App)

    })