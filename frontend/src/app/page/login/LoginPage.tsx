import { Avatar, Typography } from '@mui/material';
import LoadingButton from '@mui/lab/LoadingButton';
import { Box } from '@mui/system';
import React, { useCallback, useContext, useEffect } from 'react';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { PATH } from '../../router/Router';
import { SubmitHandler, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { Link, useNavigate } from 'react-router-dom';
import { CheckboxController, TextFieldController } from '../../components/Forms';
import {
    useConnectionTest,
    useLogin,
    useLogout,
    useRefreshAccessToken
} from '../../api/server/AuthenticationApiHook';
import { LoginContext } from '../../context/Context';
import {
    clearLocalStorage,
    getLocalStorage,
    LOCAL_STORAGE_KEY,
    setLocalStorage
} from '../../storage/LocalStorage';
import {
    clearSessionStorage,
    getSessionStorage,
    SESSION_STORAGE_KEY,
    setSessionStorage
} from '../../storage/SessionStorage';

interface IFormInputs {
    userName: string;
    password: string;
    rememberMe: boolean;
}

const schema = yup
    .object({
        userName: yup.string().required(),
        password: yup.string().required()
    })
    .required();

export const LoginPage = () => {
    const navigate = useNavigate();
    const { setIsAuthenticated } = useContext(LoginContext);
    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    const { callLogout } = useLogout({});

    const onSuccess = useCallback(() => {
        setIsAuthenticated(true);
        navigate(PATH.HOME_PATH);
    }, [navigate, setIsAuthenticated]);

    const onFailureAutoCall = useCallback(() => {
        callLogout();
        clearLocalStorage();
        clearSessionStorage();
        setIsAuthenticated(false);
    }, [callLogout, setIsAuthenticated]);

    const onFailureLogin = useCallback(() => {
        clearLocalStorage();
        clearSessionStorage();
    }, [callLogout, setIsAuthenticated]);

    const { isLoading, callLogin } = useLogin({ onSuccess: onSuccess, onError: onFailureLogin });

    const { callRefreshAccessToken } = useRefreshAccessToken({
        onSuccess: onSuccess,
        onError: onFailureAutoCall
    });

    const { callConnectionTest } = useConnectionTest({
        onSuccess: onSuccess,
        onError: onFailureAutoCall
    });

    useEffect(() => {
        const isLogin = getSessionStorage(SESSION_STORAGE_KEY.LOGIN);
        const rememberMe = getLocalStorage(LOCAL_STORAGE_KEY.REMEMBER_ME);

        if (isLogin === true) {
            callConnectionTest();
        } else if (rememberMe === true) {
            callRefreshAccessToken();
        }
    }, []);

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        if (data.rememberMe === true) {
            setLocalStorage(LOCAL_STORAGE_KEY.REMEMBER_ME, true);
        }

        setSessionStorage(SESSION_STORAGE_KEY.LOGIN, true);

        callLogin({
            username: data.userName,
            password: data.password,
            rememberMe: data.rememberMe
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
                    label="User Name"
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
                    label="Password"
                    autoComplete="password"
                    sx={{ mt: 2 }}
                    error={errors.password !== undefined}
                />
                <CheckboxController
                    name="rememberMe"
                    control={control}
                    label="Remember me"
                    color="primary"
                    defaultValue={false}
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
