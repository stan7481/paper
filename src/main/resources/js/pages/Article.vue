<template>
    
    <div>
        <h5>{{title}}</h5>
        <div> {{text}} </div>

<!-- <a :href="'/profile/' + message.author.id">{{message.author.username}}</a> -->
       
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
            title: '',
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

    

    methods: {

        async test1() {

                
                
                const id = this.$route.params.id
                const data = await messagesApi.get(id)
                const jsonData = await data.json()
                   

                // 2) punct. 

               this.id = jsonData.id
               this.text = jsonData.text
               this.title = jsonData.title
                this.$forceUpdate()
               

               
                 
            console.log('jsonText:',this.text) 
            // console.log('test1',jsonData.author.id) 
            }
    },
    beforeMount() {
            this.test1()
           


        }
}
</script>

<style>

</style>