<template>
    <div>
        <div v-if="!profile">You need to authorize with
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>{{profile.attributes.name}}&nbsp;<a href="/logout">Logout</a> </div>
            <messages-list :messages="messages"></messages-list>
        </div>
    </div>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'
    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>