package mk.ukim.finki.wp.project.epetshop.demo.web;


import mk.ukim.finki.wp.project.epetshop.demo.model.Order;
import mk.ukim.finki.wp.project.epetshop.demo.model.Product;
import mk.ukim.finki.wp.project.epetshop.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    private List<Order> showOrders(@RequestParam(required = false) String username) {
        if(username!=null)
            return this.orderService.findAllOrdersByMember(username);
        else
            return this.orderService.findAllOrders();
    }


}
