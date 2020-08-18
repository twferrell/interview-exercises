import { useState, useEffect } from 'react';
import axios from 'axios';

export function useAsyncEndpoint(fn) {
  // Track state changes for request object
  const [req, setReq] = useState();

  // Track state changes for the response object
  const [res, setRes] = useState({
    data: null,
    complete: false,
    pending: false,
    error: false,
  });

  // Setup a useEffect hook to watch a request object for changes
  // When the state of the request object changes, axios will fire off
  // a post to the server for us.
  useEffect(() => {
    if (!req) return;

    setRes({
      data: null,
      pending: true,
      error: false,
      complete: false,
    });

    axios(req)
      .then((res) =>
        setRes({
          data: res.data,
          pending: false,
          error: false,
          complete: true,
        })
      )
      .catch(() =>
        setRes({
          data: null,
          pending: false,
          error: true,
          complete: true,
        })
      );
  }, [req]);

  return [res, (...args) => setReq(fn(...args))];
}
