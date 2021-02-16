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
        <!-- Најавен -->
        <div class="col-lg-6">
          <img class="img-fluid rounded" :src="getImage()" />
          <div class="row">
            <div
              v-for="img in product.images"
              :key="img.index"
              class="col-lg-2"
              v-show="!uploadedImage"
            >
              <img
                @click="changeImage(product.images.indexOf(img))"
                style="cursor: pointer"
                class="img-fluid img-thumbnail"
                :src="img"
              />
            </div>
            <div
              v-for="img in imagesToUpload"
              :key="img.index"
              class="col-lg-2"
              v-show="uploadedImage"
            >
              <img
                @click="changeImage(imagesToUpload.indexOf(img))"
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
              <label for="collection">Одберете име на колекција</label>
              <select
                id="collection"
                v-model="product.collection"
                class="custom-select form-control"
              >
                <option
                  v-for="collection in collections"
                  :key="collection.id"
                  :value="collection.name"
                >
                  {{ collection.name }}
                </option>
              </select>
            </div>
            <div class="col-lg-12 mt-5">
              <label for="type">Одберете тип на производ</label>
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
              <label>Додадете нов тип на производ</label>
              <input
                placeholder="Додадете нов тип на производ"
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
              <label>Внесете попуст на производот</label>
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
              <div v-show="uploadedBar != 0">
                {{ uploadedBar }} % успешно додадени
              </div>
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
export default {
  props: {
    id: {
      default: "",
    },
  },
  data() {
    return {
      uploadedBar: 0,
      type: undefined,
      files: [],
      changeName: true,
      changeType: true,
      changeCollection: true,
      changeQuantity: true,
      changePrice: true,
      products: [],
      collections: [],
      types: [],
      uploadedImage: false,
      oldImages: [],
      imageToShow: 0,
      imagesToUpload: [],
    };
  },
  methods: {
    changeImage(number) {
      this.imageToShow = number;
    },
    getImage() {
      if (this.uploadedImage) return this.imagesToUpload[this.imageToShow];
      return this.product.images[this.imageToShow];
    },
    uploadImage(e) {
      this.files = e.target.files;
      this.oldImage = this.product.images;
      this.uploadedImage = true;
      for (let f of this.files) {
        this.imagesToUpload.push(URL.createObjectURL(f));
      }
      this.getImage();
    },
    addToDb() {},
    addNewType() {},
    getLink(id) {
      return "/details/" + id;
    },
  },
  computed: {
    product() {
      let prod = null;
      for (let p of this.products) {
        if (p[".key"] == this.id) {
          prod = p;
          break;
        }
      }
      return prod;
    },
    loggedIn() {
      return this.$store.state.loggedIn;
    },
  },
  components: {
    "nav-component": Nav,
    "footer-component": Footer,
  },
};
</script>