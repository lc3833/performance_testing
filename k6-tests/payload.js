import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10,
  iterations: 100,
};

export default function () {
  // Small payload test
  let res = http.get('http://localhost:8080/api/users/small');
  check(res, { 
    'REST small status 200': (r) => r.status === 200,
    'REST small size < 1KB': (r) => r.body.length < 1024,
  });

  // Medium payload test
  res = http.get('http://localhost:8080/api/users/medium');
  check(res, { 
    'REST medium status 200': (r) => r.status === 200,
    'REST medium size < 50KB': (r) => r.body.length < 51200,
  });

  // Large payload test
  res = http.get('http://localhost:8080/api/users/large');
  check(res, { 
    'REST large status 200': (r) => r.status === 200,
    'REST large size < 500KB': (r) => r.body.length < 512000,
  });

  sleep(0.1);
}