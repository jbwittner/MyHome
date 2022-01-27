import { useCallback, useState } from 'react';
import { LoginParameter, AuthenticationApi, UserRegistrationParameter } from '../../../generated';
import { API_CONFIGURATION, RequestProps, showError } from './ApiConfig';

const authenticationApi = new AuthenticationApi(API_CONFIGURATION);

export const useLogin = (props: RequestProps<void>) => {
    const [isLoading, setIsLoading] = useState(false);

    const callLogin = useCallback(
        (loginParameter: LoginParameter) => {
            setIsLoading(true);
            authenticationApi
                .login(loginParameter)
                .then((response) => {
                    if (props.onSuccess) {
                        props.onSuccess(response.data);
                    }
                })
                .catch((error) => {
                    showError(error);
                    if (props.onError) {
                        props.onError();
                    }
                })
                .finally(() => {
                    setIsLoading(false);
                });
        },
        [props.onSuccess, props.onError, setIsLoading]
    );

    return { isLoading, callLogin };
};

export const useRegistration = (props: RequestProps<void>) => {
    const [isLoading, setIsLoading] = useState(false);

    const callRegistration = useCallback(
        (userRegistrationParameter: UserRegistrationParameter) => {
            setIsLoading(true);
            authenticationApi
                .registration(userRegistrationParameter)
                .then((response) => {
                    if (props.onSuccess) {
                        props.onSuccess(response.data);
                    }
                })
                .catch((error) => {
                    showError(error);
                    if (props.onError) {
                        props.onError();
                    }
                })
                .finally(() => {
                    setIsLoading(false);
                });
        },
        [props.onSuccess, props.onError, setIsLoading]
    );

    return { isLoading, callRegistration };
};

export const useConnectionTest = (props: RequestProps<void>) => {
    const callConnectionTest = useCallback(() => {
        authenticationApi
            .connectionTest()
            .then((response) => {
                if (props.onSuccess) {
                    props.onSuccess(response.data);
                }
            })
            .catch(() => {
                if (props.onError) {
                    props.onError();
                }
            });
    }, [props.onSuccess, props.onError]);

    return { callConnectionTest };
};

export const useLogout = (props: RequestProps<void>) => {
    const callLogout = useCallback(() => {
        authenticationApi
            .logout()
            .then((response) => {
                if (props.onSuccess) {
                    props.onSuccess(response.data);
                }
            })
            .catch(() => {
                if (props.onError) {
                    props.onError();
                }
            });
    }, [props.onSuccess, props.onError]);

    return { callLogout };
};

export const useRefreshAccessToken = (props: RequestProps<void>) => {
    const callRefreshAccessToken = useCallback(() => {
        authenticationApi
            .refreshAccessToken()
            .then((response) => {
                if (props.onSuccess) {
                    props.onSuccess(response.data);
                }
            })
            .catch(() => {
                if (props.onError) {
                    props.onError();
                }
            });
    }, [props.onSuccess, props.onError]);

    return { callRefreshAccessToken };
};
