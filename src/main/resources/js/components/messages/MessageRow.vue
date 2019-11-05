 <template>
   <div class="card" style="width: 18rem;">
   <div class="card-body">
    <p class="card-text">
        {{ message.text }}
     </p>
        <div v-if="isMyProfile(userId, message.author.id)">    
            <input type="button" class="btn btn-primary" value="Edit" @click="edit" />
            <input type="button" class="btn btn-primary" value="X" @click="del"  />  
        </div>
    </div>
    <ul class="list-group list-group-flush">
    <li class="list-group-item"> 
        <a :href="'/profile/' + message.author.id">{{message.author.username}}</a>
    </li>
  </ul>
    </div>
 </template>
 <script>
    export default {
        data() {
            return {
                
                userId: frontendData.usrID
            }
        },

            props: ['message', 'messages', 'editMessage', 'deleteMessage'],
        
        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
            },
            isMyProfile(user, authId) {
               return ((this.$route.path.match('prof') != null) && (user == authId))
           }
        }
    }
 </script>

 <style>

 </style>