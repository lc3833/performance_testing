package com.performance.grpc;

import com.performance.grpc.generated.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getSmallUser(EmptyRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse user = UserResponse.newBuilder()
                .setId(1)
                .setName("John Doe")
                .setEmail("john@example.com")
                .setAge(30)
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void getMediumUsers(EmptyRequest request, StreamObserver<UserListResponse> responseObserver) {
        UserListResponse.Builder listBuilder = UserListResponse.newBuilder();
        for (int i = 0; i < 100; i++) {
            UserResponse user = UserResponse.newBuilder()
                    .setId(i)
                    .setName("User " + i)
                    .setEmail("user" + i + "@example.com")
                    .setAge(20 + i % 40)
                    .build();
            listBuilder.addUsers(user);
        }
        responseObserver.onNext(listBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getLargeUsers(EmptyRequest request, StreamObserver<UserListResponse> responseObserver) {
        UserListResponse.Builder listBuilder = UserListResponse.newBuilder();
        for (int i = 0; i < 1000; i++) {
            UserResponse user = UserResponse.newBuilder()
                    .setId(i)
                    .setName("User " + i)
                    .setEmail("user" + i + "@example.com")
                    .setAge(20 + i % 40)
                    .setAddress("Street " + i)
                    .setPhone("06" + i)
                    .setCompany("Company " + i % 50)
                    .build();
            listBuilder.addUsers(user);
        }
        responseObserver.onNext(listBuilder.build());
        responseObserver.onCompleted();
    }
}