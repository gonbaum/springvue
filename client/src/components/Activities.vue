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
      :supplierName="activity.supplierName"
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
      padding: 0;
      row-gap: 20px;
    }
  }
}
</style>
