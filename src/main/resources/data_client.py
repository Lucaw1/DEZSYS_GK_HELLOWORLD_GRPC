import grpc
import hello_pb2
import hello_pb2_grpc

def run():
    # Verbindung zum Java-Server
    channel = grpc.insecure_channel('localhost:50051')
    stub = hello_pb2_grpc.HelloWorldServiceStub(channel)

    # DataRecord erstellen
    record = hello_pb2.DataRecord(
        id=42,
        name="Weather Station B",
        value=18.9
    )

    # RPC aufrufen
    response = stub.sendData(record)

    print("Server response:", response.message)

if __name__ == "__main__":
    run()
