from asyncio.log import logger
import json
import logging
import os
import shim_config

class ShimService():

    if __name__ == '__init__':
        logging.basicConfig(filename='xray-shim.log', encoding='utf-8', level=logging.DEBUG)
        logger = logging.getLogger()


    # 从文件加载配置文件
    def load_config(self):
        xray_config = {}
        path = shim_config.config_path
        with open(path, encoding='utf-8') as f:
            xray_config = json.load(f)
            logger.info('[service-load_config] 读取配置文件完成')
        return xray_config


    # 写入配置文件
    def save_config(self, xray_config):
        path = shim_config.config_path
        with open(path, "w", encoding='utf-8') as f:
            json.dump(xray_config, f)
            logger.info('[service-save_config] 写入配置文件完成')


    # 创建新账号json对象
    def build_client(self, email, id, protocol):
        client = {}
        client['id'] = id
        client['email'] = email
        client['level'] = 1
        if 'vless' == protocol:
            client['flow'] = 'xtls-rprx-direct'
        if 'vmess' == protocol:
            client['alterId'] = 16
        logger.info('[service-new_client] 创建新账号 [%s] - [%s]', protocol, str(client))
        return client


    # 添加新账号功能
    def add_client(self, id, email):

        logger.info('[service-add_client] email:[%s], id:[%s]', email, id)

        xray_config = self.load_config()
        inbounds = xray_config['inbounds']

        for inbound in inbounds:
            protocol = inbound['protocol']
            clients = inbound['settings']['clients']
            # email相同的 直接跳过
            for client in clients:
                if client.get('email') is not None and email == client['email']:
                    logging.warn('[service-add_client] [%s]- [%s] 邮箱账号已存在，结束', protocol, email)
                    return
            client = self.build_client(email, id, protocol)
            clients.append(client)
        
        self.save_config(xray_config)

        os.system('service xray restart')



    # 删除账号功能
    def delete_client(self, email):

        logger.info('[service-delete_client] email:[%s]', email)

        xray_config = self.load_config()
        inbounds = xray_config['inbounds']
        for inbound in inbounds:
            protocol = inbound['protocol']
            logging.info('[service-delete_client] protocol: [%s]', protocol)
            clients = inbound['settings']['clients']
            # email查重删除
            clients = list(filter(lambda client : client.get('email') is None or email != client['email'], clients))
            inbound['settings']['clients'] = clients

        logger.info('[service-delete_client] 删除匹配到的账号')
        self.save_config(xray_config)
        
        os.system('service xray restart')


    # 同步账号功能
    def sync_client(self, clients):
        
        logger.info('[service-sync_client] clients:[%s]', clients)

        xray_config = self.load_config()
        inbounds = xray_config['inbounds']
        for inbound in inbounds:
            protocol = inbound['protocol']
            logger.info('[service-sync_client] protocol: [%s]', protocol)
            update_clients = []
            for account in clients:
                update_client = self.build_client(account['email'], account['id'], protocol)
                update_clients.append(update_client)
            inbound['settings']['clients'] = update_clients

        logger.info('[service-sync_client] 同步账号到文件')
        self.save_config(xray_config)

        os.system('service xray restart')
