package cc.larryzeta.manager.service;

import cc.larryzeta.manager.entity.TXrayServerInfo;

import java.util.List;

public interface XrayService {

    List<TXrayServerInfo> syncClient();

}
