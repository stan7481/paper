<template>
    
<div>
    <div class="form-group"> 
        <label for="exampleFormControlTextarea1">Write Something</label>
        <input type="text" placeholder="Write something" v-model="text" class="form-control" id="exampleFormControlTextarea1" rows="3" />  </div>
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
            id: ''
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
                    })
                )
            } else {
                this.$resource('/message{/id}').save({}, message).then(result => 
                    result.json().then(data => {
                        this.messages.push(data)
                        this.text = ''
                    })
                )
            }

        }
    }
}
</script>

<style>

</style>