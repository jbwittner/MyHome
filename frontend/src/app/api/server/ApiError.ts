import axios, { AxiosError } from 'axios';
import { toast } from 'react-toastify';
import { ExceptionDTO } from '../../../generated/api';

export const getError = (reason: Error | AxiosError): string => {
    let message: string = 'An unexpected error has occurred : ';

    if(axios.isAxiosError(reason)){
        if (reason.response !== undefined) {
            if (reason.response.status === 400 && reason.response.data !== undefined) {
                if (reason.response.data.exception !== undefined) {
                    const exceptionDTO: ExceptionDTO = reason.response.data;
                    message = exceptionDTO.exception;
                } else {
                    message = 'Bad Request';
                }
            } else {
                message =
                    'An unexpected error has occurred with error code : ' + reason.response.status;
            }
        }
    } else {
        message += reason.message
    }

    return message;
};

export const useDisplayError = () => {
    const displayError = (reason: any) => {
        const message: string = getError(reason);
        toast.error(message);
    };

    return {
        displayError
    };
};
