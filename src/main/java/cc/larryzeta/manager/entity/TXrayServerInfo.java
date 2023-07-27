package cc.larryzeta.manager.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_xray_server_info
 * @author 
 */
@Data
public class TXrayServerInfo implements Serializable {
    private Integer id;

    private String address;

    private String serverName;

    private String tag;

    private String remark;

    private static final long serialVersionUID = 1L;
}