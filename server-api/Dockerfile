FROM python:3.11

COPY ./requirements.txt /src/requirements.txt
COPY ./config.json /src/config.json


WORKDIR /src

RUN pip install --no-cache-dir --upgrade -r /src/requirements.txt

COPY ./app /src/app

WORKDIR /src/app

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]
