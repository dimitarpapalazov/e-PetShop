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
    edit: (product) => {
        return axios.get("/api/products/edit/" + product.id, product);
    }
}

export default ProductService;