package mk.ukim.finki.soa.paymentservice.web;

import mk.ukim.finki.soa.paymentservice.model.Order;
import mk.ukim.finki.soa.paymentservice.model.dto.InvoiceDto;
import mk.ukim.finki.soa.paymentservice.model.dto.OrderDto;
import mk.ukim.finki.soa.paymentservice.model.dto.UserDto;
import mk.ukim.finki.soa.paymentservice.model.dto.WorkoutProgramDto;
import mk.ukim.finki.soa.paymentservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    LoadBalancerClient loadBalancer;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;;
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getSingleOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping()
    public Order createOrder(@RequestBody OrderDto orderDto, @RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancer.choose("workout-programs-service");

        URI uri = instance.getUri();

        HttpHeaders headers = setHeaders(token);

        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(orderDto.getWorkoutPrograms(), headers);
        ResponseEntity<WorkoutProgramDto[]> programsDto = restTemplate.exchange(uri + "/api/workouts/from-list",
                                                                                HttpMethod.POST,
                                                                                requestEntity,
                                                                                WorkoutProgramDto[].class);
        List<WorkoutProgramDto> workoutPrograms = Arrays.asList(Objects.requireNonNull(programsDto.getBody()));

        return this.orderService.placeOrder(orderDto, workoutPrograms);
    }

    @PostMapping("/{orderId}/add-item/{workoutProgramId}")
    public Order addItemToOrder(@PathVariable Long orderId, @PathVariable Long workoutProgramId, @RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancer.choose("workout-programs-service");

        URI uri = instance.getUri();

        HttpHeaders headers = setHeaders(token);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        WorkoutProgramDto workoutProgramDto = restTemplate.exchange(uri + "/api/workouts/" + workoutProgramId,
                HttpMethod.GET,
                requestEntity,
                WorkoutProgramDto.class).getBody();

        return this.orderService.addItem(orderId, workoutProgramDto);
    }

    @DeleteMapping("/{orderId}/delete-item/{workoutProgramId}")
    public Order deleteItemFromOrder(@PathVariable Long orderId, @PathVariable Long workoutProgramId, @RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancer.choose("workout-programs-service");

        URI uri = instance.getUri();

        HttpHeaders headers = setHeaders(token);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        WorkoutProgramDto workoutProgramDto = restTemplate.exchange(uri + "/api/workouts/" + workoutProgramId,
                HttpMethod.GET,
                requestEntity,
                WorkoutProgramDto.class).getBody();

        return this.orderService.deleteItem(orderId, workoutProgramDto);
    }

    @Transactional
    @PostMapping("/pay/{id}")
    public ResponseEntity<InvoiceDto> payOrder(@PathVariable Long id, @RequestBody UserDto userDto, @RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instanceWorkoutsService = loadBalancer.choose("workout-programs-service");
        ServiceInstance instanceInvoicesService = loadBalancer.choose("invoice-service");

        URI uriWorkoutsService = instanceWorkoutsService.getUri();
        URI uriInvoicesService = instanceInvoicesService.getUri();

        HttpHeaders headers = setHeaders(token);

        Order order = orderService.payOrder(id);

        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(order.getWorkoutPrograms(), headers);
        List<WorkoutProgramDto> workoutPrograms = Arrays.asList(Objects.requireNonNull(restTemplate
                .exchange(uriWorkoutsService + "/api/workouts/from-list",
                HttpMethod.POST,
                requestEntity,
                WorkoutProgramDto[].class).getBody()));

        InvoiceDto invoiceDto = InvoiceDto.create(order, workoutPrograms, userDto.getName(), userDto.getSurname(), userDto.getCardNumber());
        HttpEntity<InvoiceDto> invoiceDtoHttpEntity = new HttpEntity<>(invoiceDto, headers);

        return restTemplate.exchange(uriInvoicesService + "/api/invoices",
                                    HttpMethod.POST,
                                    invoiceDtoHttpEntity,
                                    InvoiceDto.class);
    }

    @PostMapping("/cancel/{id}")
    public Order cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    private HttpHeaders setHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return headers;
    }
}
