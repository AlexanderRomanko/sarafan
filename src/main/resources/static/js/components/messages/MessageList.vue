<template>
    <div style="position: relative; width: 300px">
<!--        <message-form :messages="messages" :messageAttr="message"></message-form>-->
<!--        <message-row v-for="message in messages" :key="message.id" :message="message"-->
<!--                     :editMethod="editMethod" :messages="messages"></message-row>-->
    </div>
</template>

<script>
    export default {
        props: ['messages'],
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMethod(message) {
                this.message = message
            }
        }
    }
</script>

<style>

</style>