import grpc
import hello_pb2
import hello_pb2_grpc

def run():
    # Verbindung zum Java-Server
    channel = grpc.insecure_channel('localhost:50051')
    stub = hello_pb2_grpc.HelloWorldServiceStub(channel)

    items = [
    hello_pb2.DataItem(id=1, name="Item A", value=10.5),
    hello_pb2.DataItem(id=2, name="Item B", value=20.7),
    hello_pb2.DataItem(id=3, name="Item C", value=99.1)
    ]

    record = hello_pb2.DataRecord(items=items)

    response = stub.sendData(record)
    print(response.message)


if __name__ == "__main__":
    run()
