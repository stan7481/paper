 <template>
   <div class="card" style="width: 18rem;">
   <div class="card-body">

    <a :href="'/article/' + message.id" ><h5 class="card-title">{{message.title}}</h5></a>
    
     <p class="card-text">
        {{ message.text }}
     </p> 
     
      <p class="card-text"><small class="text-muted">Update date: {{message.creationDate}}</small></p>

        <div v-if="isMyProfile(userId, message.author.id)">    
            <a :href="'/editarticle/' + message.id"> <input type="button" class="btn btn-link" value="Edit"  /> </a>
<!-- @click="edit" -->

            <input type="button" class="btn btn-link" value="X" @click="del"  />  
        </div>
    </div>
    <ul class="list-group list-group-flush" v-if="isntProfile()">
    <li class="list-group-item"> 
       Author: <a :href="'/profile/' + message.author.id"> {{message.author.username}}</a>
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
           },
           isntProfile() {
              return (this.$route.path.match('prof') == null)
           }
        }
    }
 </script>

 <style>



 </style>