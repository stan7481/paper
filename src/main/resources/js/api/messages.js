import Vue from 'vue'

const messages = Vue.resource('/message{/id}')

export default {
	get: () => messages.get(),
	get: id => messages.get({id}),
    add: message => messages.save({}, message),
    update: message => messages.update({id: message.id}, message),
    remove: id => messages.remove({id}),
    getOnlyOwnerArticles: channelId => Vue.http.post(`/message/onlyOwnerArticles/${channelId}`),
    getSubscriptionsArticle: userId => Vue.http.post(`/message/subscriptionsArticle/${userId}`),
    changeLiks: messageId => Vue.http.post(`/message/change-likes/${messageId}`)
}