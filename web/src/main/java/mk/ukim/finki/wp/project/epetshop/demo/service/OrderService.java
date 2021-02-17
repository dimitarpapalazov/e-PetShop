package mk.ukim.finki.wp.project.epetshop.demo.service;

import mk.ukim.finki.wp.project.epetshop.demo.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();
    List<Order> findAllOrdersByMember(String username);
    Order addOrder(Order order);
}
