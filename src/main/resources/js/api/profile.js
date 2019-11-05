import Vue from 'vue'

const userProfile = Vue.resource('/userProfile{/id}')

export default {
	get: id => userProfile.get({id}),
	changeSubscriptions: channelId => Vue.http.post(`/userProfile/change-subscription/${channelId}`)

}