<template>    
        <router-view></router-view>
</template>
<script>
    import { addHandler } from 'util/ws'
    export default {
        data()  {
            return{
            }
        },
        created() {
         addHandler(data => {
            if(data.objectType === 'MESSAGE'){
                // const index = this.messages.findIndex(item => item.id === data.body.id)

                switch (data.eventType) {
                    case 'REMOVE': {console.log("Message ",data.body.id," delete"); break}
                    case 'CREATE': {console.log("test1"); break} 
                    case 'UPDATE': {console.log("test2"); break} 
                     

                        
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