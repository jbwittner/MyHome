import { PersonAddAltRounded } from '@mui/icons-material';
import { Avatar, Button, Link, TextField, Typography } from '@mui/material';
import { Box } from '@mui/system';
import React from 'react';
import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import { TextFieldController } from '../../components/form/TextFieldController';

interface IFormInputs {
    firstName: string;
    lastName: string;
    userName: string;
    email: string;
    password: string;
}

const schema = yup
    .object({
        firstName: yup.string().required(),
        lastName: yup.string().required(),
        userName: yup.string().required(),
        email: yup
            .string()
            .matches(
                /(^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$)/
            ),
        password: yup.string().matches(/(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$)/)
    })
    .required();

export const RegistrationPage = () => {
    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    console.log(errors);

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        console.log(data);
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
                            autoComplete="firstname"
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
                            autoComplete="lastName"
                            sx={{ mt: 2 }}
                            error={errors.lastName !== undefined}
                        />
                    )}
                />
                <Controller
                    name="userName"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            fullWidth
                            label="User Name*"
                            autoComplete="userName"
                            sx={{ mt: 2 }}
                            error={errors.userName !== undefined}
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
                <TextFieldController
                    name="password"
                    control={control}
                    defaultValue=""
                    fullWidth
                    label="Password*"
                    autoComplete="password"
                    sx={{ mt: 2 }}
                    error={errors.password !== undefined}
                />
                <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
                    Registration
                </Button>
                <Link href={'/'} variant="body2">
                    {"Don't have an account? Sign Up"}
                </Link>
            </Box>
        </Box>
    );
};
