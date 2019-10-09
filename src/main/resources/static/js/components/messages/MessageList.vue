 <template>
  
  <div> 
    <span>User:  {{ userId }}</span> 
    <!-- <message-form :messages="messages" :messageAttr="message" /> -->
        <div class="card-columns" > 

      
        <message-row  v-for="message in messages" :key="message.id"
           :message="message" :messages="messages" :editMessage="editMessage" :deleteMessage="deleteMessage"/>
    </div>

  </div>
     
    


 </template>
 
 <script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import messagesApi from 'api/messages'

// $http.get('api/user').then(response => {
//    console.log(response.body);
// })

    export default {
        // props: ['messages'],
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null,
               messages: frontendData.messages,
                // userId: frontendData.usrID
               // userIdi: userId
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            // standart rest Api delete
            deleteMessage(message) { 
                // messagesApi.remove(message.id).then(result => {
                //     if (result.ok) {
                //         const index = this.messages.findIndex(item => item.id === data.body.id)
                //         if (index > -1) {
                //         this.messages.splice(this.messages.indexOf(message), 1)
                //         }
                //     }
                // })
            messagesApi.remove(message.id)
            }
        }

    }

 </script>

 <style>



 </style>


