from typing import Annotated, Union

from fastapi import FastAPI, HTTPException, status, Header

import config
from service import Service
from model import Req

app = FastAPI()

service = Service()

@app.get("/")
def root():
    return {"Hello": "World"}



@app.post("/client/sync")
def sync(authorization: Annotated[Union[str, None], Header()] = None, request: Req = None):

    token = authorization.split(' ')[1]
    print(token)

    # TODO token 认证
    if config.token != token:
        raise HTTPException(
                status_code = status.HTTP_401_UNAUTHORIZED,
                detail = "unauthorized"
            )

    print(request)
    
    service.sync_client(request.clients);

    return {
        "code": "0000",
        "msg": "成功"
    }
