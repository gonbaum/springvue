module.exports = {
  moduleFileExtensions: ["vue", "js", "ts"],
  testEnvironment: "jsdom",
  transform: {
    "^.+\\.vue$": "@vue/vue3-jest",
    "^.+\\.jsx?$": "babel-jest",
    "^.+\\.ts$": "ts-jest",
    ".+\\.(css|styl|less|sass|scss|png|jpg|svg|ttf|woff|woff2|webp)$":
      "jest-transform-stub",
  },
  moduleNameMapper: {
    "^@/(.*)$": "<rootDir>/src/$1",
  },
  testRegex: "(/__tests__/.*|(\\.|/)(test|spec))\\.(js|ts)$",
  testEnvironmentOptions: {
    url: "http://localhost/",
    customExportConditions: ["node", "node-addons"],
  },
};
