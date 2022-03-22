import api_service
from flask import Flask, request

app = Flask(__name__)

@app.route('/api/add', methods=['POST'])
def add_client():
    print(request.json)
    json = request.json
    id = json['id']
    email = json['email']
    try:
        api_service.add_client(id, email)
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
    print(request.json)
    json = request.json
    email = json['email']
    try:
        api_service.delete_client(email)
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
    print(request.json)
    clients = request.json['clients']
    try:
        api_service.sync_client(clients)
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