 <template>
  
  <div> 
    
<h5 v-if="isMyProfile">My Profile</h5>
<h5 v-else >{{profile.username}}</h5>
  <!-- <div class="col-sm-6"> -->
    <div>
       
        <div class="card-text">
            <div>Subscription: {{Subscribtions}}</div>
            <div>Subscribers: {{Subscribers}}</div>
       <button type="button" class="btn btn-link text-right"
            v-if="!isMyProfile" 
            @click="changeSubscriptions" 
        >
            {{isISubscribed ? '- Unsubscribe' : '+ Subscribe'}}
            <!-- {{isISubscribed}} -->
        </button>
        </div>
         
    </div>  

       
 
        
   
  <!-- </div> -->

    <a :href="/editarticle/" v-if="isMyProfile" > <input type="button" class="btn btn-link text-right" value="+ New article"  /> </a>

    <!-- <message-form :messages="messages" :messageAttr="message" /> -->
        <div class="card-columns" > 
      
            <message-row  v-for="message in messages" :key="message.id"
                :message="message" :messages="messages" :editMessage="editMessage" :deleteMessage="deleteMessage"/>
        </div>

  </div>
     
 </template>
 
 <script>
     
     import MessageRow from 'components/messages/MessageRow.vue' 
    // import MessageForm from 'components/messages/MessageForm.vue'
    import messagesApi from 'api/messages'
    import profileApi from 'api/profile'

    export default {
        components: {
            MessageRow,
            // MessageForm
        },
        data() {
            return {
                message: null,
                userId: frontendData.usrID,
                currUser: (this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID,
                // messages: messages.filter(message => message.author.id == ((this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID)  ),
                messages: {},
                profile: {},
                userName: ''
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

              const test2 = (this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID

                console.log("updateProfile")
               const id = this.$route.params.id || this.userId
                const data = await profileApi.get(id)
                this.profile = await data.json()
                const test = await messagesApi.getOnlyOwnerArticles(test2)
                this.messages = await test.json()
                const test1 = await test.json()


                 this.$forceUpdate()
            console.log('1',test1)
            },
             

           
            
    },
        beforeMount() {
            this.updateProfile()


        }
}

 </script>

 <style>
    


 </style>


