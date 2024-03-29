# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: event.proto

from google.protobuf.internal import enum_type_wrapper
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='event.proto',
  package='tutorial',
  syntax='proto3',
  serialized_options=b'\n\003genB\013EventProtosP\001',
  serialized_pb=b'\n\x0b\x65vent.proto\x12\x08tutorial\"6\n\x0c\x45ventRequest\x12&\n\teventType\x18\x01 \x01(\x0e\x32\x13.tutorial.EventType\"\x97\x01\n\x05\x45vent\x12\x0c\n\x04name\x18\x01 \x01(\t\x12&\n\teventType\x18\x02 \x01(\x0e\x32\x13.tutorial.EventType\x12\x0c\n\x04\x63ity\x18\x03 \x01(\t\x12\x13\n\x0b\x65ntranceFee\x18\x04 \x01(\x05\x12\x10\n\x08\x64uration\x18\x05 \x01(\x05\x12#\n\torganizer\x18\x06 \x03(\x0b\x32\x10.tutorial.Person\"B\n\x06Person\x12\x11\n\tfirstName\x18\x01 \x01(\t\x12\x10\n\x08lastName\x18\x02 \x01(\t\x12\x13\n\x0bphoneNumber\x18\x03 \x01(\t\"\t\n\x07\x43onnect*B\n\tEventType\x12\x0b\n\x07\x43ONCERT\x10\x00\x12\x0c\n\x08\x46\x45STIVAL\x10\x01\x12\x0e\n\nEXHIBITION\x10\x02\x12\n\n\x06PARADE\x10\x03\x32\x8b\x01\n\x11\x45ventSubscription\x12=\n\x10subscribeOnEvent\x12\x16.tutorial.EventRequest\x1a\x0f.tutorial.Event0\x01\x12\x37\n\x0f\x63onnectToServer\x12\x11.tutorial.Connect\x1a\x11.tutorial.ConnectB\x14\n\x03genB\x0b\x45ventProtosP\x01\x62\x06proto3'
)

_EVENTTYPE = _descriptor.EnumDescriptor(
  name='EventType',
  full_name='tutorial.EventType',
  filename=None,
  file=DESCRIPTOR,
  values=[
    _descriptor.EnumValueDescriptor(
      name='CONCERT', index=0, number=0,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FESTIVAL', index=1, number=1,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EXHIBITION', index=2, number=2,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PARADE', index=3, number=3,
      serialized_options=None,
      type=None),
  ],
  containing_type=None,
  serialized_options=None,
  serialized_start=314,
  serialized_end=380,
)
_sym_db.RegisterEnumDescriptor(_EVENTTYPE)

EventType = enum_type_wrapper.EnumTypeWrapper(_EVENTTYPE)
CONCERT = 0
FESTIVAL = 1
EXHIBITION = 2
PARADE = 3



_EVENTREQUEST = _descriptor.Descriptor(
  name='EventRequest',
  full_name='tutorial.EventRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='eventType', full_name='tutorial.EventRequest.eventType', index=0,
      number=1, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=25,
  serialized_end=79,
)


_EVENT = _descriptor.Descriptor(
  name='Event',
  full_name='tutorial.Event',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='name', full_name='tutorial.Event.name', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='eventType', full_name='tutorial.Event.eventType', index=1,
      number=2, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='city', full_name='tutorial.Event.city', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='entranceFee', full_name='tutorial.Event.entranceFee', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='duration', full_name='tutorial.Event.duration', index=4,
      number=5, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='organizer', full_name='tutorial.Event.organizer', index=5,
      number=6, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=82,
  serialized_end=233,
)


_PERSON = _descriptor.Descriptor(
  name='Person',
  full_name='tutorial.Person',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='firstName', full_name='tutorial.Person.firstName', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='lastName', full_name='tutorial.Person.lastName', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='phoneNumber', full_name='tutorial.Person.phoneNumber', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=235,
  serialized_end=301,
)


_CONNECT = _descriptor.Descriptor(
  name='Connect',
  full_name='tutorial.Connect',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=303,
  serialized_end=312,
)

_EVENTREQUEST.fields_by_name['eventType'].enum_type = _EVENTTYPE
_EVENT.fields_by_name['eventType'].enum_type = _EVENTTYPE
_EVENT.fields_by_name['organizer'].message_type = _PERSON
DESCRIPTOR.message_types_by_name['EventRequest'] = _EVENTREQUEST
DESCRIPTOR.message_types_by_name['Event'] = _EVENT
DESCRIPTOR.message_types_by_name['Person'] = _PERSON
DESCRIPTOR.message_types_by_name['Connect'] = _CONNECT
DESCRIPTOR.enum_types_by_name['EventType'] = _EVENTTYPE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

EventRequest = _reflection.GeneratedProtocolMessageType('EventRequest', (_message.Message,), {
  'DESCRIPTOR' : _EVENTREQUEST,
  '__module__' : 'event_pb2'
  # @@protoc_insertion_point(class_scope:tutorial.EventRequest)
  })
_sym_db.RegisterMessage(EventRequest)

Event = _reflection.GeneratedProtocolMessageType('Event', (_message.Message,), {
  'DESCRIPTOR' : _EVENT,
  '__module__' : 'event_pb2'
  # @@protoc_insertion_point(class_scope:tutorial.Event)
  })
_sym_db.RegisterMessage(Event)

Person = _reflection.GeneratedProtocolMessageType('Person', (_message.Message,), {
  'DESCRIPTOR' : _PERSON,
  '__module__' : 'event_pb2'
  # @@protoc_insertion_point(class_scope:tutorial.Person)
  })
_sym_db.RegisterMessage(Person)

Connect = _reflection.GeneratedProtocolMessageType('Connect', (_message.Message,), {
  'DESCRIPTOR' : _CONNECT,
  '__module__' : 'event_pb2'
  # @@protoc_insertion_point(class_scope:tutorial.Connect)
  })
_sym_db.RegisterMessage(Connect)


DESCRIPTOR._options = None

_EVENTSUBSCRIPTION = _descriptor.ServiceDescriptor(
  name='EventSubscription',
  full_name='tutorial.EventSubscription',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=383,
  serialized_end=522,
  methods=[
  _descriptor.MethodDescriptor(
    name='subscribeOnEvent',
    full_name='tutorial.EventSubscription.subscribeOnEvent',
    index=0,
    containing_service=None,
    input_type=_EVENTREQUEST,
    output_type=_EVENT,
    serialized_options=None,
  ),
  _descriptor.MethodDescriptor(
    name='connectToServer',
    full_name='tutorial.EventSubscription.connectToServer',
    index=1,
    containing_service=None,
    input_type=_CONNECT,
    output_type=_CONNECT,
    serialized_options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_EVENTSUBSCRIPTION)

DESCRIPTOR.services_by_name['EventSubscription'] = _EVENTSUBSCRIPTION

# @@protoc_insertion_point(module_scope)
