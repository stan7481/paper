 <template>
      <div>
         {{this.liks}} 
         
     <!-- <i class="far fa-heart" @click="like" v-if="liks2"></i> -->
<i v-bind:class=" [liks2] " @click="like" style="color: red"  ></i>
      </div>
       
 </template>
 <script>

  import messagesApi from 'api/messages'

  export default {
    data() {
            return {
                liks: null,
                liks2: "fa fa-heart"
            }
        },

    props: ['message', 'user'],
    computed:{

          

        },
      methods: {
               async like() {

                
                  const data = await messagesApi.changeLiks(this.message.id)
                  const test = await data.json()


                  this.liks = Object.keys( test.likes ).length
                  
                  var BreakException = {};

                  // this.isLike(test.likes)
                   // this.liks2 = "fa fa-heart"
                   // 
                  // this.liks = await data.json()  
                    console.log(test.likes)
                    
                  this.liks2 = "far fa-heart"
try {
                   test.likes.forEach(element => {
                      if(element.id == this.user)
                          { this.liks2 = "fa fa-heart"
                            throw BreakException
                      }
                        else { this.liks2 = "far fa-heart"  }

                   });

                   } catch (e) {
  if (e !== BreakException) throw e;
} 
                     // this.$forceUpdate()

                },

                isLike(datei){
                  // console.log("likeslikes", this.message.likes)
                  //   if(this.message.likes.includes(this.user)){
                  //     this.liks2 = "fa fa-heart"
                  //   }
                  // else{
                  //   this.liks2 = "far fa-heart"
                  // }  
                  
                  console.log("isLike()",datei)
                  console.log("this.user",this.user)

                  // console.log("likeslikes", this.datei)
                    if(datei.includes(this.user)){
                      this.liks2 = "fa fa-heart"
                    }
                  else{
                    this.liks2 = "far fa-heart"
                  } 

                }


              },
      beforeMount() {
          // this.updateProfile()

          this.liks = Object.keys( this.message.likes ).length

          this.isLike(this.message.likes)

        }
      }
    
 </script>

 <style>
 </style>