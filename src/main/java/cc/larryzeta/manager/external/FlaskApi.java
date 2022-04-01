package cc.larryzeta.manager.external;


import cc.larryzeta.manager.external.model.AddRequest;
import cc.larryzeta.manager.external.model.RemoveRequest;
import cc.larryzeta.manager.external.model.Response;
import cc.larryzeta.manager.external.model.SyncRequest;
import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface FlaskApi {

    @RequestLine("POST /client/add")
    Response addClient(AddRequest addRequest);

    @RequestLine("POST /client/remove")
    Response removeClient(RemoveRequest removeRequest);

    @RequestLine("POST /client/sync")
    Response syncClients(SyncRequest syncRequest);

}
