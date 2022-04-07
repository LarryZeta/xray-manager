from functools import wraps
from flask import request
import shim_config


def permission(func):
    @wraps(func)
    def inner():
        access_key = request.json['access_key']
        secret_key = request.json['secret_key']
        if access_key != shim_config.access_key or secret_key != shim_config.secret_key:
            return  {
            'code': '8000',
            'msg': 'AUTH_FAIL'
            }, 401
        return func()
    return inner
