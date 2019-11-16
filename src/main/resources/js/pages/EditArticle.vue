<template>
    
    <div v-if="this.isOwner" >
        <h5>Edit Page</h5>
        <div class="form-group" > 
            <label for="exampleFormControlTextarea1">Title</label>
            <input type="text" class="form-control"  v-bind:class="{ 'is-invalid': invalid }" id="exampleFormControlTextarea1" placeholder="Write something" v-model="title" required>  
            <div class="invalid-feedback">
            Please write something
          </div> 


<!-- <a :href="'/profile/' + message.author.id">{{message.author.username}}</a> -->
       
    <label for="exampleFormControlTextarea11">Article</label>
    <textarea class="form-control" id="exampleFormControlTextarea11" rows="25" v-model="text" placeholder="Write something" required></textarea>
    <div class="invalid-feedback">
            Please write something
          </div>
 </div>

       <!-- <a :href="/profile/"> <input type="button" value="Save" @click="save" class="btn btn-primary mb-2" /></a> -->
       <input type="button" value="Save" @click="save" class="btn btn-primary mb-2" />
    </div>
  <!--   <div v-else>
        Permission denite
    </div> -->

</template>


<script>
    import messagesApi from 'api/messages'

export default {
    props: ['messages','messageAttr'],
    data() {
        return {
            
            id: '',
            title: '',
            text: '',
            invalid: false,
            userId: frontendData.usrID,
            isOwner: true
             
             // messagesApi.get(560).then(result =>
                     // result.json().then(data => { return data })),
            // test: text1.text
        }
    },
    // computed: {
    //     isMyProfile() {
    //         return (this.$route.params.id == null) || (userId == this.$route.params.id)
    //     }

    // },

    watch: {
        messageAttr(newVal, oldVal) {
            this.text = newVal.text
            this.title = newVal.title
            this.id = newVal.id
        }
    },

    methods: {
        save() {          
            const message = {
                id: this.id,
                title: this.title,
                text: this.text
            }

            
               if ((this.text != '')  && (this.title != '')) {
                   
                   if (this.id){
                messagesApi.update(message)
                } else 
                { messagesApi.add(message)}

                this.$forceUpdate()

                window.location.href = "/profile";
                }
                   else {
                    this.invalid= true    
                }
              
            
            
            // this.text = ''
            // this.id = ''
        },
        async test1() {

                if(this.$route.params.id != null) { 
               const id = this.$route.params.id
                const data = await messagesApi.get(id)
                const jsonData = await data.json()
                // 2) punct. 

               this.id = jsonData.id
               if(frontendData.usrID == jsonData.author.id){
                    this.text = jsonData.text
                    this.title = jsonData.title
                    
               } else {
                    this.isOwner = false
                    this.text = "not owner"
               }

               }
                 
            // console.log('test1',frontendData.usrID) 
            // console.log('test1',jsonData.author.id) 
                

this.$forceUpdate()
                



            }
    },
    beforeMount() {
            this.test1()
        }
}
</script>

<style>

</style>