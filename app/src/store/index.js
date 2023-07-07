import { createStore } from 'vuex'

export default createStore({
  state: {
    reserves: [],
    infoModal: false,
    chosenReserve: {},
  },
  getters: {
  },
  mutations: {
    setReserves(state, payload) {
      state.reserves = payload;
    },

    switchInfoModal(state, payload) {
      state.infoModal = payload;
    },

    setChosenReserve(state, payload) {
      state.chosenReserve = payload;
    },

    confirmReserve(state) {
      state.chosenReserve.confirmed = true;
    }
  },
  actions: {
    updateReserves({ state, commit }, payload) {
      const reserves = [...state.reserves];
      const index = reserves.findIndex((r) => r.id == payload.id);
      if (!index) return;

      reserves.splice(index, 1, payload);
      commit('setReserves', reserves);
    }
  },
  modules: {
  }
})
