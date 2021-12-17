import { PersonAddAltRounded } from '@mui/icons-material';
import { Avatar, Button, TextField, Typography } from '@mui/material';
import { Box } from '@mui/system';
import React, { useCallback } from 'react';
import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import { useNavigate } from 'react-router';
import { PATH } from '../../router/Router';
import { UserRegistrationParameter } from '../../../generated';
import LoadingButton from '@mui/lab/LoadingButton';
import { useRegistration } from '../../api/server/SecurityApiHook';
import { toast } from 'react-toastify';

interface IFormInputs {
    firstName: string;
    lastName: string;
    username: string;
    email: string;
    password: string;
}

const schema = yup
    .object({
        firstName: yup.string().required(),
        lastName: yup.string().required(),
        username: yup.string().required(),
        email: yup
            .string()
            .matches(
                /(^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$)/
            ),
        password: yup.string().matches(/(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$)/)
    })
    .required();

export const RegistrationPage = () => {
    const navigate = useNavigate();

    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    const onCancel = useCallback(() => navigate(PATH.LOGIN_PATH), []);

    const onSuccess = useCallback(() => {
        navigate(PATH.LOGIN_PATH);
        toast.success('Registration sucessfull');
    }, [navigate, toast]);

    const { isLoading, callRegistration } = useRegistration({ onSuccess: onSuccess });

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        const userRegistrationParameter: UserRegistrationParameter = {
            username: data.username,
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            password: data.password
        };

        callRegistration(userRegistrationParameter);
    };

    return (
        <Box
            sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center'
            }}
        >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                <PersonAddAltRounded />
            </Avatar>
            <Typography component="h1" variant="h5">
                Registration
            </Typography>
            <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 2, width: '500px' }}>
                <Controller
                    name="firstName"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            fullWidth
                            label="First Name*"
                            autoComplete="given-name"
                            sx={{ mt: 2 }}
                            error={errors.firstName !== undefined}
                        />
                    )}
                />
                <Controller
                    name="lastName"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            fullWidth
                            label="Last Name*"
                            autoComplete="family-name"
                            sx={{ mt: 2 }}
                            error={errors.lastName !== undefined}
                        />
                    )}
                />
                <Controller
                    name="username"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            fullWidth
                            label="User Name*"
                            autoComplete="username"
                            sx={{ mt: 2 }}
                            error={errors.username !== undefined}
                        />
                    )}
                />
                <Controller
                    name="email"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            fullWidth
                            label="Email*"
                            autoComplete="email"
                            sx={{ mt: 2 }}
                            error={errors.email !== undefined}
                        />
                    )}
                />
                <Controller
                    name="password"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            fullWidth
                            label="Password*"
                            autoComplete="current-password"
                            type="password"
                            sx={{ mt: 2 }}
                            error={errors.password !== undefined}
                        />
                    )}
                />
                <LoadingButton
                    loading={isLoading}
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3 }}
                >
                    Registration
                </LoadingButton>
                <Button onClick={onCancel} fullWidth variant="contained" sx={{ mt: 3 }}>
                    Cancel
                </Button>
            </Box>
        </Box>
    );
};
