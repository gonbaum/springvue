<template>
  <div class="activities__activity overflow-hidden shadow-sm">
    <div class="image-container overflow-hidden">
      <img
        src="@/assets/dummy-image.webp"
        alt="Activity Image"
        class="activities__img w-full"
      />
    </div>
    <span
      class="heart-icon"
      :class="{ favorite: isFavorite }"
      @click="toggleFavorite"
    ></span>
    <div class="px-3 py-1">
      <span class="text-sm font-semibold text-slate-500">ACTIVITY</span>
      <div class="font-bold text-sm text-left mb-1">{{ title }}</div>
      <div class="text-xs text-left text-slate-400 mb-4">
        Activity provider: {{ supplierName }}
      </div>
      <div class="text-sm text-left">
        <span class="star-icon" v-for="i in 5" :key="i">&#9733;</span>
        {{ rating }}/5
      </div>
      <div class="text-left mb-2">
        <span class="text-m font-semibold"
          >From {{ currency }} {{ price }}
        </span>
        <span class="text-xs"> per person</span>
      </div>

      <span
        v-if="specialOffer"
        class="float-right inline-block bg-orange-500 rounded-full px-3 py-1 text-sm font-semibold text-white mb-2"
        >Special Offer</span
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
    supplierName: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      isFavorite: false,
    };
  },
  methods: {
    toggleFavorite() {
      this.isFavorite = !this.isFavorite;
    },
  },
});
</script>

<style scoped>
.star-icon {
  color: #f0d84f;
}
.heart-icon {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 30px;
  height: 28px;
  background-image: url("https://cdn.getyourguide.com/tf/assets/static/icons/heart-photo-sprite.png");
  background-size: cover;
  background-position: -30px 0;
  opacity: 0.5;
  transition: opacity 0.3s;
}
.heart-icon.favorite {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 30px;
  height: 28px;
  background-image: url("https://cdn.getyourguide.com/tf/assets/static/icons/heart-photo-sprite.png");
  background-size: cover;
  background-position: 60px 0;
  opacity: 0.5;
  transition: opacity 0.3s;
}
</style>
