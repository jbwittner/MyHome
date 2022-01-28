/* tslint:disable */
/* eslint-disable */
/**
 * Swagger for MyHome
 * This is openapi specification for MyHome application.
 *
 * The version of the OpenAPI document: 0.1.0
 * Contact: jeanbaptiste.wittner@outlook.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import { Configuration } from './configuration';
import globalAxios, { AxiosPromise, AxiosInstance, AxiosRequestConfig } from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import { DUMMY_BASE_URL, assertParamExists, setApiKeyToObject, setBasicAuthToObject, setBearerAuthToObject, setOAuthToObject, setSearchParams, serializeDataIfNeeded, toPathString, createRequestFunction } from './common';
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from './base';

/**
 * 
 * @export
 * @interface CollectionDTO
 */
export interface CollectionDTO {
    /**
     * 
     * @type {number}
     * @memberof CollectionDTO
     */
    'collectionId': number;
    /**
     * 
     * @type {string}
     * @memberof CollectionDTO
     */
    'collectionName': string;
    /**
     * 
     * @type {Array<CollectionPermissionDTO>}
     * @memberof CollectionDTO
     */
    'permissions': Array<CollectionPermissionDTO>;
}
/**
 * 
 * @export
 * @interface CollectionParameter
 */
export interface CollectionParameter {
    /**
     * 
     * @type {string}
     * @memberof CollectionParameter
     */
    'collectionName': string;
    /**
     * 
     * @type {Array<CollectionPermissionParameter>}
     * @memberof CollectionParameter
     */
    'permissions'?: Array<CollectionPermissionParameter>;
}
/**
 * 
 * @export
 * @interface CollectionPermissionDTO
 */
export interface CollectionPermissionDTO {
    /**
     * 
     * @type {number}
     * @memberof CollectionPermissionDTO
     */
    'collectionPermissionId': number;
    /**
     * 
     * @type {UserDTO}
     * @memberof CollectionPermissionDTO
     */
    'userDTO': UserDTO;
    /**
     * 
     * @type {CollectionPermissionEnum}
     * @memberof CollectionPermissionDTO
     */
    'permission': CollectionPermissionEnum;
}
/**
 * 
 * @export
 * @enum {string}
 */

export enum CollectionPermissionEnum {
    Admin = 'ADMIN',
    ReadWrite = 'READ_WRITE',
    Read = 'READ'
}

/**
 * 
 * @export
 * @interface CollectionPermissionParameter
 */
export interface CollectionPermissionParameter {
    /**
     * 
     * @type {string}
     * @memberof CollectionPermissionParameter
     */
    'userName': string;
    /**
     * 
     * @type {CollectionPermissionEnum}
     * @memberof CollectionPermissionParameter
     */
    'permission': CollectionPermissionEnum;
}
/**
 * 
 * @export
 * @interface CollectionSumarryDTO
 */
export interface CollectionSumarryDTO {
    /**
     * 
     * @type {number}
     * @memberof CollectionSumarryDTO
     */
    'collectionId': number;
    /**
     * 
     * @type {string}
     * @memberof CollectionSumarryDTO
     */
    'collectionName': string;
    /**
     * 
     * @type {CollectionPermissionEnum}
     * @memberof CollectionSumarryDTO
     */
    'permission': CollectionPermissionEnum;
}
/**
 * 
 * @export
 * @interface ExceptionDTO
 */
export interface ExceptionDTO {
    /**
     * 
     * @type {string}
     * @memberof ExceptionDTO
     */
    'details': string;
    /**
     * 
     * @type {string}
     * @memberof ExceptionDTO
     */
    'exception': string;
    /**
     * 
     * @type {string}
     * @memberof ExceptionDTO
     */
    'message': string;
    /**
     * 
     * @type {string}
     * @memberof ExceptionDTO
     */
    'timestamp': string;
}
/**
 * 
 * @export
 * @interface LoginParameter
 */
export interface LoginParameter {
    /**
     * 
     * @type {string}
     * @memberof LoginParameter
     */
    'username': string;
    /**
     * 
     * @type {string}
     * @memberof LoginParameter
     */
    'password': string;
    /**
     * 
     * @type {boolean}
     * @memberof LoginParameter
     */
    'rememberMe': boolean;
}
/**
 * 
 * @export
 * @interface TokenDTO
 */
export interface TokenDTO {
    /**
     * 
     * @type {string}
     * @memberof TokenDTO
     */
    'jwt': string;
    /**
     * 
     * @type {number}
     * @memberof TokenDTO
     */
    'duration': number;
    /**
     * 
     * @type {TokenTypeEnum}
     * @memberof TokenDTO
     */
    'type': TokenTypeEnum;
}
/**
 * 
 * @export
 * @enum {string}
 */

export enum TokenTypeEnum {
    AccessToken = 'ACCESS_TOKEN',
    RememberMeToken = 'REMEMBER_ME_TOKEN'
}

/**
 * 
 * @export
 * @interface UserDTO
 */
export interface UserDTO {
    /**
     * 
     * @type {number}
     * @memberof UserDTO
     */
    'userId': number;
    /**
     * 
     * @type {string}
     * @memberof UserDTO
     */
    'username': string;
    /**
     * 
     * @type {string}
     * @memberof UserDTO
     */
    'email': string;
    /**
     * 
     * @type {string}
     * @memberof UserDTO
     */
    'firstName': string;
    /**
     * 
     * @type {string}
     * @memberof UserDTO
     */
    'lastName': string;
    /**
     * 
     * @type {Array<UserRoleEnum>}
     * @memberof UserDTO
     */
    'roles': Array<UserRoleEnum>;
}
/**
 * 
 * @export
 * @interface UserRegistrationParameter
 */
export interface UserRegistrationParameter {
    /**
     * 
     * @type {string}
     * @memberof UserRegistrationParameter
     */
    'username': string;
    /**
     * 
     * @type {string}
     * @memberof UserRegistrationParameter
     */
    'email': string;
    /**
     * 
     * @type {string}
     * @memberof UserRegistrationParameter
     */
    'firstName': string;
    /**
     * 
     * @type {string}
     * @memberof UserRegistrationParameter
     */
    'lastName': string;
    /**
     * 
     * @type {string}
     * @memberof UserRegistrationParameter
     */
    'password': string;
}
/**
 * 
 * @export
 * @enum {string}
 */

export enum UserRoleEnum {
    Admin = 'ADMIN',
    User = 'USER'
}


/**
 * AuthenticationApi - axios parameter creator
 * @export
 */
export const AuthenticationApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary Check if the user are connected
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        connectionTest: async (options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/authentication/connectionTest`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication JwtAuth required
            // http bearer authentication required
            await setBearerAuthToObject(localVarHeaderParameter, configuration)


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Login
         * @param {LoginParameter} [loginParameter] Object that need to be authenticated
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        login: async (loginParameter?: LoginParameter, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/authentication/login`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(loginParameter, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Logout
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        logout: async (options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/authentication/logout`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Refresh access token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        refreshAccessToken: async (options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/authentication/refreshAccessToken`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication JwtAuth required
            // http bearer authentication required
            await setBearerAuthToObject(localVarHeaderParameter, configuration)


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Register a new user account
         * @param {UserRegistrationParameter} [userRegistrationParameter] Object that needs to register a new user
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        registration: async (userRegistrationParameter?: UserRegistrationParameter, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/authentication/registration`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'PUT', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(userRegistrationParameter, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * AuthenticationApi - functional programming interface
 * @export
 */
export const AuthenticationApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = AuthenticationApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @summary Check if the user are connected
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async connectionTest(options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.connectionTest(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Login
         * @param {LoginParameter} [loginParameter] Object that need to be authenticated
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async login(loginParameter?: LoginParameter, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.login(loginParameter, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Logout
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async logout(options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.logout(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Refresh access token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async refreshAccessToken(options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.refreshAccessToken(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Register a new user account
         * @param {UserRegistrationParameter} [userRegistrationParameter] Object that needs to register a new user
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async registration(userRegistrationParameter?: UserRegistrationParameter, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<void>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.registration(userRegistrationParameter, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * AuthenticationApi - factory interface
 * @export
 */
export const AuthenticationApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = AuthenticationApiFp(configuration)
    return {
        /**
         * 
         * @summary Check if the user are connected
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        connectionTest(options?: any): AxiosPromise<void> {
            return localVarFp.connectionTest(options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Login
         * @param {LoginParameter} [loginParameter] Object that need to be authenticated
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        login(loginParameter?: LoginParameter, options?: any): AxiosPromise<void> {
            return localVarFp.login(loginParameter, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Logout
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        logout(options?: any): AxiosPromise<void> {
            return localVarFp.logout(options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Refresh access token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        refreshAccessToken(options?: any): AxiosPromise<void> {
            return localVarFp.refreshAccessToken(options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Register a new user account
         * @param {UserRegistrationParameter} [userRegistrationParameter] Object that needs to register a new user
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        registration(userRegistrationParameter?: UserRegistrationParameter, options?: any): AxiosPromise<void> {
            return localVarFp.registration(userRegistrationParameter, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * AuthenticationApi - object-oriented interface
 * @export
 * @class AuthenticationApi
 * @extends {BaseAPI}
 */
export class AuthenticationApi extends BaseAPI {
    /**
     * 
     * @summary Check if the user are connected
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthenticationApi
     */
    public connectionTest(options?: AxiosRequestConfig) {
        return AuthenticationApiFp(this.configuration).connectionTest(options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Login
     * @param {LoginParameter} [loginParameter] Object that need to be authenticated
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthenticationApi
     */
    public login(loginParameter?: LoginParameter, options?: AxiosRequestConfig) {
        return AuthenticationApiFp(this.configuration).login(loginParameter, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Logout
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthenticationApi
     */
    public logout(options?: AxiosRequestConfig) {
        return AuthenticationApiFp(this.configuration).logout(options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Refresh access token
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthenticationApi
     */
    public refreshAccessToken(options?: AxiosRequestConfig) {
        return AuthenticationApiFp(this.configuration).refreshAccessToken(options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Register a new user account
     * @param {UserRegistrationParameter} [userRegistrationParameter] Object that needs to register a new user
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthenticationApi
     */
    public registration(userRegistrationParameter?: UserRegistrationParameter, options?: AxiosRequestConfig) {
        return AuthenticationApiFp(this.configuration).registration(userRegistrationParameter, options).then((request) => request(this.axios, this.basePath));
    }
}


/**
 * CollectionApi - axios parameter creator
 * @export
 */
export const CollectionApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary Create Collection
         * @param {CollectionParameter} [collectionParameter] Object that need to create a collection
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createCollection: async (collectionParameter?: CollectionParameter, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/collection`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(collectionParameter, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Get all user collection
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getCollections: async (options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            const localVarPath = `/collection`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * CollectionApi - functional programming interface
 * @export
 */
export const CollectionApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = CollectionApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @summary Create Collection
         * @param {CollectionParameter} [collectionParameter] Object that need to create a collection
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async createCollection(collectionParameter?: CollectionParameter, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<CollectionDTO>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.createCollection(collectionParameter, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Get all user collection
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getCollections(options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<Array<CollectionSumarryDTO>>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getCollections(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * CollectionApi - factory interface
 * @export
 */
export const CollectionApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = CollectionApiFp(configuration)
    return {
        /**
         * 
         * @summary Create Collection
         * @param {CollectionParameter} [collectionParameter] Object that need to create a collection
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createCollection(collectionParameter?: CollectionParameter, options?: any): AxiosPromise<CollectionDTO> {
            return localVarFp.createCollection(collectionParameter, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Get all user collection
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getCollections(options?: any): AxiosPromise<Array<CollectionSumarryDTO>> {
            return localVarFp.getCollections(options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * CollectionApi - object-oriented interface
 * @export
 * @class CollectionApi
 * @extends {BaseAPI}
 */
export class CollectionApi extends BaseAPI {
    /**
     * 
     * @summary Create Collection
     * @param {CollectionParameter} [collectionParameter] Object that need to create a collection
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof CollectionApi
     */
    public createCollection(collectionParameter?: CollectionParameter, options?: AxiosRequestConfig) {
        return CollectionApiFp(this.configuration).createCollection(collectionParameter, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Get all user collection
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof CollectionApi
     */
    public getCollections(options?: AxiosRequestConfig) {
        return CollectionApiFp(this.configuration).getCollections(options).then((request) => request(this.axios, this.basePath));
    }
}


