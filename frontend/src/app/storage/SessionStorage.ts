/* eslint-disable no-unused-vars */
export enum SESSION_STORAGE_KEY {
    LOGIN = 'LOGIN'
}
/* eslint-enable no-unused-vars */

export const setSessionStorage = (key: SESSION_STORAGE_KEY, value: any) => {
    sessionStorage.setItem(key, JSON.stringify(value));
};

export const deleteSessionStorage = (key: SESSION_STORAGE_KEY) => {
    sessionStorage.removeItem(key);
};

export const clearSessionStorage = () => {
    sessionStorage.clear();
};

export const getSessionStorage = (key: SESSION_STORAGE_KEY) => {
    const value = sessionStorage.getItem(key);
    var object;
    if (value) {
        object = JSON.parse(value);
        return object;
    }
};
