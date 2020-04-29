// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: event.proto

package io.grpc.examples.routeguide;

/**
 * Protobuf type {@code tutorial.EventRequest}
 */
public  final class EventRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tutorial.EventRequest)
    EventRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EventRequest.newBuilder() to construct.
  private EventRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EventRequest() {
    eventType_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new EventRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EventRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            eventType_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.examples.routeguide.Event.internal_static_tutorial_EventRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.examples.routeguide.Event.internal_static_tutorial_EventRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.examples.routeguide.EventRequest.class, io.grpc.examples.routeguide.EventRequest.Builder.class);
  }

  public static final int EVENTTYPE_FIELD_NUMBER = 1;
  private int eventType_;
  /**
   * <code>.tutorial.Type eventType = 1;</code>
   * @return The enum numeric value on the wire for eventType.
   */
  public int getEventTypeValue() {
    return eventType_;
  }
  /**
   * <code>.tutorial.Type eventType = 1;</code>
   * @return The eventType.
   */
  public io.grpc.examples.routeguide.Type getEventType() {
    @SuppressWarnings("deprecation")
    io.grpc.examples.routeguide.Type result = io.grpc.examples.routeguide.Type.valueOf(eventType_);
    return result == null ? io.grpc.examples.routeguide.Type.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (eventType_ != io.grpc.examples.routeguide.Type.WORK_OFFICIAL.getNumber()) {
      output.writeEnum(1, eventType_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (eventType_ != io.grpc.examples.routeguide.Type.WORK_OFFICIAL.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, eventType_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.grpc.examples.routeguide.EventRequest)) {
      return super.equals(obj);
    }
    io.grpc.examples.routeguide.EventRequest other = (io.grpc.examples.routeguide.EventRequest) obj;

    if (eventType_ != other.eventType_) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + EVENTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + eventType_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.examples.routeguide.EventRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.examples.routeguide.EventRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.examples.routeguide.EventRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.grpc.examples.routeguide.EventRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code tutorial.EventRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tutorial.EventRequest)
      io.grpc.examples.routeguide.EventRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.examples.routeguide.Event.internal_static_tutorial_EventRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.examples.routeguide.Event.internal_static_tutorial_EventRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.examples.routeguide.EventRequest.class, io.grpc.examples.routeguide.EventRequest.Builder.class);
    }

    // Construct using io.grpc.examples.routeguide.EventRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      eventType_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.examples.routeguide.Event.internal_static_tutorial_EventRequest_descriptor;
    }

    @java.lang.Override
    public io.grpc.examples.routeguide.EventRequest getDefaultInstanceForType() {
      return io.grpc.examples.routeguide.EventRequest.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.examples.routeguide.EventRequest build() {
      io.grpc.examples.routeguide.EventRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.examples.routeguide.EventRequest buildPartial() {
      io.grpc.examples.routeguide.EventRequest result = new io.grpc.examples.routeguide.EventRequest(this);
      result.eventType_ = eventType_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.grpc.examples.routeguide.EventRequest) {
        return mergeFrom((io.grpc.examples.routeguide.EventRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.examples.routeguide.EventRequest other) {
      if (other == io.grpc.examples.routeguide.EventRequest.getDefaultInstance()) return this;
      if (other.eventType_ != 0) {
        setEventTypeValue(other.getEventTypeValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.grpc.examples.routeguide.EventRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.examples.routeguide.EventRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int eventType_ = 0;
    /**
     * <code>.tutorial.Type eventType = 1;</code>
     * @return The enum numeric value on the wire for eventType.
     */
    public int getEventTypeValue() {
      return eventType_;
    }
    /**
     * <code>.tutorial.Type eventType = 1;</code>
     * @param value The enum numeric value on the wire for eventType to set.
     * @return This builder for chaining.
     */
    public Builder setEventTypeValue(int value) {
      eventType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.tutorial.Type eventType = 1;</code>
     * @return The eventType.
     */
    public io.grpc.examples.routeguide.Type getEventType() {
      @SuppressWarnings("deprecation")
      io.grpc.examples.routeguide.Type result = io.grpc.examples.routeguide.Type.valueOf(eventType_);
      return result == null ? io.grpc.examples.routeguide.Type.UNRECOGNIZED : result;
    }
    /**
     * <code>.tutorial.Type eventType = 1;</code>
     * @param value The eventType to set.
     * @return This builder for chaining.
     */
    public Builder setEventType(io.grpc.examples.routeguide.Type value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      eventType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.tutorial.Type eventType = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearEventType() {
      
      eventType_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tutorial.EventRequest)
  }

  // @@protoc_insertion_point(class_scope:tutorial.EventRequest)
  private static final io.grpc.examples.routeguide.EventRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.examples.routeguide.EventRequest();
  }

  public static io.grpc.examples.routeguide.EventRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EventRequest>
      PARSER = new com.google.protobuf.AbstractParser<EventRequest>() {
    @java.lang.Override
    public EventRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EventRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EventRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EventRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.examples.routeguide.EventRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

