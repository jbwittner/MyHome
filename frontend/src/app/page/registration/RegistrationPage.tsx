import { PersonAddAltRounded } from '@mui/icons-material';
import { Avatar, Button, Typography } from '@mui/material';
import { Box } from '@mui/system';
import React, { useCallback, useEffect } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import { useNavigate } from 'react-router';
import { PATH } from '../../router/Router';
import { UserRegistrationParameter } from '../../../generated';
import LoadingButton from '@mui/lab/LoadingButton';
import { useRegistration } from '../../api/server/AuthenticationApiHook';
import { toast } from 'react-toastify';
import { EMAIL_REGEX, PASSWORD_REGEX } from '../../api/server/ApiConfig';
import { TextFieldController } from '../../components/Forms';

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
        email: yup.string().matches(EMAIL_REGEX),
        password: yup.string().matches(PASSWORD_REGEX)
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

    useEffect(() => {
        if (errors.firstName) {
            toast.error(errors.firstName.message);
        }

        if (errors.lastName) {
            toast.error(errors.lastName.message);
        }

        if (errors.username) {
            toast.error(errors.username.message);
        }

        if (errors.email) {
            toast.error(errors.email.message);
        }

        if (errors.password) {
            toast.error(errors.password.message);
        }
    }, [errors]);

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
                <TextFieldController
                    name="firstName"
                    control={control}
                    defaultValue=""
                    fullWidth
                    required
                    label="First Name"
                    autoComplete="given-name"
                    sx={{ mt: 2 }}
                    error={errors.firstName !== undefined}
                />
                <TextFieldController
                    name="lastName"
                    control={control}
                    defaultValue=""
                    fullWidth
                    required
                    label="Last Name"
                    autoComplete="family-name"
                    sx={{ mt: 2 }}
                    error={errors.lastName !== undefined}
                />
                <TextFieldController
                    name="username"
                    control={control}
                    defaultValue=""
                    fullWidth
                    required
                    label="User Name"
                    autoComplete="username"
                    sx={{ mt: 2 }}
                    error={errors.username !== undefined}
                />
                <TextFieldController
                    name="email"
                    control={control}
                    defaultValue=""
                    fullWidth
                    required
                    label="Email"
                    autoComplete="email"
                    sx={{ mt: 2 }}
                    error={errors.email !== undefined}
                />
                <TextFieldController
                    name="password"
                    control={control}
                    defaultValue=""
                    fullWidth
                    required
                    type="password"
                    label="Password"
                    autoComplete="current-password"
                    sx={{ mt: 2 }}
                    error={errors.password !== undefined}
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
