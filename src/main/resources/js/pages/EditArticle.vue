<template>   
    <div v-if="this.isOwner" >
        <h5>Edit Page</h5>
        <div class="form-group" > 
            <label for="titel">Title</label>
            <input type="text" class="form-control"  v-bind:class="{ 'is-invalid': invalid }" id="titel" placeholder="Write something" v-model="title" required>  
            <div class="invalid-feedback">
                Please write something
            </div> 
       
            <label for="article">Article</label>
            <textarea class="form-control" id="article" rows="25" v-model="text" placeholder="Write something" required></textarea>
            <div class="invalid-feedback">
                Please write something
            </div>
        </div>
        <input type="button" value="Save" @click="save" class="btn btn-primary mb-2" />
    </div>
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
                 
            }
        },
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
                    if (this.id){messagesApi.update(message)} 
                        else {messagesApi.add(message)}
                    this.$forceUpdate()
                    window.location.href = "/profile";
                } else {
                    this.invalid= true    
                }
                  
            },
            async getArticle() {
                if(this.$route.params.id != null) { 
                    const id = this.$route.params.id
                    const article = await messagesApi.get(id)
                    const jsonArticle = await article.json()

                       this.id = jsonArticle.id
                       if(frontendData.usrID == jsonArticle.author.id){
                            this.text = jsonArticle.text
                            this.title = jsonArticle.title
                            
                       } else {
                            this.isOwner = false
                            this.text = "not owner"
                       }
                   }

                this.$forceUpdate()
                }
        },
        beforeMount() {
                this.getArticle()
            }
    }
</script>

<style>

</style>