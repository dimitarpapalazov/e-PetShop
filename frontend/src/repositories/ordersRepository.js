import axios from "axios";

const OrderService = {
    allOrders: (username) => {
        return axios.get("/api/orders" + username!= null ? "?username="+username : "");
    }
}

export default OrderService;