import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {

    public static void main(String[] args) {

        String firstname = args.length > 0 ? args[0] : "Max";
        String lastname  = args.length > 1 ? args[1] : "Mustermann";

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(channel);

        Hello.HelloResponse helloResponse = stub.hello(Hello.HelloRequest.newBuilder()
                .setFirstname(firstname)
                .setLastname(lastname)
                .build());
        System.out.println( helloResponse.getText() );

        Hello.DataItem item1 = Hello.DataItem.newBuilder()
        .setId(1)
        .setName("Item A")
        .setValue(10.5)
        .build();

        Hello.DataItem item2 = Hello.DataItem.newBuilder()
        .setId(2)
        .setName("Item B")
        .setValue(20.7)
        .build();

        Hello.DataRecord record = Hello.DataRecord.newBuilder()
        .addItems(item1)
        .addItems(item2)   
        .build();

        Hello.DataResponse response = stub.sendData(record);
        System.out.println(response.getMessage());

        
        channel.shutdown();

    }

}