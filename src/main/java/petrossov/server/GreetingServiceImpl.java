package petrossov.server;

import petrossov.client.GreetingService;
import petrossov.server.domain.Order;
import petrossov.server.repository.OrderRepository;
import petrossov.server.service.AppContext;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import petrossov.shared.OrderResp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */

public class GreetingServiceImpl extends RemoteServiceServlet
        implements GreetingService, Serializable {

 private final OrderRepository orders = AppContext.getBean(OrderRepository.class);


  @Override
  public List<OrderResp> list() {
    List<OrderResp> orders = new ArrayList<>();
    for (Order order : this.orders.findAll()) {
      orders.add(new OrderResp(order.getId(),order.getOrderName(),
              order.getClientName(),order.getDate(),order.getAddress(),
              order.getPhoneNumber(),order.getPrice(),order.getPrepay()));
    }
    return orders;
  }

  @Override
  public OrderResp save(OrderResp data) {
    Order order = new Order();
    if (data.getId() !=-1) {
        order.setId(data.getId());
    }
   order.setOrderName(data.getOrderName());
   order.setClientName(data.getClientName());
   order.setDate(data.getDate());
   order.setAddress(data.getAddress());
   order.setPhoneNumber(data.getPhoneNumber());
   order.setPrice(data.getPrice());
   order.setPrepay(data.getPrepay());
    this.orders.save(order);
    data.setId(order.getId());
      System.out.println("DATA = " + data);
      System.out.println("ORDER ID = " + order.getId());
      for (Order p : orders.findAll()) {
          System.out.println("OrderRepository = " + p);
      }
    return data;
  }

  @Override
  public Boolean delete(OrderResp order) {
    this.orders.delete(order.getId());
    return true;
  }
}
