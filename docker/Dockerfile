FROM debian:stretch

RUN apt-get update && apt-get -y install cmake protobuf-compiler

WORKDIR /app

COPY Makefile .
COPY main .

CMD ["make", "run"]
