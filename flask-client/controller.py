import api_service
from flask import Flask, request

app = Flask(__name__)

@app.route('/api/add', methods=['POST'])
def add_new_account():
    print(request.json)
    json = request.json
    id = json['id']
    email = json['email']
    try:
        api_service.add_new_account(id, email)
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
def delete_account():
    print(request.json)
    json = request.json
    email = json['email']
    try:
        api_service.delete_account(email)
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