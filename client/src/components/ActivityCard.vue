<template>
  <div class="max-w-sm rounded overflow-hidden shadow-lg">
    <!-- Your card content here -->
    <div class="px-6 py-4">
      <div class="font-bold text-xl mb-2">{{ title }}</div>
    </div>
    <div class="px-6 pt-4 pb-2">
      <!-- Display other activity information here -->
      <span
        v-if="specialOffer"
        class="inline-block bg-blue-500 rounded-full px-3 py-1 text-sm font-semibold text-white mr-2 mb-2"
        >Special Offer</span
      >
      <span
        class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2"
        >Supplier ID: {{ supplierId }}</span
      >
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "ActivityCard",
  props: {
    title: {
      type: String,
      required: true,
    },
    price: {
      type: Number,
      required: true,
    },
    currency: {
      type: String,
      required: true,
    },
    rating: {
      type: Number,
      required: true,
    },
    specialOffer: {
      type: Boolean,
      required: true,
    },
    supplierId: {
      type: Number,
      required: true,
    },
  },
  methods: {
    onButtonClick() {
      // Do something when the button is clicked
      // For example, navigate to the activity details page
      // using Vue Router or perform any other action.
    },
  },
});
</script>

<style>
/* Add any additional styles or override Tailwind CSS classes here */
</style>
