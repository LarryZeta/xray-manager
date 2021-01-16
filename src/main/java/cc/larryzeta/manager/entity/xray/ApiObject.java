package cc.larryzeta.manager.entity.xray;

import lombok.Data;

import java.util.List;

@Data
public class ApiObject {

    private String tag;
    private List<String> services;

}
