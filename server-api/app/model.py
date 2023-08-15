from pydantic import BaseModel

class Client(BaseModel):
    id: str
    email: str

class Req(BaseModel):
    clients: list[Client] = list()