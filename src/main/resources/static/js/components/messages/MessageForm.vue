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

    function getIndex(list, id) {
        for (var i=0; list.length; i++){
            if(list[i].id === id){
                return i
            }
        }

        return -1
    }

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
            const message = {text: this.text}

            if(this.id){
                this.$resource('/message{/id}').update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        const index = getIndex(this.messages, data.id)
                        this.messages.splice(index, 1, data)
                        this.text = ''
                        this.id = ''
                        this.invalid= false
                    })
                )
            } else {
               if (this.text != '') {
                    this.$resource('/message{/id}').save({}, message).then(result => 
                    result.json().then(data => {
                        this.messages.push(data)
                        this.text = ''
                        this.invalid=false
                    })
                )

               }
               else {
                this.invalid= true
                
               }
                
            }

        }
    }
}
</script>

<style>

</style>