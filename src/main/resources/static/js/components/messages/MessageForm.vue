<template>
    
<div>
    <div class="form-group"> 
        <input type="text" class="form-control"  v-bind:class="{ 'is-invalid': invalid }"  v-model="text"  id="exampleFormControlTextarea1" placeholder="Write something" required>  
        <div class="invalid-feedback">
        Please write something
      </div> </div>



    <input type="button" value="Save" @click="save" class="btn btn-primary mb-2" />
</div>

</template>


<script>
    import messagesApi from 'api/messages'

    

export default {
    props: ['messages','messageAttr'],
    data() {
        return {
            text: '',
            id: '',
            invalid: false
        }
    },

    watch: {
        messageAttr(newVal, oldVal) {
            this.text = newVal.text
            this.id = newVal.id
        }
    },

    methods: {
        save() {          
            const message = {
                id: this.id,
                text: this.text
            }

            if(this.id){
                messagesApi.update(message).then(result =>
                    result.json().then(data => {
                        const index = this.messages.findIndex(item => item.id === data.id)
                        this.messages.splice(index, 1, data)
                        this.invalid= false
                    })
                )
            } else {
               if (this.text != '') {
                    messagesApi.add(message).then(result => 
                    result.json().then(data => {
                        const index = this.messages.findIndex(item => item.id === data.id)

                        if(index > -1) {
                            this.messages.splice(index, 1, data)
                        } else {
                           this.messages.push(data) 
                        }


                        
                        
                        this.invalid=false
                    })
                )

               }
               else {
                this.invalid= true
                
               }
                
            }

            this.text = ''
            this.id = ''
        }
    }
}
</script>

<style>

</style>