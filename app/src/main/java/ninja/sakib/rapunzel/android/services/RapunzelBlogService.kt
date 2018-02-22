package ninja.sakib.rapunzel.android.services

import io.grpc.ManagedChannelBuilder
import ninja.sakib.rapunzel.android.proto.RapunzelBlogServiceGrpc

/**
 * := Coded with love by Sakib Sami on 23/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

var service: RapunzelBlogServiceGrpc.RapunzelBlogServiceBlockingStub? = null

fun initGRPCConnection() {
    val channel = ManagedChannelBuilder
            .forAddress("103.205.71.51", 8090)
            .usePlaintext(true)
            .build()
    service = RapunzelBlogServiceGrpc.newBlockingStub(channel)
}

fun getRapunzelBlogService(): RapunzelBlogServiceGrpc.RapunzelBlogServiceBlockingStub? {
    return service
}
