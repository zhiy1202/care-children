import Vue from 'vue'
import VueX from 'vuex'
// Vue.use(VueX)

const state = {

}

const getters = {
    allUserLock(state){
        return state.allUserLock
    },
}

const mutations = {
    setUsername(state,value){
        state.username = value;
    },
}

const actions = {
    getAllUserLock(context){
        axios({
            method:'get',
            url:`http://localhost:8081/getAllUserLock`,
        }).then((response) => {
            const data = response.data.data;
            context.commit('UPDATEALLUSERLOCK',data)
        })
    },
}

const store =new VueX.Store({
    state,
    mutations,
    actions,
    getters

})

export default store