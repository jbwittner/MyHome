import { Avatar, Checkbox, FormControlLabel, Typography } from '@mui/material';
import LoadingButton from '@mui/lab/LoadingButton';
import { Box } from '@mui/system';
import React, { useCallback } from 'react';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { PATH } from '../../router/Router';
import { SubmitHandler, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { Link, useNavigate } from 'react-router-dom';
import { TextFieldController } from '../../components/Forms';
import { useLogin } from '../../api/server/SecurityApiHook';
import { LoginContext } from '../../context/Context';

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
    const navigate = useNavigate();
    const { setIsAuthenticated } = React.useContext(LoginContext);

    const onSuccess = useCallback(() => {
        setIsAuthenticated(true);
        navigate(PATH.HOME_PATH);
    }, [navigate, setIsAuthenticated]);

    const { isLoading, callLogin } = useLogin({ onSuccess: onSuccess });

    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        callLogin({
            username: data.userName,
            password: data.password
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
                    loading={isLoading}
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
