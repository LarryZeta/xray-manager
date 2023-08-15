package cc.larryzeta.manager.external.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddRequest extends RequestBase {

    private static final long serialVersionUID = -3558884321832737258L;

    private String email;

    private String id;

}
