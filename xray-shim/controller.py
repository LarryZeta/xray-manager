import logging
import shim_service
import flask

if __name__ == '__main__':
    logging.basicConfig(filename='xray-shim.log', encoding='utf-8', level=logging.DEBUG)
    logger = logging.getLogger()
    app = flask.Flask(__name__)
    service = shim_service.ShimService()


@app.route('/api/add', methods=['POST'])
def add_client():
    logger.info('[controller-add_client]  request.json: ' + str(flask.request.json))
    
    try:
        service.token_auth(flask.request.json)
        service.add_client(flask.request.json['id'], flask.request.json['email'])
    except BaseException:
        return {
            'code': '9999',
            'msg': 'FAIL'
        }
    return {
        'code': '0000',
        'msg': 'SUCCESS'
    }


@app.route('/api/remove', methods=['POST'])
def delete_client():
    logger.info('[controller-delete_client]  request.json: ' + str(flask.request.json))
    try:
        service.token_auth(flask.request.json)
        service.delete_client(flask.request.json['email'])
    except BaseException: 
        return {
            'code': '9999',
            'msg': 'FAIL'
        }
    return {
        'code': '0000',
        'msg': 'SUCCESS'
    }


@app.route('/client/sync', methods=['POST', 'PUT'])
def sync_client():
    logger.info('[controller-sync_client]  request.json: ' + str(flask.request.json))
    try:
        service.token_auth(flask.request.json)
        service.sync_client(flask.request.json['clients'])
    except BaseException: 
        return {
            'code': '9999',
            'msg': 'FAIL'
        }
    return {
        'code': '0000',
        'msg': 'SUCCESS'
    }

@app.route('/test', methods=['POST'])
def test_auth():
    logger.info('[controller-test_auth]  request.json: ' + str(flask.request.json))
    try:
        service.token_auth(flask.request.json)
    except BaseException: 
        return {
            'code': '9999',
            'msg': 'FAIL'
        }
    return {
        'code': '0000',
        'msg': 'SUCCESS'
    }

app.run()