package mk.ukim.finki.wp.project.epetshop.demo.web;


import mk.ukim.finki.wp.project.epetshop.demo.model.Order;
import mk.ukim.finki.wp.project.epetshop.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
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

    //Todo OrderDto
    @PostMapping("/save")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        try {
            Order o = this.orderService.addOrder(order);
            return ResponseEntity.ok().body(o);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Order> save(@PathVariable Long id, @RequestParam Long number) {
        try {
            Order o = this.orderService.editTrackingNumber(id, number);
            return ResponseEntity.ok().body(o);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
