import axios, { AxiosError } from 'axios';
import { toast } from 'react-toastify';
import { Configuration, ConfigurationParameters, ExceptionDTO, SecurityApi } from '../../../generated';

export const PASSWORD_REGEX: RegExp = /(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$)/;
export const EMAIL_REGEX: RegExp =
    /(^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$)/;

const configurationParmeter: ConfigurationParameters = {
    basePath: '/api'
};

export const API_CONFIGURATION: Configuration = new Configuration(configurationParmeter);

export const showError = (reason: Error | AxiosError): string => {
    let message: string = 'An unexpected error has occurred';

    if (axios.isAxiosError(reason)) {
        if (reason.response !== undefined) {
            if (reason.response.status === 400 && reason.response.data !== undefined) {
                if (reason.response.data.exception !== undefined) {
                    const exceptionDTO: ExceptionDTO = reason.response.data;
                    message = exceptionDTO.message;
                } else {
                    message = ' : Bad Request';
                }
            } else {
                message =
                    'An unexpected error has occurred with error code : ' + reason.response.status;
            }
        }
    } else {
        message += ' : ' + reason.message;
    }

    toast.error(message);
    console.error(message);

    return message;
};

axios.interceptors.response.use(
    (response) => {
        console.log('response');
        console.log(response);
        console.log('response : ' + response.config);
        console.log(response.config);
        return response;
    },
    (error) => {
        const originalRequest = error.config;
        console.log('error 11');
        console.log(error.response.status);
        console.log(originalRequest);
        console.log(originalRequest._retry);
        console.log('error 22');
        if (error.response) {
            if (error.response.status === 403 && !originalRequest._retry) {
                console.log('handling');
                originalRequest._retry = true;
                error.config._retry = true;
                const securityApi = new SecurityApi(API_CONFIGURATION);
                securityApi
                    .refreshAccessToken()
                    .then(() => {
                        console.log('refreshAccessToken');
                        return axios(originalRequest);
                    })
                    .catch(() => {
                        console.log('catch Promise.reject');
                        return Promise.reject(error);
                    });
            } else {
                return Promise.reject(error);
            }
        } else {
            return Promise.reject(error);
        }
        
    }
);