<template>
  <div class="filter-info mt-6">
    <p class="text-gray-500">
      Showing results for "{{ searchQuery }}"
      <button
        @click="clearFilter"
        class="rounded-full py-1 px-3 ml-2 text-slate-500 hover:text-blue-700 cursor-pointer"
      >
        Clear Filter
      </button>
    </p>
  </div>
</template>

<script>
export default {
  props: {
    searchQuery: String,
  },
  methods: {
    clearFilter() {
      this.$emit("clear-filter");
    },
  },
};
</script>

<style scoped></style>
