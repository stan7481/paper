 <template>
  
  <div> 
    <message-form :messages="messages" :messageAttr="message" />
        <div class="card-columns" > 
      
            <message-row  v-for="message in messages" :key="message.id"
                :message="message" :messages="messages" :editMessage="editMessage" :deleteMessage="deleteMessage"/>
        </div>


        <div>Subscription: {{Subscribtions}}</div>
        <div>Subscribers: {{Subscribers}}</div>

        <button type="button" class="btn btn-outline-primary"
            v-if="!isMyProfile" 
            @click="changeSubscriptions" 
        >
            {{isISubscribed ? 'Unsubscribe' : 'Subscribe'}}
            <!-- {{isISubscribed}} -->
        </button>


         

  </div>
     
 </template>
 
 <script>
     
     import MessageRow from 'components/messages/MessageRow.vue' 
    import MessageForm from 'components/messages/MessageForm.vue'
    import messagesApi from 'api/messages'
    import profileApi from 'api/profile'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null,
                userId: frontendData.usrID,
                currUser: (this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID,
                messages: messages.filter(message => message.author.id == ((this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID)  ),
                profile: {}
            }
        },
        computed:{
            isMyProfile() {
                return (!this.$route.params.id || 
                    this.$route.params.id == this.userId)

            },
             isISubscribed() {
                // return false
                // console.log('3',this.profile.subscribers.length)

                if(this.$route.params.id){

                    return this.profile.subscribers 
                && this.profile.subscribers.find(subscription => {

                    console.log('4',subscription)
                        if(subscription.id != null) {
                             return subscription.id == this.userId
                        } else {
                            return subscription == this.userId
                        }

                        
                       // console.log('2', subscription)
                       //  return true
                    }) 
                }
                else{
                    return false
                }

               
            },
            Subscribers() {  
                 if (this.profile.id != null) {

                    return Object.keys( this.profile.subscribers ).length
                }
                return 0

             

            },
            Subscribtions() {
                if (this.profile.id != null) {

                    return Object.keys( this.profile.subscriptions ).length
                }
                return 0
               

            }

       
        },
          watch: {
            '$route'() {
                this.updateProfile()

            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) { 
                this.messages.splice(this.messages.indexOf(message), 1)
            messagesApi.remove(message.id)
            },

            async changeSubscriptions() {
                const data = await profileApi.changeSubscriptions(this.profile.id)
                this.profile = await data.json()        


           },

           async updateProfile() {

                console.log("updateProfile")
               const id = this.$route.params.id || this.userId
                const data = await profileApi.get(id)
                this.profile = await data.json()

                 this.$forceUpdate()
            console.log('1',this.profile)
            },
           
            
    },
        beforeMount() {
            this.updateProfile()


        }
}

 </script>

 <style>



 </style>


