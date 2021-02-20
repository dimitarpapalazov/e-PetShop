import axios from "axios";

const ProductService = {
    allProducts: () => {
        return axios.get("/api/products");
    },
    details: (id) => {
        return axios.get("/api/products/details/"+id);
    },
    add: (product) => {
        return axios.get("/api/products/add", product);
    },
    edit: (id, product) => {
        return axios.get("/api/products/edit/" + id, product);
    }
}

export default ProductService;