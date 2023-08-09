class ActivityService {
  async getActivities() {
    const response = await fetch("http://localhost:8080/activities", {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    });
    return await response.json();
  }

  async searchActivitiesByTitle(title) {
    const response = await fetch(
      `http://localhost:8080/activities/search?title=${title}`,
      {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      },
    );
    return await response.json();
  }
}

export default new ActivityService();
