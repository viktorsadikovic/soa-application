package mk.ukim.finki.soa.paymentservice.repository;

import mk.ukim.finki.soa.paymentservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
