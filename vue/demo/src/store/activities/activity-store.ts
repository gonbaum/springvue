import ActivityService from "../../services/activity-service.ts";
import { createStore } from "vuex";

const activityStore = {
  namespaced: true,
  state: {
    activities: [],
  },
  mutations: {
    setActivities(state, activities) {
      state.activities = activities;
    },
  },
  actions: {
    async fetchActivities({ commit }) {
      const activities = await ActivityService.getActivities();
      commit("setActivities", activities);
    },
  },
  getters: {
    getAllActivities(state) {
      return state.activities;
    },
  },
};

const store = createStore({
  modules: {
    activityStore,
  },
});

export default store;
