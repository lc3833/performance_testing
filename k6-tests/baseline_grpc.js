import grpc from 'k6/net/grpc';
import { check, sleep } from 'k6';

const client = new grpc.Client();
client.load(['../grpc-service/src/main/proto'], 'user.proto');

export const options = {
  vus: 1,
  iterations: 100,
};

export default function () {
  client.connect('localhost:9090', { plaintext: true });

  let res = client.invoke('user.UserService/GetSmallUser', {});
  check(res, { 'gRPC small status OK': (r) => r.status === grpc.StatusOK });

  res = client.invoke('user.UserService/GetMediumUsers', {});
  check(res, { 'gRPC medium status OK': (r) => r.status === grpc.StatusOK });

  res = client.invoke('user.UserService/GetLargeUsers', {});
  check(res, { 'gRPC large status OK': (r) => r.status === grpc.StatusOK });

  client.close();
  sleep(0.1);
}