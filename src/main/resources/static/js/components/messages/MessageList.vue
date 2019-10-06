 <template>
  
  <div> 
    <message-form :messages="messages" :messageAttr="message" />
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

    export default {
        props: ['messages'],
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {
                messagesApi.remove(message.id).then(result => {
                    if (result.ok) {
                        if (index > -1) {
                        this.messages.splice(this.messages.indexOf(message), 1)
                        }
                    }
                })
            }
        }

    }

 </script>

 <style>



 </style>
