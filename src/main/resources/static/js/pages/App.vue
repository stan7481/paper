<template>
    <!-- <messages-list :messages="messages" /> -->
    
        <router-view></router-view>

</template>

<script>
    // import MessagesList from 'components/messages/MessageList.vue'
    import { addHandler } from 'util/ws'

    export default {
        data()  {
            return{
                messages: frontendData.messages,
                // userId: frontendData.usrID
            }
        },
        created() { // socets rest Api
         addHandler(data => {
            if(data.objectType === 'MESSAGE'){
                const index = this.messages.findIndex(item => item.id === data.body.id)

                switch (data.eventType) {
                    case 'CREATE':
                    case 'UPDATE':
                        if (index > -1) {
                            this.messages.splice(index, 1, data.body)
                        } else {
                            this.messages.push(data.body)
                        }
                        break
                    case 'REMOVE':
                        if (index > -1) {
                            this.messages.splice(index, 1)
                        }
                        break
                    default:
                        console.error(`Looks like the object type if unknow "${data.eventType}"`)
                }

            } else {
                console.error(`Looks like the object type if unknown "${data.objectType}"`)
            }
           
         })   

        }
    }
</script>

<style>

</style>