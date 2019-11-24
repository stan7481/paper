<template>
    
    <div>
        <h5>{{title}}</h5>
        <div> {{text}} </div>
    </div>

</template>

<script>
    import messagesApi from 'api/messages'

export default {
    props: ['messages','messageAttr'],
    data() {
        return {
            
            id: '',
            text: '',
            title: ''
        }
    },
    methods: {

        async getArticle() {
 
                const id = this.$route.params.id
                const article = await messagesApi.get(id) 
                const jsonArticle = await article.json()
                this.id = jsonArticle.id
                this.text = jsonArticle.text
                this.title = jsonArticle.title
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