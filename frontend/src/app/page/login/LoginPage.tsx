import {
    Avatar,
    Button,
    Checkbox,
    FormControlLabel,
    TextField,
    Typography
} from '@mui/material';
import { Box } from '@mui/system';
import React from 'react';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { PATH } from '../../router/Router';
import { LoginParameter, SecurityApi } from '../../../generated';
import { API_CONFIGURATION } from '../../config/ApiConfig';
import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { Link } from "react-router-dom";


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

    const {
        control,
        formState: { errors },
        handleSubmit
    } = useForm<IFormInputs>({
        resolver: yupResolver(schema)
    });

    console.log(errors);

    const onSubmit: SubmitHandler<IFormInputs> = (data) => {
        const parameter: LoginParameter = {
            username: data.userName,
            password: data.password
        }
        apiRegistration.login(parameter).then((response) => {
            console.log(response)
        }).catch((error) => {
            console.log(error)
        })
    };

    const apiRegistration = new SecurityApi(API_CONFIGURATION);

    const onClicklll = () => {
        const parameter: LoginParameter = {
            username: 'toto',
            password: 'tata'
        }
        apiRegistration.login(parameter).then((response) => {
            console.log(response)
        }).catch((error) => {
            console.log(error)
        })
    }

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
                <Button type="submit" onClick={onClicklll} fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
                    Sign In
                </Button>
                <Link to={PATH.REGISTRATION_PATH}>
                    {"Don't have an account? Sign Up"}
                </Link>
            </Box>
        </Box>
    );
};
