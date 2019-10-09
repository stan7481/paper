 <template>
  
  <div> 
  	Profille {{ this.$route.path }}
    <!-- {{ $route.params.id }} -->
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
        // props: ['id'],
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null,
               // messages: frontendData.messages,
               
               userId: frontendData.usrID,
               currUser: (this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID,
               // messages: frontendData.messages.filter(message => frontendData.usrID == message.author.id)
             messages: frontendData.messages.filter(message => message.author.id == ((this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID)
                )
               // messages: null
            }
        },
    //     mounted: function () {
    // // `this` указывает на экземпляр vm
    //         // currUser = $route.params.id

    //         messages = frontendData.messages.filter(message => message.author.id == currUser)

    //         },

    //     mounted: function() {
    //     parameters = this.$route.query
    //     console.log(parameters)
   
        
  
    // },
        methods: {
            editMessage(message) {
                this.message = message
            },
            // standart rest Api delete
            deleteMessage(message) { 
                // messagesApi.remove(message.id).then(result => {
                //     if (result.ok) {
                //         // const index = this.messages.findIndex(item => item.id === data.body.id)
                //         // if (index > -1) {
                //         // this.messages.splice(this.messages.indexOf(message), 1)
                //         // }
                //     }
                // })
            messagesApi.remove(message.id)
            }
        }

    }

 </script>

 <style>



 </style>


