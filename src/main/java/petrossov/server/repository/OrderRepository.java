package petrossov.server.repository;

import org.springframework.data.repository.CrudRepository;
import petrossov.server.domain.Order;

import java.io.Serializable;

public interface OrderRepository extends CrudRepository<Order, Integer>, Serializable {
}
