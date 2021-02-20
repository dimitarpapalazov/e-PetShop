import axios from "axios";

const OrderService = {
    allOrders: (username) => {
        return axios.get("/api/orders" + username!= null ? "?username="+username : "");
    },
    add: (order) => {
        return axios.post("/api/orders/save", order);
    },
    addTracking:(id, number) => {
        return axios.post("/api/orders/edit/"+id+"?number="+ number);
    }
}

export default OrderService;