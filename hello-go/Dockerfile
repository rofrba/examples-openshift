FROM golang:1.14.4-alpine3.11
RUN mkdir /app
COPY main.go /app
WORKDIR /app
RUN go build -o main .
EXPOSE 8080 8888
USER 1001
ENTRYPOINT ["/app/main"]
