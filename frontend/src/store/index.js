import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedIn: false,
    cart: [],
    types:[],
  },
  mutations: {
    doLogIn(state) {
      localStorage.setItem("loggedIn", true);
      state.loggedIn = true;
    },
    doLogOff(state) {
      localStorage.setItem("loggedIn", false);
      state.loggedIn = false;
    },
    setCartToEmpty(state) {
      state.cart = [];
      localStorage.setItem("cart", JSON.stringify(state.cart));
    },
    addToCart(state, product) {
      state.cart = JSON.parse(localStorage.getItem("cart"));
      for (let p of state.cart) {
        if (p[".key"] == product[".key"]) {
          alert("Производот е веќе додаден во кошничката");
          return;
        }
      }
      if (product.quantity > 0) {
        alert("Производот е успешно додаден во кошничката");
        product.id = product[".key"];
        state.cart.push(product);
        localStorage.setItem("cart", JSON.stringify(state.cart));
      } else {
        alert("Производот е распродаден");
      }
    },
    removeFromCart(state, product) {
      state.cart = JSON.parse(localStorage.getItem("cart"));
      let id = undefined;
      for (let p of state.cart) {
        if (p.id == product.id) {
          console.log("works");
          id = state.cart.indexOf(p);
        }
      }
      if (id != undefined) {
        console.log("id not undef");
        state.cart.splice(id, 1);
        alert("Производот е успешно избришан од кошничката");
      } else {
        alert("Производот не е избришан од кошничката поради некаква грешка");
        console.log("error");
      }
      localStorage.setItem("cart", JSON.stringify(state.cart));
    },
    initialiseStore(state) {
      if (localStorage.getItem("cart") == null) {
        localStorage.setItem("cart", JSON.stringify([]));
      }
      if (localStorage.getItem("loggedIn") == "true") {
        state.loggedIn = true;
      }
      if (localStorage.getItem("cart") != null) {
        state.cart = JSON.parse(localStorage.getItem("cart"));
      }
    },
  },
  actions: {},
  modules: {},
});
