import {
    Avatar,
    Checkbox,
    FormControlLabel,
    TextField,
    Typography
} from '@mui/material';
import LoadingButton from '@mui/lab/LoadingButton';
import { Box } from '@mui/system';
import React from 'react';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { PATH } from '../../router/Router';
import { LoginParameter, SecurityApi } from '../../../generated';
import { API_CONFIGURATION } from '../../config/ApiConfig';
import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { TestCompo } from '../../components/Forms';

interface IFormInputs {
    userName: string;
    password: string;
}

const schema = yup
    .object({
        userName: yup.string().required(),
        password: yup.string().required()
    })
    .required();

export const LoginPage = () => {
    const [loading, setLoading] = React.useState(false);

    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        toast("Wow so easy !");
        setLoading(true);
        const parameter: LoginParameter = {
            username: data.userName,
            password: data.password
        };
        apiRegistration
            .login(parameter)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            })
            .finally(() => {
                setLoading(true);
            });
    };

    const apiRegistration = new SecurityApi(API_CONFIGURATION);

    const onClicklll = () => {
        const parameter: LoginParameter = {
            username: 'toto',
            password: 'tata'
        };
        apiRegistration
            .login(parameter)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });
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
                <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                Sign in
            </Typography>
            <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 2, width: '500px' }}>
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
                <TestCompo
                    name="userName"
                    control={control}
                    defaultValue=""
                    fullWidth
                    label="User Name*"
                    autoComplete="userName"
                    sx={{ mt: 2 }}
                    error={errors.userName !== undefined}
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
                            autoComplete="password"
                            sx={{ mt: 2 }}
                            error={errors.password !== undefined}
                        />
                    )}
                />
                <FormControlLabel
                    control={<Checkbox value="remember" color="primary" />}
                    label="Remember me"
                />
                <LoadingButton
                    loading={loading}
                    type="submit"
                    onClick={onClicklll}
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Sign In
                </LoadingButton>
                <Link to={PATH.REGISTRATION_PATH}>{"Don't have an account? Sign Up"}</Link>
            </Box>
        </Box>
    );
};
