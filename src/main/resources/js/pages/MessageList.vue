 <template>
    <div> 
        <div v-if="SubscriptionsExist">    
            <h5>Subscriptions </h5>
            <div class="card-columns"> 
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
},
        data() {
            return {
                message: null,
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
            deleteMessage(message) { 
                messagesApi.remove(message.id)
            },
            async updateMessageList() {

                if(this.usrID !== undefined){
                    const subscrMsg = await messagesApi.getSubscriptionsArticle(this.usrID)
                    this.subscriptionsMessage = await subscrMsg.json()
                }
                const msg = await messagesApi.get()
                this.messages = await msg.json()
                this.$forceUpdate()
            }
        },
        beforeMount() {
            this.updateMessageList()
        }
    }

 </script>

 <style>
 </style>


