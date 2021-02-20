import axios from "axios";

const ProductService = {
    allProducts: () => {
        return axios.get("/api/products");
    },
    bestProducts: () => {
        return axios.get("/api/products/most-selling");
    },
    details: (id) => {
        return axios.get("/api/products/details/"+id);
    },
    add: (product) => {
        return axios.post("/api/products/add", product);
    },
    edit: (product) => {
        return axios.post("/api/products/edit/" + product.id, product);
    },
    delete: (id) => {
        return axios.post("/api/products/delete/" + id);
    }
}

export default ProductService;