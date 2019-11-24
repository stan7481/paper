 <template>
    <div > 

        <div v-if="SubscriptionsExist">
            
            <h5>Subscriptions </h5>
            <div class="card-columns" > 
            <message-row  v-for="message in subscriptionsMessage" :key="message.id"
                :message="message" :messages="subscriptionsMessage" />
                </div>
        </div>


        <h5>All Articles </h5>
        <div class="card-columns" > 


             <message-row  v-for="message in messages" :key="message.id"
                :message="message" :messages="messages"/>
        </div>

    </div>
     
 </template>
 
 <script>
    import MessageRow from 'components/messages/MessageRow.vue'
   
    import messagesApi from 'api/messages'

    export default {
        components: {
            MessageRow,

            // MessageForm
        },
        data() {
            return {
                message: null,
               // messages: frontendData.messages,
            messages: null,
            usrID: frontendData.usrID,
            subscriptionsMessage: {}
            }
        },
        computed:{
            SubscriptionsExist() {
                return (Object.keys( this.subscriptionsMessage ).length > 0)

            }

        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) { 
                messagesApi.remove(message.id)
            },
            async updateMessageList() {

                if(this.usrID !== undefined){

                    const test = await messagesApi.getSubscriptionsArticle(this.usrID)
                    this.subscriptionsMessage = await test.json()
                    
                    

                    // console.log(test3)

                }


                const test2 = await messagesApi.get()
                const test3 = await test2.json()
                this.messages = await test2.json()
                // this.messages = messages
                this.$forceUpdate()
                
            }

        },
        beforeMount() {
            this.updateMessageList()


        }

    }

 </script>

 <style>
/*.card-columns {
  @include media-breakpoint-only(lg) {
    column-count: 4;
  }
  @include media-breakpoint-only(xl) {
    column-count: 5;
  }
}*/


 </style>


