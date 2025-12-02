import io.grpc.stub.StreamObserver;

public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void hello( Hello.HelloRequest request, StreamObserver<Hello.HelloResponse> responseObserver) {

        System.out.println("Handling hello endpoint: " + request.toString());

        String text = "Hello World, " + request.getFirstname() + " " + request.getLastname();
        Hello.HelloResponse response = Hello.HelloResponse.newBuilder().setText(text).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void sendData(Hello.DataRecord request, StreamObserver<Hello.DataResponse> responseObserver) {

    System.out.println("Received DataRecord: " + request);

    String msg = "Received DataRecord with ID=" + request.getId() +
            ", Name=" + request.getName() +
            ", Value=" + request.getValue();

    Hello.DataResponse response = Hello.DataResponse.newBuilder()
            .setMessage(msg)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}
}