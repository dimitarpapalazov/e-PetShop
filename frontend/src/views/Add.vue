<template>
  <div class="bg-light">
    <nav-component></nav-component>
    <div class="container p-5 text-center mt-5" v-show="!loggedIn">
      <span class="h1 text-danger">
        Не сте автентицирани за да имате пристап до оваа страна
      </span>
    </div>
    <div class="container p-5 mt-5" v-show="loggedIn">
      <div class="row bg-white p-5 rounded">
        <!-- Овде ќе има промена -->
        <div class="col-lg-6">
          <img class="img-fluid" :src="imageToShow" />
          <div class="row">
            <div
              v-for="img in imagesToUpload"
              :key="img.index"
              class="col-lg-2"
            >
              <img
                @click="getImage(imagesToUpload.indexOf(img))"
                style="cursor: pointer"
                class="img-fluid img-thumbnail"
                :src="img"
              />
            </div>
          </div>
          <input
            class="form-control-file"
            type="file"
            accept="image/*"
            @change="uploadImage"
            multiple
          />
        </div>
        <div class="col-lg-6">
          <div class="row">
            <div class="col-lg-12">
              <label>Внесете име на производот</label>
              <input
                placeholder="Внесете име на производот"
                type="text"
                v-model="product.name"
                class="form-control my-auto"
              />
            </div>
            <div class="col-lg-12 mt-5">
              <label>Внесете цена на производот</label>
              <input
                placeholder="Внесете цена на производот"
                type="text"
                v-model="product.price"
                class="form-control my-auto"
              />
            </div>
            <div class="col-lg-12 mt-5">
              <label for="type">Одберете категорија на производ</label>
              <select
                id="type"
                v-model="product.type"
                class="custom-select form-control"
              >
                <option v-for="type in types" :key="type.id" :value="type.name">
                  {{ type.name }}
                </option>
              </select>
            </div>
            <div class="col-lg-12 mt-5 p-3 rounded bg-primary">
              <label>Додадете нов категорија на производ</label>
              <input
                placeholder="Додадете нов категорија на производ"
                type="text"
                v-model="type"
                class="form-control my-auto"
                @keyup.enter="addNewType()"
              />
            </div>
            <div class="col-lg-12 mt-5">
              <small class="text-primary"
                >Ова нема да се прикажува на купувачите</small
              >
              <label>Внесете залиха на производот</label>
              <input
                placeholder="Внесете залиха на производот"
                type="text"
                v-model="product.quantity"
                class="form-control my-auto"
              />
            </div>
            <div class="col-lg-12 mt-5">
              <label for="type">Внесете попуст на производот</label>
              <input
                placeholder="Внесете попуст на производот"
                type="text"
                v-model="product.sale"
                class="form-control my-auto"
              />
            </div>
            <div class="col-lg-12">
              <button
                @click="addToDb()"
                class="mt-5 btn btn-lg btn-primary rounded"
              >
                Зачувај
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer-component></footer-component>
  </div>
</template>

<script>
import Nav from "../components/Navbar.vue";
import Footer from "../components/Footer.vue";
import ProductService from "@/repositories/productsRepository";
export default {
  data() {
    return {
      type: undefined,
      product: {
        price: undefined,
        name: undefined,
        type: undefined,
        quantity: undefined,
        images: [],
        sale: null,
        sold: 0,
        sharingMembers: []
      },
    };
  },
  methods: {
    addToDb() {
      ProductService.add(this.product).then(() => {console.log("success")}).catch((e) => {
        console.log(e)
      })
    },
    addNewType() {},
  },
  computed: {
    loggedIn() {
      return this.$store.state.loggedIn;
    },
    types() {
      return null;
    }
  },
  components: {
    "nav-component": Nav,
    "footer-component": Footer,
  },
};
</script>
