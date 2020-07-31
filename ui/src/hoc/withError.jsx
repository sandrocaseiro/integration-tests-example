import React, { useEffect, useState } from 'react';
import { Snackbar } from '@material-ui/core';
import { Alert } from '@material-ui/lab';
import http from '../services/http-service';

const withErrorHandler = (WrappedComponent) => {
  return props => {
    const [error, setError] = useState(null);

    useEffect(() => {
      http.interceptors.response.use(res => res, err => {
        console.log(err.response);
        setError(err.response.data.errors[0].description);
        return Promise.reject(err);
      });
    }, []);

    const handleSnackClose = () => setError(null);

    return (
      <React.Fragment>
        <WrappedComponent {...props} />
        <Snackbar open={error != null} autoHideDuration={1500} onClose={handleSnackClose}>
          <Alert severity="error" elevation={6} variant="filled" onClose={handleSnackClose}>
            {error}
          </Alert>
        </Snackbar>
      </React.Fragment>
    );
  }
}

export default withErrorHandler;
