package mk.ukim.finki.wp.project.epetshop.demo.service.impl;

import mk.ukim.finki.wp.project.epetshop.demo.model.Order;
import mk.ukim.finki.wp.project.epetshop.demo.repository.OrderRepo;
import mk.ukim.finki.wp.project.epetshop.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Order> findAllOrders() {
        return this.orderRepo.findAll();
    }

    @Override
    public List<Order> findAllOrdersByMember(String username) {
        return this.orderRepo.findAllByMember_Username(username);
    }

    @Override
    public Order addOrder(Order order) {
        return this.orderRepo.save(order);
    }
}
