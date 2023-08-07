from typing import Annotated

from fastapi import FastAPI, HTTPException, Header

import config
from service import Service
from model import Req

app = FastAPI()

service = Service()

@app.get("/")
def root():
    return {"Hello": "World"}



@app.post("/client/sync")
def sync(authorization: Annotated[str | None, Header()] = None, request: Req = None):

    token = authorization.split(' ')[1]
    print(token)

    # TODO token 认证
    if config.token != token:
        raise HTTPException(status_code=401, detail="unauthorized")

    print(request)
    
    service.sync_client(request.clients);

    return {
        "code": "0000",
        "msg": "成功"
    }

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)