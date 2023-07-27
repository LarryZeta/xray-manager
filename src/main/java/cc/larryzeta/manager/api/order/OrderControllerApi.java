package cc.larryzeta.manager.api.order;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.api.order.model.OrderDTO;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface OrderControllerApi {

    @RequestLine("POST /order")
    ResultEntity<String> AddOrder(OrderDTO orderDTO);

    @RequestLine("DELETE /order/{order_id}")
    ResultEntity<String> deleteOrder(String orderId);

    @RequestLine("GET /order/{order_id}")
    ResultEntity<List<OrderDTO>> queryOrder(String orderId);

    @RequestLine("PUT /order/{order_id}/active")
    ResultEntity<OrderDTO> activeOrder(String orderId);

}
