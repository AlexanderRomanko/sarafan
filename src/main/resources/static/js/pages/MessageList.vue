<template>
    <v-container>
        <v-layout v-if="profile" align-space-around justify-start column>
            <message-form :messageAttr="message"></message-form>
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMessage="editMessage">
            </message-row>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapGetters, mapState } from 'vuex'
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: {...mapGetters(['sortedMessages']), ...mapState(['profile'])},
        methods: {
            editMessage(message) {
                this.message = message
            }
        }
    }
</script>

<style>

</style>