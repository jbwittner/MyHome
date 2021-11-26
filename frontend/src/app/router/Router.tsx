import React from 'react';
import { Route, Navigate, RouteProps, Routes, BrowserRouter } from 'react-router-dom';
import { LoginContext } from '../context/Context';
import { LoginPage } from '../page/login/LoginPage';
import { RegistrationPage } from '../page/registration/RegistrationPage';

/* eslint-disable no-unused-vars */
export enum PATH {
    LOGIN_PATH = '/',
    REGISTRATION_PATH = '/registration',
    HOME_PATH = '/home'
}

interface PrivateRouteProps extends RouteProps {
    children: JSX.Element;
}

const PrivateRoute = ({ children }: PrivateRouteProps) => {
    const { isAuthenticated } = React.useContext(LoginContext);

    // Show the component only when the user is logged in
    // Otherwise, redirect the user to / page
    return isAuthenticated ? children : <Navigate to={PATH.LOGIN_PATH} />;
};

export function MainRouter() {
    const { isAuthenticated } = React.useContext(LoginContext);

    return (
        <BrowserRouter>
            {isAuthenticated}
            <Routes>
                <Route path={PATH.LOGIN_PATH} element={<LoginPage />} />
                <Route path={PATH.REGISTRATION_PATH} element={<RegistrationPage />} />
                <Route
                    path={PATH.HOME_PATH}
                    element={
                        <PrivateRoute>
                            <Privateaaa />
                        </PrivateRoute>
                    }
                />
            </Routes>
        </BrowserRouter>
    );
}

const Privateaaa = () => <div>private</div>;
