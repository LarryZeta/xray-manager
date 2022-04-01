package cc.larryzeta.manager.external.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SyncRequest extends RequestBase {

    private static final long serialVersionUID = 3717663639424982950L;

    @Data
    static class Client implements Serializable {

        private static final long serialVersionUID = -3343902941335697481L;

        private String id;

        private String email;

    }

    private List<Client> clients;


}
