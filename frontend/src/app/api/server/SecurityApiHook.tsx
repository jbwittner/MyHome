import { useCallback, useState } from 'react';
import { LoginParameter, SecurityApi, UserRegistrationParameter } from '../../../generated';
import { API_CONFIGURATION, showError } from './ApiConfig';

const apiTest = new SecurityApi(API_CONFIGURATION);

interface RequestProps<T> {
    onSuccess?: (data: T) => void;
    onError?: () => void;
}

export const useLogin = (props: RequestProps<void>) => {
    const [isLoading, setIsLoading] = useState(false);

    const callLogin = useCallback(
        (loginParameter: LoginParameter) => {
            setIsLoading(true);
            apiTest
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
            apiTest
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
        apiTest
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
