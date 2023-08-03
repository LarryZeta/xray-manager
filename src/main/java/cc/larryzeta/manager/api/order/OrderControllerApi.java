package cc.larryzeta.manager.api.order;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.api.order.model.OrderDTO;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface OrderControllerApi {

    @RequestLine("POST /order")
    ResultEntity<String> addOrder(OrderDTO orderDTO);

    @RequestLine("DELETE /order/{order_id}")
    ResultEntity<String> deleteOrder(String orderId);

    @RequestLine("GET /order/{order_id}")
    ResultEntity<OrderDTO> queryOrder(String orderId);

    @RequestLine("GET /orders")
    ResultEntity<List<OrderDTO>> queryOrders(OrderDTO orderDTO);

    @RequestLine("PUT /order/{order_id}")
    ResultEntity<OrderDTO> activeOrder(String orderId);

}
