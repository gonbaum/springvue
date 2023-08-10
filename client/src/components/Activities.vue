<script>
import { mapState, mapActions } from "vuex";
import ActivityCard from "./ActivityCard.vue";
import SearchBar from "./SearchBar.vue";

export default {
  name: "ActivitiesComponent",
  components: {
    ActivityCard,
    SearchBar,
  },
  data() {
    return {
      searchQuery: "",
      fetchError: null, // Initialize fetchError to null
    };
  },
  computed: {
    ...mapState("activityStore", ["activities"]),
  },
  async mounted() {
    try {
      await this.fetchActivities();
    } catch (error) {
      this.fetchError = "An error occurred while fetching activities.";
    }
  },
  methods: {
    ...mapActions("activityStore", [
      "fetchActivities",
      "searchActivitiesByTitle",
    ]),
    async search(searchText) {
      this.searchQuery = searchText;
      this.fetchError = null;
      try {
        await this.searchActivitiesByTitle(searchText);
      } catch (error) {
        this.activities = [];
        this.fetchError =
          "An error occurred while fetching activities. Please try later.";
      }
    },
  },
};
</script>

<template>
  <SearchBar @search="search" />
  <div class="activities__container">
    <ActivityCard
      v-for="activity in activities"
      :key="activity.id"
      :title="activity.title"
      :price="activity.price"
      :currency="activity.currency"
      :rating="activity.rating"
      :specialOffer="activity.specialOffer"
      :supplierName="activity.supplierName"
    />
    <!-- Display error message if fetchError is not null -->
    <div v-if="fetchError" class="text-center mt-4 text-red-500">
      {{ fetchError }}
    </div>
    <div
      v-if="activities.length === 0 && !fetchError"
      class="text-center mt-4 text-gray-500"
    >
      No results for "{{ searchQuery }}"
    </div>
  </div>
</template>

<style lang="scss">
.activities {
  &__container {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
    max-width: 1200px;
    padding: 20px;
    row-gap: 0;
  }
  &__activity {
    position: relative;

    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    max-width: 300px;
    min-width: 300px;

    height: 400px;
    margin: 10px 10px;
    padding: 0px;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-align: left;
    transition: all 0.3s ease-in-out;
    &:hover {
      border: 1px solid #000;
      box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
      cursor: pointer;
    }
  }
  &__img {
    width: 100%;
    height: auto;
    transition: transform 0.5s ease-in-out;
  }
  &__activity:hover &__img {
    transform: scale(1.2);
  }
}

@media screen and (min-width: 768px) {
  .activities {
    &__container {
      width: 100%;
      padding: 20px;
      row-gap: 0;
    }
  }
}
</style>
