package cc.larryzeta.manager.entity;

import lombok.Data;

@Data
public class TXrayServerInfo {
    private String id;

    /**
    * 服务器IP或域名
    */
    private String address;

    /**
    * 服务器名
    */
    private String serverName;

    /**
    * 服务器标签，可用作标记地区
    */
    private String tag;

    /**
    * 备注
    */
    private String remark;
}