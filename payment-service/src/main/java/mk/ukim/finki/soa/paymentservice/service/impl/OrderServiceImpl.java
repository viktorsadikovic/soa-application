package mk.ukim.finki.soa.paymentservice.service.impl;

import mk.ukim.finki.soa.paymentservice.model.Order;
import mk.ukim.finki.soa.paymentservice.model.dto.OrderDto;
import mk.ukim.finki.soa.paymentservice.model.dto.WorkoutProgramDto;
import mk.ukim.finki.soa.paymentservice.model.enumeration.OrderState;
import mk.ukim.finki.soa.paymentservice.repository.OrderRepository;
import mk.ukim.finki.soa.paymentservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(OrderDto orderDto, List<WorkoutProgramDto> workoutProgramsDto) {
        Order order = new Order();

        order.setWorkoutPrograms(orderDto.getWorkoutPrograms());
        workoutProgramsDto.forEach(workoutProgramDto -> order.setPrice(order.getPrice() + workoutProgramDto.getPrice()));
        order.setState(OrderState.PENDING);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Order addItem(Long orderId, WorkoutProgramDto workoutProgramDto) {
        Order order = findById(orderId);

        order.addItem(workoutProgramDto.getId());
        order.setPrice(order.getPrice() + workoutProgramDto.getPrice());

        return this.orderRepository.save(order);
    }

    @Override
    public Order deleteItem(Long orderId, WorkoutProgramDto workoutProgramDto) {
        Order order = findById(orderId);

        order.removeItem(workoutProgramDto.getId());
        order.setPrice(order.getPrice() - workoutProgramDto.getPrice());

        return this.orderRepository.save(order);
    }

    @Override
    public Order payOrder(Long orderId) {
        Order order = findById(orderId);

        order.setState(OrderState.COMPLETED);

        return order;
    }

    @Override
    public Order cancelOrder(Long orderId) {
        Order order = findById(orderId);

        order.setState(OrderState.CANCELED);

        return order;
    }
}
