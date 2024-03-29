// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: event.proto

package gen;

/**
 * Protobuf type {@code tutorial.Event}
 */
public  final class Event extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tutorial.Event)
    EventOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Event.newBuilder() to construct.
  private Event(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Event() {
    name_ = "";
    eventType_ = 0;
    city_ = "";
    entranceFee_ = 0;
    duration_ = 0;
    organizer_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Event(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            eventType_ = rawValue;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            city_ = s;
            break;
          }
          case 32: {

            entranceFee_ = input.readInt32();
            break;
          }
          case 40: {

            duration_ = input.readInt32();
            break;
          }
          case 50: {
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
              organizer_ = new java.util.ArrayList<gen.Person>();
              mutable_bitField0_ |= 0x00000020;
            }
            organizer_.add(
                input.readMessage(gen.Person.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
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
      if (((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
        organizer_ = java.util.Collections.unmodifiableList(organizer_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return gen.EventProtos.internal_static_tutorial_Event_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return gen.EventProtos.internal_static_tutorial_Event_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            gen.Event.class, gen.Event.Builder.class);
  }

  private int bitField0_;
  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 1;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EVENTTYPE_FIELD_NUMBER = 2;
  private int eventType_;
  /**
   * <code>.tutorial.EventType eventType = 2;</code>
   */
  public int getEventTypeValue() {
    return eventType_;
  }
  /**
   * <code>.tutorial.EventType eventType = 2;</code>
   */
  public gen.EventType getEventType() {
    @SuppressWarnings("deprecation")
    gen.EventType result = gen.EventType.valueOf(eventType_);
    return result == null ? gen.EventType.UNRECOGNIZED : result;
  }

  public static final int CITY_FIELD_NUMBER = 3;
  private volatile java.lang.Object city_;
  /**
   * <code>string city = 3;</code>
   */
  public java.lang.String getCity() {
    java.lang.Object ref = city_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      city_ = s;
      return s;
    }
  }
  /**
   * <code>string city = 3;</code>
   */
  public com.google.protobuf.ByteString
      getCityBytes() {
    java.lang.Object ref = city_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      city_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ENTRANCEFEE_FIELD_NUMBER = 4;
  private int entranceFee_;
  /**
   * <code>int32 entranceFee = 4;</code>
   */
  public int getEntranceFee() {
    return entranceFee_;
  }

  public static final int DURATION_FIELD_NUMBER = 5;
  private int duration_;
  /**
   * <code>int32 duration = 5;</code>
   */
  public int getDuration() {
    return duration_;
  }

  public static final int ORGANIZER_FIELD_NUMBER = 6;
  private java.util.List<gen.Person> organizer_;
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  public java.util.List<gen.Person> getOrganizerList() {
    return organizer_;
  }
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  public java.util.List<? extends gen.PersonOrBuilder> 
      getOrganizerOrBuilderList() {
    return organizer_;
  }
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  public int getOrganizerCount() {
    return organizer_.size();
  }
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  public gen.Person getOrganizer(int index) {
    return organizer_.get(index);
  }
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  public gen.PersonOrBuilder getOrganizerOrBuilder(
      int index) {
    return organizer_.get(index);
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
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    if (eventType_ != gen.EventType.CONCERT.getNumber()) {
      output.writeEnum(2, eventType_);
    }
    if (!getCityBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, city_);
    }
    if (entranceFee_ != 0) {
      output.writeInt32(4, entranceFee_);
    }
    if (duration_ != 0) {
      output.writeInt32(5, duration_);
    }
    for (int i = 0; i < organizer_.size(); i++) {
      output.writeMessage(6, organizer_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    if (eventType_ != gen.EventType.CONCERT.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, eventType_);
    }
    if (!getCityBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, city_);
    }
    if (entranceFee_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, entranceFee_);
    }
    if (duration_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, duration_);
    }
    for (int i = 0; i < organizer_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(6, organizer_.get(i));
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
    if (!(obj instanceof gen.Event)) {
      return super.equals(obj);
    }
    gen.Event other = (gen.Event) obj;

    boolean result = true;
    result = result && getName()
        .equals(other.getName());
    result = result && eventType_ == other.eventType_;
    result = result && getCity()
        .equals(other.getCity());
    result = result && (getEntranceFee()
        == other.getEntranceFee());
    result = result && (getDuration()
        == other.getDuration());
    result = result && getOrganizerList()
        .equals(other.getOrganizerList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + EVENTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + eventType_;
    hash = (37 * hash) + CITY_FIELD_NUMBER;
    hash = (53 * hash) + getCity().hashCode();
    hash = (37 * hash) + ENTRANCEFEE_FIELD_NUMBER;
    hash = (53 * hash) + getEntranceFee();
    hash = (37 * hash) + DURATION_FIELD_NUMBER;
    hash = (53 * hash) + getDuration();
    if (getOrganizerCount() > 0) {
      hash = (37 * hash) + ORGANIZER_FIELD_NUMBER;
      hash = (53 * hash) + getOrganizerList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static gen.Event parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static gen.Event parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static gen.Event parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static gen.Event parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static gen.Event parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static gen.Event parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static gen.Event parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static gen.Event parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static gen.Event parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static gen.Event parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static gen.Event parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static gen.Event parseFrom(
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
  public static Builder newBuilder(gen.Event prototype) {
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
   * Protobuf type {@code tutorial.Event}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tutorial.Event)
      gen.EventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return gen.EventProtos.internal_static_tutorial_Event_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return gen.EventProtos.internal_static_tutorial_Event_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              gen.Event.class, gen.Event.Builder.class);
    }

    // Construct using gen.Event.newBuilder()
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
        getOrganizerFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      name_ = "";

      eventType_ = 0;

      city_ = "";

      entranceFee_ = 0;

      duration_ = 0;

      if (organizerBuilder_ == null) {
        organizer_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000020);
      } else {
        organizerBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return gen.EventProtos.internal_static_tutorial_Event_descriptor;
    }

    @java.lang.Override
    public gen.Event getDefaultInstanceForType() {
      return gen.Event.getDefaultInstance();
    }

    @java.lang.Override
    public gen.Event build() {
      gen.Event result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public gen.Event buildPartial() {
      gen.Event result = new gen.Event(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.name_ = name_;
      result.eventType_ = eventType_;
      result.city_ = city_;
      result.entranceFee_ = entranceFee_;
      result.duration_ = duration_;
      if (organizerBuilder_ == null) {
        if (((bitField0_ & 0x00000020) == 0x00000020)) {
          organizer_ = java.util.Collections.unmodifiableList(organizer_);
          bitField0_ = (bitField0_ & ~0x00000020);
        }
        result.organizer_ = organizer_;
      } else {
        result.organizer_ = organizerBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof gen.Event) {
        return mergeFrom((gen.Event)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(gen.Event other) {
      if (other == gen.Event.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.eventType_ != 0) {
        setEventTypeValue(other.getEventTypeValue());
      }
      if (!other.getCity().isEmpty()) {
        city_ = other.city_;
        onChanged();
      }
      if (other.getEntranceFee() != 0) {
        setEntranceFee(other.getEntranceFee());
      }
      if (other.getDuration() != 0) {
        setDuration(other.getDuration());
      }
      if (organizerBuilder_ == null) {
        if (!other.organizer_.isEmpty()) {
          if (organizer_.isEmpty()) {
            organizer_ = other.organizer_;
            bitField0_ = (bitField0_ & ~0x00000020);
          } else {
            ensureOrganizerIsMutable();
            organizer_.addAll(other.organizer_);
          }
          onChanged();
        }
      } else {
        if (!other.organizer_.isEmpty()) {
          if (organizerBuilder_.isEmpty()) {
            organizerBuilder_.dispose();
            organizerBuilder_ = null;
            organizer_ = other.organizer_;
            bitField0_ = (bitField0_ & ~0x00000020);
            organizerBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOrganizerFieldBuilder() : null;
          } else {
            organizerBuilder_.addAllMessages(other.organizer_);
          }
        }
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
      gen.Event parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (gen.Event) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 1;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 1;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 1;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private int eventType_ = 0;
    /**
     * <code>.tutorial.EventType eventType = 2;</code>
     */
    public int getEventTypeValue() {
      return eventType_;
    }
    /**
     * <code>.tutorial.EventType eventType = 2;</code>
     */
    public Builder setEventTypeValue(int value) {
      eventType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.tutorial.EventType eventType = 2;</code>
     */
    public gen.EventType getEventType() {
      @SuppressWarnings("deprecation")
      gen.EventType result = gen.EventType.valueOf(eventType_);
      return result == null ? gen.EventType.UNRECOGNIZED : result;
    }
    /**
     * <code>.tutorial.EventType eventType = 2;</code>
     */
    public Builder setEventType(gen.EventType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      eventType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.tutorial.EventType eventType = 2;</code>
     */
    public Builder clearEventType() {
      
      eventType_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object city_ = "";
    /**
     * <code>string city = 3;</code>
     */
    public java.lang.String getCity() {
      java.lang.Object ref = city_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        city_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string city = 3;</code>
     */
    public com.google.protobuf.ByteString
        getCityBytes() {
      java.lang.Object ref = city_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        city_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string city = 3;</code>
     */
    public Builder setCity(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      city_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string city = 3;</code>
     */
    public Builder clearCity() {
      
      city_ = getDefaultInstance().getCity();
      onChanged();
      return this;
    }
    /**
     * <code>string city = 3;</code>
     */
    public Builder setCityBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      city_ = value;
      onChanged();
      return this;
    }

    private int entranceFee_ ;
    /**
     * <code>int32 entranceFee = 4;</code>
     */
    public int getEntranceFee() {
      return entranceFee_;
    }
    /**
     * <code>int32 entranceFee = 4;</code>
     */
    public Builder setEntranceFee(int value) {
      
      entranceFee_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 entranceFee = 4;</code>
     */
    public Builder clearEntranceFee() {
      
      entranceFee_ = 0;
      onChanged();
      return this;
    }

    private int duration_ ;
    /**
     * <code>int32 duration = 5;</code>
     */
    public int getDuration() {
      return duration_;
    }
    /**
     * <code>int32 duration = 5;</code>
     */
    public Builder setDuration(int value) {
      
      duration_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 duration = 5;</code>
     */
    public Builder clearDuration() {
      
      duration_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<gen.Person> organizer_ =
      java.util.Collections.emptyList();
    private void ensureOrganizerIsMutable() {
      if (!((bitField0_ & 0x00000020) == 0x00000020)) {
        organizer_ = new java.util.ArrayList<gen.Person>(organizer_);
        bitField0_ |= 0x00000020;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        gen.Person, gen.Person.Builder, gen.PersonOrBuilder> organizerBuilder_;

    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public java.util.List<gen.Person> getOrganizerList() {
      if (organizerBuilder_ == null) {
        return java.util.Collections.unmodifiableList(organizer_);
      } else {
        return organizerBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public int getOrganizerCount() {
      if (organizerBuilder_ == null) {
        return organizer_.size();
      } else {
        return organizerBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public gen.Person getOrganizer(int index) {
      if (organizerBuilder_ == null) {
        return organizer_.get(index);
      } else {
        return organizerBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder setOrganizer(
        int index, gen.Person value) {
      if (organizerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrganizerIsMutable();
        organizer_.set(index, value);
        onChanged();
      } else {
        organizerBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder setOrganizer(
        int index, gen.Person.Builder builderForValue) {
      if (organizerBuilder_ == null) {
        ensureOrganizerIsMutable();
        organizer_.set(index, builderForValue.build());
        onChanged();
      } else {
        organizerBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder addOrganizer(gen.Person value) {
      if (organizerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrganizerIsMutable();
        organizer_.add(value);
        onChanged();
      } else {
        organizerBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder addOrganizer(
        int index, gen.Person value) {
      if (organizerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrganizerIsMutable();
        organizer_.add(index, value);
        onChanged();
      } else {
        organizerBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder addOrganizer(
        gen.Person.Builder builderForValue) {
      if (organizerBuilder_ == null) {
        ensureOrganizerIsMutable();
        organizer_.add(builderForValue.build());
        onChanged();
      } else {
        organizerBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder addOrganizer(
        int index, gen.Person.Builder builderForValue) {
      if (organizerBuilder_ == null) {
        ensureOrganizerIsMutable();
        organizer_.add(index, builderForValue.build());
        onChanged();
      } else {
        organizerBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder addAllOrganizer(
        java.lang.Iterable<? extends gen.Person> values) {
      if (organizerBuilder_ == null) {
        ensureOrganizerIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, organizer_);
        onChanged();
      } else {
        organizerBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder clearOrganizer() {
      if (organizerBuilder_ == null) {
        organizer_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000020);
        onChanged();
      } else {
        organizerBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public Builder removeOrganizer(int index) {
      if (organizerBuilder_ == null) {
        ensureOrganizerIsMutable();
        organizer_.remove(index);
        onChanged();
      } else {
        organizerBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public gen.Person.Builder getOrganizerBuilder(
        int index) {
      return getOrganizerFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public gen.PersonOrBuilder getOrganizerOrBuilder(
        int index) {
      if (organizerBuilder_ == null) {
        return organizer_.get(index);  } else {
        return organizerBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public java.util.List<? extends gen.PersonOrBuilder> 
         getOrganizerOrBuilderList() {
      if (organizerBuilder_ != null) {
        return organizerBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(organizer_);
      }
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public gen.Person.Builder addOrganizerBuilder() {
      return getOrganizerFieldBuilder().addBuilder(
          gen.Person.getDefaultInstance());
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public gen.Person.Builder addOrganizerBuilder(
        int index) {
      return getOrganizerFieldBuilder().addBuilder(
          index, gen.Person.getDefaultInstance());
    }
    /**
     * <code>repeated .tutorial.Person organizer = 6;</code>
     */
    public java.util.List<gen.Person.Builder> 
         getOrganizerBuilderList() {
      return getOrganizerFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        gen.Person, gen.Person.Builder, gen.PersonOrBuilder> 
        getOrganizerFieldBuilder() {
      if (organizerBuilder_ == null) {
        organizerBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            gen.Person, gen.Person.Builder, gen.PersonOrBuilder>(
                organizer_,
                ((bitField0_ & 0x00000020) == 0x00000020),
                getParentForChildren(),
                isClean());
        organizer_ = null;
      }
      return organizerBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tutorial.Event)
  }

  // @@protoc_insertion_point(class_scope:tutorial.Event)
  private static final gen.Event DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new gen.Event();
  }

  public static gen.Event getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Event>
      PARSER = new com.google.protobuf.AbstractParser<Event>() {
    @java.lang.Override
    public Event parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Event(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Event> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Event> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public gen.Event getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

