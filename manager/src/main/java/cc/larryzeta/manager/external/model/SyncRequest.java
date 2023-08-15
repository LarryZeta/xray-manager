package cc.larryzeta.manager.external.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SyncRequest extends RequestBase {

    private static final long serialVersionUID = 3717663639424982950L;

    private List<Client> clients;


}
