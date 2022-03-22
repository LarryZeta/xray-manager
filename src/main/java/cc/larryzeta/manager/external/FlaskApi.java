package cc.larryzeta.manager.external;


import cc.larryzeta.manager.external.model.AddRequest;
import cc.larryzeta.manager.external.model.RemoveRequest;
import cc.larryzeta.manager.external.model.Response;
import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface FlaskApi {

    @RequestLine("POST /add")
    Response addClient(AddRequest addRequest);

    @RequestLine("POST /remove")
    Response removeClient(RemoveRequest removeRequest);

}
