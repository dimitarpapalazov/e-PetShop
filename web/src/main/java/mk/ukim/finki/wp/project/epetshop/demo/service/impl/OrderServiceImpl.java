package mk.ukim.finki.wp.project.epetshop.demo.service.impl;

import mk.ukim.finki.wp.project.epetshop.demo.model.Member;
import mk.ukim.finki.wp.project.epetshop.demo.model.Order;
import mk.ukim.finki.wp.project.epetshop.demo.model.Product;
import mk.ukim.finki.wp.project.epetshop.demo.model.dto.OrderDto;
import mk.ukim.finki.wp.project.epetshop.demo.model.dto.ProductDto;
import mk.ukim.finki.wp.project.epetshop.demo.model.exceptions.InvalidOrder;
import mk.ukim.finki.wp.project.epetshop.demo.model.exceptions.InvalidType;
import mk.ukim.finki.wp.project.epetshop.demo.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.wp.project.epetshop.demo.repository.MemberRepo;
import mk.ukim.finki.wp.project.epetshop.demo.repository.OrderRepo;
import mk.ukim.finki.wp.project.epetshop.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final MemberRepo memberRepo;

    public OrderServiceImpl(OrderRepo orderRepo, MemberRepo memberRepo) {
        this.orderRepo = orderRepo;
        this.memberRepo = memberRepo;
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
    public Order editTrackingNumber(Long id, Long number) {
        Order order=this.findOrder(id);
        order.setTrackingNumber(number);
        return this.orderRepo.save(order);
    }

    @Override
    public Order findOrder(Long id) {
        return this.orderRepo.findById(id).orElseThrow(InvalidOrder::new);
    }

    @Override
    public Optional<Order> addOrder(OrderDto orderDto) {
        Member member = this.memberRepo.findById(orderDto.getUsername())
                .orElseThrow(() -> new InvalidUsernameException());
        //Member member, String phoneNumber, String address, String city, String postalCode, Boolean toDoor, Long trackingNumber
        this.orderRepo.deleteByTrackingNumber(orderDto.getTrackingNumber());
        return Optional.of(this.orderRepo.save(new Order(member, orderDto.getPhoneNumber(), orderDto.getAddress(), orderDto.getCity(), orderDto.getPostalCode(), orderDto.getToDoor(), orderDto.getTrackingNumber())));
    }
}
