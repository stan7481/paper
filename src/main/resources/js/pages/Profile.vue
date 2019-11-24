 <template>
  
  <div> 
    <h5 v-if="isMyProfile">My Profile</h5>
    <h5 v-else >{{profile.username}}</h5>
        <div>
            <div class="card-text">
                <div>Subscription: {{Subscribtions}}</div>
                <div>Subscribers: {{Subscribers}}</div>
                <button type="button" class="btn btn-link text-right"
                    v-if="!isMyProfile" 
                    @click="changeSubscriptions" 
                >
                    {{isISubscribed ? '- Unsubscribe' : '+ Subscribe'}}
                </button>
            </div>      
        </div>  

        <a :href="/editarticle/" v-if="isMyProfile" >  
            <input type="button" class="btn btn-link text-right" value="+ New article"  /> 
        </a>
        <div class="card-columns" >   
            <message-row  v-for="message in messages" :key="message.id"
                    :message="message" :messages="messages" :editMessage="editMessage" :deleteMessage="deleteMessage"/>
        </div>
  </div>
     
 </template>
 
 <script>
     
    import MessageRow from 'components/messages/MessageRow.vue' 
    import messagesApi from 'api/messages'
    import profileApi from 'api/profile'

    export default {
        components: {
            MessageRow,
        },
        data() {
            return {
                message: null,
                userId: frontendData.usrID,
                currUser: (this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID,
                messages: {},
                profile: {}
            }
        },
        computed:{
            isMyProfile() {
                return (!this.$route.params.id || 
                    this.$route.params.id == this.userId)

            },
             isISubscribed() {
                if(this.$route.params.id){
                    return this.profile.subscribers 
                && this.profile.subscribers.find(subscription => {

                    console.log('4',subscription)
                        if(subscription.id != null) {
                             return subscription.id == this.userId
                        } else {
                            return subscription == this.userId
                        }
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
            deleteMessage(message) { 
                this.messages.splice(this.messages.indexOf(message), 1)
            messagesApi.remove(message.id)
            },

            async changeSubscriptions() {
                const dataFromServer = await profileApi.changeSubscriptions(this.profile.id)
                this.profile = await dataFromServer.json()        


            },
            async updateProfile() {

                const usr = (this.$route.params.id != null) ? this.$route.params.id : frontendData.usrID
                const id = this.$route.params.id || this.userId
                const data = await profileApi.get(id)
                this.profile = await data.json()
                const ownersArticles = await messagesApi.getOnlyOwnerArticles(usr)
                this.messages = await ownersArticles.json()
                this.$forceUpdate()
            },
       
        },
        beforeMount() {
            this.updateProfile()
        }
}

 </script>
 <style>
 </style>


