<script>
import { mapState, mapActions } from "vuex";
import ActivityCard from "./ActivityCard.vue";

export default {
  name: "ActivitiesComponent",
  components: {
    ActivityCard,
  },
  computed: {
    ...mapState("activityStore", ["activities"]),
  },
  async mounted() {
    this.fetchActivities();
  },
  methods: {
    ...mapActions("activityStore", ["fetchActivities"]),
  },
};
</script>

<template>
  <div class="activities__container">
    <ActivityCard
      v-for="activity in activities"
      :key="activity.id"
      :title="activity.title"
      :price="activity.price"
      :currency="activity.currency"
      :rating="activity.rating"
      :specialOffer="activity.specialOffer"
      :supplierId="activity.supplierId"
    />
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
    padding: 0 20px;
  }
  &__activity {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    max-width: 300px;
    margin: 0 20px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-align: center;
    transition: all 0.3s ease-in-out;
    &:hover {
      border: 1px solid #000;
      box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
    }
  }
}

@media screen and (min-width: 768px) {
  .activities {
    &__container {
      width: 100%;
      padding: 0;
      row-gap: 20px;
    }
  }
}
</style>
