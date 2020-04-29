import grpc
import sys

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
            _ = stub.Ping(event_pb2.PingMsg())
        except:
            continue
        is_alive = True
        print('Server reachable\n')


def reconnect_to_server():
    wait_for_connection()
    subscribe_on_event()


def subscribe_on_event():
    global server_connected, event_type
    subscription_request = event_pb2.EventRequest(eventType=event_type)
    try:
        stream = stub.subscribeOnEvent(subscription_request)
        for event in stream:
            print("\n" + event)
    except grpc._channel._Rendezvous:
        server_connected = False
        print("Failed to reach the server. Trying to reconnect...")
        reconnect_to_server()
    except KeyboardInterrupt:
        print("Bye bye!")


def wait_for_event(request):
    try:
        for event in self.stub.subscribeOnEvent(request):
            print("""Attention! New event!""")
            print(event)

    except _MultiThreadedRendezvous as e:
        print(f"Connection with server is {e.code().name.lower()}")
        print("Exiting")


def get_subscribe_on():
    event_request = event_pb2.EventRequest(
        # subscriber = "asmoter"
        # eventType =
    )
    print("Subscribing on events:" )


def main():
    global event_type
    initialize()
    event_type = inititialize_event_type()
    subscribe_on_event()


if __name__ == '__main__':
    main()
