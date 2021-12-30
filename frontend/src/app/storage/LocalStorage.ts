/* eslint-disable no-unused-vars */
export enum LOCAL_STORAGE_KEY {
    EXEMPLE = 'EXEMPLE'
}
/* eslint-enable no-unused-vars */

export const setLocalStorage = (key: LOCAL_STORAGE_KEY, value: any) => {
    localStorage.setItem(key, JSON.stringify(value));
};

export const deleteLocalStorage = (key: LOCAL_STORAGE_KEY) => {
    localStorage.removeItem(key);
};

export const clearLocalStorage = () => {
    localStorage.clear();
};

export const getLocalStorage = (key: LOCAL_STORAGE_KEY) => {
    const value = localStorage.getItem(key);
    var object;
    if (value) {
        object = JSON.parse(value);
        return object;
    }
};
