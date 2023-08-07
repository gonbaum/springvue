/* eslint-disable no-undef */
module.exports = {
  root: true,
  env: {
    node: true,
  },
  parser: "vue-eslint-parser",
  plugins: ["@typescript-eslint", "vue"],
  extends: [
    "eslint:recommended",
    "plugin:@typescript-eslint/eslint-recommended",
    "plugin:@typescript-eslint/recommended",
    "@vue/eslint-config-typescript",
    "plugin:vue/vue3-recommended",
    "plugin:prettier/recommended",
  ],
  rules: {},
};
