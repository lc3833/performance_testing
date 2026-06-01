import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 1,
  iterations: 100,
};

export default function () {
  // Small payload
  let res = http.get('http://localhost:8080/api/users/small');
  check(res, { 'REST small status 200': (r) => r.status === 200 });

  // Medium payload
  res = http.get('http://localhost:8080/api/users/medium');
  check(res, { 'REST medium status 200': (r) => r.status === 200 });

  // Large payload
  res = http.get('http://localhost:8080/api/users/large');
  check(res, { 'REST large status 200': (r) => r.status === 200 });

  sleep(0.1);
}