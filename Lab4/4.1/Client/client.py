import grpc
import time
import sys

# python3 -m grpc_tools.protoc -I. --python_out=Client/gen --grpc_python_out=Client/gen event.proto

from grpc._channel import _Rendezvous

from gen import event_pb2
from gen import event_pb2_grpc

SERVER_HOST = 'localhost'
SERVER_PORT = 50051
server_connected = False
event_type = None
channel = None
stub = None


def initialize():
    global channel, stub
    channel = grpc.insecure_channel('localhost:50051')
    stub = event_pb2_grpc.EventSubscriptionStub(channel)


def inititialize_event_type():
    print(""" Choose event type you would like to subscribe on:
    - CONCERT
    - FESTIVAL
    - EXHIBITION
    - PARADE""")
    type_of_event = input()
    return type_of_event


def wait_for_connection():
    global server_connected
    while not server_connected:
        try:
            time.sleep(1)
            stub.connectToServer(event_pb2.Connect())
        except:
            continue
        server_connected = True
        print('Successfully connected to server!\n')


def reconnect_to_server():
    wait_for_connection()
    wait_for_events()


def wait_for_events():
    global server_connected, event_type
    subscription_request = event_pb2.EventRequest(eventType=event_type)
    try:
        stream = stub.subscribeOnEvent(subscription_request)
        for event in stream:
            print("""== Attention! New event! == \n""")
            print(event)
    except grpc._channel._Rendezvous:
        server_connected = False
        print("Failed to reach the server. Trying to reconnect...")
        reconnect_to_server()
    except KeyboardInterrupt:
        print("Bye bye!")


def main():
    global event_type
    initialize()
    event_type = inititialize_event_type()
    print("Waiting for server connection...")
    wait_for_connection()
    wait_for_events()


if __name__ == '__main__':
    main()
