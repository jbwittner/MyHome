import { useCallback, useState } from 'react';
import { CollectionApi, CollectionDTO, CollectionParameter } from '../../../generated';
import { API_CONFIGURATION, RequestProps, showError } from './ApiConfig';

const collectionApi = new CollectionApi(API_CONFIGURATION);

collectionApi.createCollection;

export const useCreateCollection = (props: RequestProps<CollectionDTO>) => {
    const [isLoading, setIsLoading] = useState(false);

    const callCreateCollection = useCallback(
        (collectionParameter: CollectionParameter) => {
            setIsLoading(true);
            collectionApi
                .createCollection(collectionParameter)
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

    return { isLoading, callCreateCollection };
};
