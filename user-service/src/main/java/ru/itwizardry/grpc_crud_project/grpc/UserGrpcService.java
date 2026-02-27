package ru.itwizardry.grpc_crud_project.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.itwizardry.grpc.user.GetUserRequest;
import ru.itwizardry.grpc.user.UserResponse;
import ru.itwizardry.grpc.user.UserServiceGrpc.UserServiceImplBase;

@GrpcService
public class UserGrpcService extends UserServiceImplBase {

    @Override
    public void getUser(GetUserRequest request,
                        StreamObserver<UserResponse> responseObserver) {

        long id = request.getId();

        if (id <= 0) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("User id must be positive")
                            .asRuntimeException()
            );
            return;
        }

        if (id != 1) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("User not found")
                            .asRuntimeException()
            );
            return;
        }

        UserResponse response = UserResponse.newBuilder()
                .setId(1)
                .setUsername("mikhail")
                .setEmail("mikhail@example.com")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}