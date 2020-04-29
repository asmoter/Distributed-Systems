package gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: event.proto")
public final class EventSubscriptionGrpc {

  private EventSubscriptionGrpc() {}

  public static final String SERVICE_NAME = "tutorial.EventSubscription";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gen.EventRequest,
      gen.Event> getSubscribeOnEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribeOnEvent",
      requestType = gen.EventRequest.class,
      responseType = gen.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<gen.EventRequest,
      gen.Event> getSubscribeOnEventMethod() {
    io.grpc.MethodDescriptor<gen.EventRequest, gen.Event> getSubscribeOnEventMethod;
    if ((getSubscribeOnEventMethod = EventSubscriptionGrpc.getSubscribeOnEventMethod) == null) {
      synchronized (EventSubscriptionGrpc.class) {
        if ((getSubscribeOnEventMethod = EventSubscriptionGrpc.getSubscribeOnEventMethod) == null) {
          EventSubscriptionGrpc.getSubscribeOnEventMethod = getSubscribeOnEventMethod = 
              io.grpc.MethodDescriptor.<gen.EventRequest, gen.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "tutorial.EventSubscription", "subscribeOnEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.EventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Event.getDefaultInstance()))
                  .setSchemaDescriptor(new EventSubscriptionMethodDescriptorSupplier("subscribeOnEvent"))
                  .build();
          }
        }
     }
     return getSubscribeOnEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventSubscriptionStub newStub(io.grpc.Channel channel) {
    return new EventSubscriptionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventSubscriptionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EventSubscriptionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventSubscriptionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EventSubscriptionFutureStub(channel);
  }

  /**
   */
  public static abstract class EventSubscriptionImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribeOnEvent(gen.EventRequest request,
        io.grpc.stub.StreamObserver<gen.Event> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeOnEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeOnEventMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                gen.EventRequest,
                gen.Event>(
                  this, METHODID_SUBSCRIBE_ON_EVENT)))
          .build();
    }
  }

  /**
   */
  public static final class EventSubscriptionStub extends io.grpc.stub.AbstractStub<EventSubscriptionStub> {
    private EventSubscriptionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventSubscriptionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventSubscriptionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventSubscriptionStub(channel, callOptions);
    }

    /**
     */
    public void subscribeOnEvent(gen.EventRequest request,
        io.grpc.stub.StreamObserver<gen.Event> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeOnEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EventSubscriptionBlockingStub extends io.grpc.stub.AbstractStub<EventSubscriptionBlockingStub> {
    private EventSubscriptionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventSubscriptionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventSubscriptionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventSubscriptionBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<gen.Event> subscribeOnEvent(
        gen.EventRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeOnEventMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EventSubscriptionFutureStub extends io.grpc.stub.AbstractStub<EventSubscriptionFutureStub> {
    private EventSubscriptionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventSubscriptionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventSubscriptionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventSubscriptionFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_ON_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EventSubscriptionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EventSubscriptionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE_ON_EVENT:
          serviceImpl.subscribeOnEvent((gen.EventRequest) request,
              (io.grpc.stub.StreamObserver<gen.Event>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EventSubscriptionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventSubscriptionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gen.EventProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventSubscription");
    }
  }

  private static final class EventSubscriptionFileDescriptorSupplier
      extends EventSubscriptionBaseDescriptorSupplier {
    EventSubscriptionFileDescriptorSupplier() {}
  }

  private static final class EventSubscriptionMethodDescriptorSupplier
      extends EventSubscriptionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EventSubscriptionMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EventSubscriptionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventSubscriptionFileDescriptorSupplier())
              .addMethod(getSubscribeOnEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
