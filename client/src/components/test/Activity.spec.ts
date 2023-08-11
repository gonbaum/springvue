import { mount } from "@vue/test-utils";
import ActivityCard from "@/components/ActivityCard.vue";

describe("Activities component", () => {
  it("renders correctly with props", () => {
    const props = {
      title: "Sample Title",
      price: 100,
      currency: "USD",
      rating: 4,
      specialOffer: true,
      supplierName: "Sample Supplier",
    };
    const wrapper = mount(ActivityCard, {
      props: props,
    });

    expect(wrapper.text()).toContain(props.title);
    expect(wrapper.text()).toContain(`From ${props.currency} ${props.price}`);
    expect(wrapper.text()).toContain(`${props.rating}/5`);
    expect(wrapper.text()).toContain(props.supplierName);
    expect(wrapper.text()).toContain("Special Offer");
  });

  it("toggles favorite status when heart icon is clicked", async () => {
    const props = {
      title: "Sample Title",
      price: 100,
      currency: "USD",
      rating: 4,
      specialOffer: true,
      supplierName: "Sample Supplier",
    };
    const wrapper = mount(ActivityCard, {
      props: props,
    });

    await wrapper.find(".heart-icon").trigger("click");
    expect(wrapper.vm.isFavorite).toBe(true);
    await wrapper.find(".heart-icon").trigger("click");
    expect(wrapper.vm.isFavorite).toBe(false);
  });
});
