var messageApi = Vue.resource('/message{/id}');

Vue.component('message-row', {
  props: ['message', 'messages'],
  template: '<div> ({{ message.id }})</i> {{ message.text }} </div>' 
});


Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
    '<div style="position: relative; width: 300px;"> '+
          '<message-row v-for="message in messages" :key="message.id"  :message="message" :messages="messages" />'+
    '</div>',
    created: function() {
        messageApi.get().then(result => result.json().then(data => data.forEach(message => this.messages.push(message))))

    }
});




var app = new Vue({
  el: '#app',
  template: '<messages-list :messages="messages" />',
  data: {
    messages: []
  }
});