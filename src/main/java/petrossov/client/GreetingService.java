package petrossov.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import petrossov.shared.OrderResp;

import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

  List<OrderResp> list();

  OrderResp save(OrderResp person);

  Boolean delete(OrderResp person);
}
