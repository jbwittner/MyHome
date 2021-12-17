import { Button, Typography } from '@mui/material';
import React, { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { PATH } from '../../router/Router';

export const NotFoundPage = () => {
    const navigate = useNavigate();

    const onClick = useCallback(() => {
        navigate(PATH.HOME_PATH);
    }, [navigate]);

    return (
        <React.Fragment>
            <Typography variant="h1" component="h2">
                Oops 404 not found
            </Typography>
            <Button onClick={onClick}>Back to home</Button>
        </React.Fragment>
    );
};
