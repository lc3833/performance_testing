import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 100 },
    { duration: '30s', target: 500 },
    { duration: '30s', target: 1000 },
    { duration: '30s', target: 0 },
  ],
};

export default function () {
  let res = http.get('http://localhost:8080/api/users/small');
  check(res, { 'REST small status 200': (r) => r.status === 200 });

  res = http.get('http://localhost:8080/api/users/medium');
  check(res, { 'REST medium status 200': (r) => r.status === 200 });

  res = http.get('http://localhost:8080/api/users/large');
  check(res, { 'REST large status 200': (r) => r.status === 200 });

  sleep(0.1);
}