 <template>
   <div class="card" style="width: 18rem;">
   <div class="card-body">
    <p class="card-text">
        ({{ message.id }}) {{ message.text }}

     </p>
            <input type="button" class="btn btn-primary" value="Edit" @click="edit" />
            <input type="button" class="btn btn-primary" value="X" @click="del" />


    </div>
    </div>
 </template>

 <script>
    export default {
            props: ['message', 'messages', 'editMessage', 'deleteMessage'],
        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
            }
        }
    }
 </script>

 <style>

 </style>