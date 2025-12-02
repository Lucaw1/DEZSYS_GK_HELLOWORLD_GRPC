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

    StringBuilder sb = new StringBuilder("Received DataItems:\n");

    for (Hello.DataItem item : request.getItemsList()) {
        sb.append("ID: ").append(item.getId())
          .append(", Name: ").append(item.getName())
          .append(", Value: ").append(item.getValue())
          .append("\n");
    }

    Hello.DataResponse response = Hello.DataResponse.newBuilder()
            .setMessage(sb.toString())
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}

}