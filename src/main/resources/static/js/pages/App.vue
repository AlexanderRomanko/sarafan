<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.attributes.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>mdi-logout</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'

    export default {
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error('Looks like the event type is unknown "${data.eventType}"')
                    }
                } else {
                    console.error('Looks like the object type is unknown "${data.objectType}"')
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    };
</script>

<style>

</style>