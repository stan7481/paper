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
    <ul class="list-group list-group-flush" >
    <li class="list-group-item"> 
     <div class="d-flex">
      <span v-if="isntProfile()" >Author: <a :href="'/profile/' + message.author.id"> {{message.author.username}}</a> </span> 
      <!-- <i class="fa fa-heart" aria-hidden="true"></i> -->
      <!-- <span class="ml-auto"><i class="fa fa-heart" aria-hidden="true"></i></span> -->
      <span class="ml-auto"><like :message="message" :user="userId" /></span>
      <!-- <p class="text-right">like</p> -->
       <!-- <like /> -->

        


     </div>
    </li>
    
       
    </li>
  </ul>
  
    </div>
 </template>
 <script>
  import Like from 'components/messages/Like.vue'


    export default {
      
      components: {
            Like
        },
        data() {
            return {
                
                userId: frontendData.usrID
            }
        },

            props: ['message', 'messages', 'deleteMessage'],
        
        methods: {
            
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