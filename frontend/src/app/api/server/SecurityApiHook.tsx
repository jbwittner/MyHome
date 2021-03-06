import { useCallback, useState } from 'react';
import { LoginParameter, SecurityApi, UserRegistrationParameter } from '../../../generated';
import { API_CONFIGURATION, showError } from './ApiConfig';

const securityApi = new SecurityApi(API_CONFIGURATION);

interface RequestProps<T> {
    onSuccess?: (data: T) => void;
    onError?: () => void;
}

export const useLogin = (props: RequestProps<void>) => {
    const [isLoading, setIsLoading] = useState(false);

    const callLogin = useCallback(
        (loginParameter: LoginParameter) => {
            setIsLoading(true);
            securityApi
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
            securityApi
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
        securityApi
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
        securityApi
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
        securityApi
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
