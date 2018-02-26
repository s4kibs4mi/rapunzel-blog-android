package ninja.sakib.rapunzel.android.services

import io.grpc.ManagedChannelBuilder
import ninja.sakib.rapunzel.android.proto.RapunzelBlogServiceGrpc

/**
 * := Coded with love by Sakib Sami on 23/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

val serviceHost = "192.168.0.194"
val servicePort = 8090

val service: RapunzelBlogServiceGrpc.RapunzelBlogServiceBlockingStub = RapunzelBlogServiceGrpc.newBlockingStub(ManagedChannelBuilder
        .forAddress(serviceHost, servicePort)
        .usePlaintext(true)
        .build())

val callbackService: RapunzelBlogServiceGrpc.RapunzelBlogServiceFutureStub = RapunzelBlogServiceGrpc.newFutureStub(ManagedChannelBuilder
        .forAddress(serviceHost, servicePort)
        .usePlaintext(true)
        .build())

fun getRapunzelBlogService(): RapunzelBlogServiceGrpc.RapunzelBlogServiceBlockingStub {
    return service
}

fun getRapunzelBlogCallbackService(): RapunzelBlogServiceGrpc.RapunzelBlogServiceFutureStub {
    return callbackService
}
