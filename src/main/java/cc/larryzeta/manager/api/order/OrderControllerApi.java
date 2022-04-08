package cc.larryzeta.manager.api.order;

import cc.larryzeta.manager.api.model.ResultEntity;

public interface OrderControllerApi {

    ResultEntity<Object> AddOrder(Object o);

    ResultEntity<Object> deleteOrder(Object o);

    ResultEntity<Object> queryOrder(Object o);

    ResultEntity<Object> activeOrder(Object o);

}
