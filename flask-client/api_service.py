import json
import logging
import os


logging.basicConfig(filename='service.log', encoding='utf-8', level=logging.DEBUG)


# 从文件加载配置文件
def load_config():
    xray_config = {}
    with open("./config.json", encoding='utf-8') as config:
        xray_config = json.load(config)
        logging.info('[service-load_config] 读取配置文件完成')
    return xray_config


# 写入配置文件
def save_config(xray_config):
    with open("./config.json", "w", encoding='utf-8') as config:
        json.dump(xray_config, config)
        logging.info('[service-save_config] 写入配置文件完成')


# 创建新账号json对象
def build_client(email, id, protocol):
    client = {}
    client['id'] = id
    client['email'] = email
    client['level'] = 1
    if 'vless' == protocol:
        client['flow'] = 'xtls-rprx-direct'
    if 'vmess' == protocol:
        client['alterId'] = 16
    logging.info('[service-new_client] 创建新账号 [%s] - [%s]', protocol, str(client))
    return client


# 添加新账号功能
def add_new_account(id, email):

    logging.info('[service-add_new_account] email:[%s], id:[%s]', email, id)

    xray_config = load_config()
    inbounds = xray_config['inbounds']

    for inbound in inbounds:
        protocol = inbound['protocol']
        clients = inbound['settings']['clients']
        # email相同的 直接跳过
        for client in clients:
            if client.get('email') is not None and email == client['email']:
                logging.warn('[service-add_new_account] [%s]- [%s] 邮箱账号已存在，结束', protocol, email)
                return
        client = build_client(email, id, protocol)
        clients.append(client)
    
    save_config(xray_config)

    os.system('service xray restart')



# 删除账号功能
def delete_account(email):

    logging.info('[service-del_account] email:[%s]', email)

    xray_config = load_config()
    inbounds = xray_config['inbounds']
    for inbound in inbounds:
        protocol = inbound['protocol']
        logging.info('[service-del_account] protocol: [%s]', protocol)
        clients = inbound['settings']['clients']
        # email查重删除
        clients = list(filter(lambda client : client.get('email') is None or email != client['email'], clients))
        inbound['settings']['clients'] = clients

    logging.info('[service-del_account] 删除匹配到的账号')
    save_config(xray_config)
    
    os.system('service xray restart')

