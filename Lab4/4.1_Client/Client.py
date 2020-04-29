import grpc
import sys
import time

# python3 -m grpc_tools.protoc -I. --python_out=gen --grpc_python_out=gen event.proto

from gen import event_pb2, event_pb2_grpc

channel = None
stub = None
city = None
is_connected = False


def initialize():
    global channel, stub, is_connected
    channel = grpc.insecure_channel('localhost:50051')
    stub = event_pb2_grpc.EventStub(channel)
    initialize_city()
    print('Waiting for server...')
    wait_for_connection()
    print('Ready to send meetings\' info!\n')
    subscribe_on()


def initialize_city():
    global city
    while city is None:
        ask_for_valid_city()


def ask_for_valid_city():
    global city
    print(
        """ Choose a city to suscribe:
                0 - Cracow
                1 - Warsaw
                2 - London
                3 - Venice
                4 - Paris""")
    input_city = int(sys.stdin.readline()[:-1])
    if input_city in [0, 1, 2, 3, 4]:
        city = event_pb2.City.Name(input_city)


def subscribe_on():
    global category, is_connected
    subscription_request = event_pb2.EventRequest(city=city)
    try:
        stream = stub.InformAboutMeeting(subscription_request)
        for f in stream:
            print(f)
    except grpc.channel.Rendezvous:
        is_alive = False
        print('Cannot reach server. Begin reconnecting...')
        reconnect()
    except KeyboardInterrupt:
        print('Goodbye!')


def main():
    initialize()


if __name__ == '__main__':
    main()
