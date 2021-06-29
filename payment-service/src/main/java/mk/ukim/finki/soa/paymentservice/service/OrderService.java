package mk.ukim.finki.soa.paymentservice.service;

import mk.ukim.finki.soa.paymentservice.model.Order;
import mk.ukim.finki.soa.paymentservice.model.dto.OrderDto;
import mk.ukim.finki.soa.paymentservice.model.dto.WorkoutProgramDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(OrderDto orderDto, List<WorkoutProgramDto> workoutProgramsDto);

    List<Order> findAll();

    Order findById(Long id);

    Order addItem(Long orderId, WorkoutProgramDto workoutProgramDto);

    Order deleteItem(Long orderId, WorkoutProgramDto workoutProgramDto);

    Order payOrder(Long orderId);

    Order cancelOrder(Long orderId);
}
