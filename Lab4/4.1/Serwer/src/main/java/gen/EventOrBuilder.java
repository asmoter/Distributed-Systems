// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: event.proto

package gen;

public interface EventOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tutorial.Event)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.tutorial.EventType eventType = 2;</code>
   */
  int getEventTypeValue();
  /**
   * <code>.tutorial.EventType eventType = 2;</code>
   */
  gen.EventType getEventType();

  /**
   * <code>string city = 3;</code>
   */
  java.lang.String getCity();
  /**
   * <code>string city = 3;</code>
   */
  com.google.protobuf.ByteString
      getCityBytes();

  /**
   * <code>int32 entranceFee = 4;</code>
   */
  int getEntranceFee();

  /**
   * <code>int32 duration = 5;</code>
   */
  int getDuration();

  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  java.util.List<gen.Person> 
      getOrganizerList();
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  gen.Person getOrganizer(int index);
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  int getOrganizerCount();
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  java.util.List<? extends gen.PersonOrBuilder> 
      getOrganizerOrBuilderList();
  /**
   * <code>repeated .tutorial.Person organizer = 6;</code>
   */
  gen.PersonOrBuilder getOrganizerOrBuilder(
      int index);
}
