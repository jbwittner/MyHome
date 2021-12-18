import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './app/App';
import { RegistrationPage } from './app/page/registration/RegistrationPage';
import { MemoryRouter } from 'react-router-dom';

test('test', () => {
    const {queryByLabelText, getByLabelText} = render(
        <MemoryRouter>
            <RegistrationPage />
        </MemoryRouter>
    );
});
