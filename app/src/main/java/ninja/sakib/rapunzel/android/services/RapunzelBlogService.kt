package ninja.sakib.rapunzel.android.services

import io.grpc.ManagedChannelBuilder
import ninja.sakib.rapunzel.android.proto.RapunzelBlogServiceGrpc

/**
 * := Coded with love by Sakib Sami on 23/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

val serviceHost = "127.0.0.1"
val servicePort = 8090

var service = RapunzelBlogServiceGrpc.newBlockingStub(ManagedChannelBuilder
        .forAddress(serviceHost, servicePort)
        .usePlaintext(true)
        .build())

var callbackService = RapunzelBlogServiceGrpc.newFutureStub(ManagedChannelBuilder
        .forAddress(serviceHost, servicePort)
        .usePlaintext(true)
        .build())

fun getRapunzelBlogService(): RapunzelBlogServiceGrpc.RapunzelBlogServiceBlockingStub {
    return service
}

fun getRapunzelBlogCallbackService(): RapunzelBlogServiceGrpc.RapunzelBlogServiceFutureStub {
    return callbackService
}
