 <template>
   <div class="card" style="width: 18rem;">
   <div class="card-body">
    <p class="card-text">
        ({{ message.id }}) {{ message.text }} {{ userId }} {{ message.author.id }}

     </p>
        <!-- <div v-if="userId == message.author.id" >/profile/ -->
        <!-- <div v-if="this.$route.path == '/profile/\d+'" > -->
        <div v-if="(this.$route.path.match('prof') != null) && (userId == message.author.id)" >    
            <input type="button" class="btn btn-primary" value="Edit" @click="edit" />
            <input type="button" class="btn btn-primary" value="X" @click="del"  />  
        </div>
            <!-- {{ this.$route.path }} -->

 <!-- console.error(`Looks like the object type if unknow "${data.eventType}"`) -->
    </div>
    <ul class="list-group list-group-flush">
    <li class="list-group-item"> 
        <!-- <a href="/profile/@{{ message.author.username }}"> {{ message.author.username }} </a> -->
        <a :href="'/profile/' + message.author.id">{{message.author.username}}</a>
    </li>
  </ul>
    </div>
 </template>
<!-- {{ message.author.username }} -->
 <script>
    export default {
        data() {
            return {
                
                userId: frontendData.usrID
               // userIdi: userId
            }
        },

            props: ['message', 'messages', 'editMessage', 'deleteMessage'],
        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
            }
        }
    }
 </script>

 <style>

 </style>