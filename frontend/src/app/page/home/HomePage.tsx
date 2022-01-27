import { Button } from '@mui/material';
import React, { useCallback, useState } from 'react';
import { CollectionDTO } from '../../../generated';
import { useCreateCollection } from '../../api/server/CollectionApiHook';

export const HomePage = () => {
    const [data, setData] = useState<CollectionDTO>();

    const onSucess = useCallback(
        (data: CollectionDTO) => {
            setData(data);
        },
        [setData]
    );

    const { callCreateCollection } = useCreateCollection({ onSuccess: onSucess });

    return (
        <div>
            Home Page
            <Button
                onClick={() => {
                    callCreateCollection({
                        collectionName: 'toto'
                    });
                }}
            >
                Cliiiick
            </Button>
            <div>data --- : {data}</div>
        </div>
    );
};
