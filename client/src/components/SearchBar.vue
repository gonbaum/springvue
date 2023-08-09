<template>
  <div class="search-bar-hero relative h-40">
    <div
      class="absolute inset-0 bg-cover bg-center z-0"
      style="
        background-image: url(&quot;https://images.contentstack.io/v3/assets/blt06f605a34f1194ff/bltacfaa2c4f8284555/64a15127fa1835ab5718df6c/RewildingQ223-Website-GLO-20.jpg?auto=webp&quot;);
      "
    ></div>
    <div class="absolute inset-0 bg-black opacity-50"></div>

    <div class="flex items-center justify-center h-full relative">
      <img src="@/assets/logo.png" alt="Logo" class="w-20 h-20 mr-2" />

      <div
        class="bg-white py-2 px-2 rounded-full overflow-hidden flex items-center z-10"
      >
        <input
          v-model="searchText"
          type="text"
          class="py-2 px-4 border-none focus:outline-none flex-grow"
          placeholder="Search activities..."
        />
        <button
          class="bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded-full"
          @click="performSearch"
        >
          Search
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  data() {
    return {
      searchText: "",
    };
  },
  methods: {
    performSearch() {
      this.$emit("search", this.searchText);
    },
  },
};
</script>

<style scoped></style>
