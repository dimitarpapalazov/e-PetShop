<!--TODO треба да го препрам да е точно за регистрација-->
<template>
  <div class="bg-light">
    <main class="form-signin">
      <form class="mx-auto">
        <router-link to="/">
          <h1 class="text-center">e-PetShop</h1>
        </router-link>
        <h1 class="h3 fw-normal mt-3">Ве молиме регистрирајте се</h1>
        <label for="inputEmail" class="visually-hidden mt-3"
        >Email адреса</label
        >
        <input
            v-model="email"
            type="email"
            id="inputEmail"
            class="form-control"
            required
            autofocus
        />
        <label for="inputPassword" class="visually-hidden mt-3">Пасворд</label>
        <input
            v-model="password"
            type="password"
            id="inputPassword"
            class="form-control"
            required
        />
        <div v-show="errorMessage != undefined" class="text-danger">
          {{ errorMessage }}
        </div>
        <button
            class="w-100 btn btn-lg btn-primary mt-3"
            type="submit"
            @click.prevent="register()"
        >
          Регистрирај се
        </button>
      </form>
    </main>
  </div>
</template>
<script>
export default {
  data() {
    return {
      email: undefined,
      password: undefined,
      errorMessage: undefined,
    };
  },
  methods: {
    register() {},
  },
};
</script>

<style scoped>
.form-signin {
  height: 100vh;
  display: flex;
  align-items: center;
}
</style>