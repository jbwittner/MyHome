import { Avatar, Checkbox, FormControlLabel, TextField, Typography } from '@mui/material';
import LoadingButton from '@mui/lab/LoadingButton';
import { Box } from '@mui/system';
import React from 'react';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { PATH } from '../../router/Router';
import { LoginParameter, SecurityApi } from '../../../generated';
import { API_CONFIGURATION } from '../../api/server/ApiConfig';
import { SubmitHandler, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { TextFieldController } from '../../components/Forms';
import { AxiosError } from 'axios';

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

    const apiRegistration = new SecurityApi(API_CONFIGURATION);

    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        toast('Wow so easy !');
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
                setLoading(false);
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
                <TextFieldController
                    name="userName"
                    control={control}
                    defaultValue=""
                    fullWidth
                    label="User Name*"
                    autoComplete="userName"
                    sx={{ mt: 2 }}
                    error={errors.userName !== undefined}
                />
                <TextFieldController
                    name="password"
                    control={control}
                    defaultValue=""
                    fullWidth
                    type="password"
                    label="Password*"
                    autoComplete="password"
                    sx={{ mt: 2 }}
                    error={errors.password !== undefined}
                />
                <FormControlLabel
                    control={<Checkbox value="remember" color="primary" />}
                    label="Remember me"
                />
                <LoadingButton
                    loading={loading}
                    type="submit"
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
