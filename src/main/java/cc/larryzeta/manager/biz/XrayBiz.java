package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.exception.ReturnException;
import cc.larryzeta.manager.external.model.AddRequest;
import cc.larryzeta.manager.external.FlaskApi;
import cc.larryzeta.manager.external.model.RemoveRequest;
import cc.larryzeta.manager.external.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class XrayBiz {

    @Autowired
    private List<FlaskApi> flaskApiList;


    public Boolean addClient(String email, String uuid) {

        AddRequest addRequest = new AddRequest();
        addRequest.setEmail(email);
        addRequest.setId(uuid);

        flaskApiList.forEach( flaskApi -> {
            try {
                Response response = flaskApi.addClient(addRequest);
            } catch (Exception e) {
                log.error("[XrayBiz-addClient] Exception", e);
                throw new ReturnException();
            }
        } );


        return true;

    }

    public Boolean deleteClient(String email) {

        RemoveRequest removeRequest = new RemoveRequest();
        removeRequest.setEmail(email);
        flaskApiList.forEach( flaskApi -> {
            try {
                Response response = flaskApi.removeClient(removeRequest);

            } catch (Exception e) {
                log.error("[XrayBiz-deleteClient] Exception", e);
                throw new ReturnException();
            }
        } );

        return true;

    }

}
