 <template>
    <div> 
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
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null,
               // messages: frontendData.messages,
            messages: messages,
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) { 
                messagesApi.remove(message.id)
            }
        }

    }

 </script>

 <style>



 </style>


