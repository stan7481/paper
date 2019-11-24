 <template>
    <div>
      {{this.likes}} 
      <i v-bind:class=" [likesStyle] " @click="like" style="color: red"  ></i>
    </div>   
 </template>
 <script>
  import messagesApi from 'api/messages'
  export default {
    data() {
      return {
        likes: null,
        likesStyle: "fa fa-heart"
        }
      },
    props: ['message', 'user'],
    methods: {
      async like() {
            const dataFromServer = await messagesApi.changeLiks(this.message.id)
            const dataFromServerJson = await dataFromServer.json()
            this.likes = Object.keys( dataFromServerJson.likes ).length
            var BreakException = {};  
            this.likesStyle = "far fa-heart"
            
            try {
              dataFromServerJson.likes.forEach(element => {
                if(element.id == this.user)
                  { this.likesStyle = "fa fa-heart"
                    throw BreakException
                  }
                  else { this.likesStyle = "far fa-heart"  }
                }
              );
            } catch (e) {
              if (e !== BreakException) throw e;
            } 
        },
        isLike(datei) {
          if(datei.includes(this.user)){
            this.likesStyle = "fa fa-heart"
            }
          else{
              this.likesStyle = "far fa-heart"
              } 

        }
      
    },
    beforeMount() {
      this.likes = Object.keys( this.message.likes ).length
      this.isLike(this.message.likes)
    }
  }  
 </script>

 <style>
 </style>