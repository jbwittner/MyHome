import React, { useEffect } from 'react';
import { Route, Navigate, Routes, BrowserRouter as Router, Outlet } from 'react-router-dom';
import { LoginContext } from '../context/Context';
import { NotFoundPage } from '../page/error/NotFoundPage';
import { HomePage } from '../page/home/HomePage';
import { LoginPage } from '../page/login/LoginPage';
import { RegistrationPage } from '../page/registration/RegistrationPage';
import { clearLocalStorage } from '../storage/LocalStorage';

/* eslint-disable no-unused-vars */
export enum PATH {
    LOGIN_PATH = '/',
    REGISTRATION_PATH = '/registration',
    HOME_PATH = '/home'
}

const PrivateOutlet = () => {
    const { isAuthenticated } = React.useContext(LoginContext);

    // Show the component only when the user is logged in
    // Otherwise, redirect the user to / page
    return isAuthenticated ? <Outlet /> : <Navigate to={PATH.LOGIN_PATH} />;
};

export function MainRouter() {
    return (
        <Router>
            <Routes>
                <Route index element={<LoginPage />} />
                <Route path={PATH.REGISTRATION_PATH} element={<RegistrationPage />} />
                <Route path="/" element={<PrivateOutlet />}>
                    <Route path={PATH.HOME_PATH} element={<HomePage />} />
                </Route>
                <Route path="*" element={<NotFoundPage />} />
            </Routes>
        </Router>
    );
}
