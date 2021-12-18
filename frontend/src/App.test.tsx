import React from 'react';
import { render } from '@testing-library/react';
import { RegistrationPage } from './app/page/registration/RegistrationPage';
import { MemoryRouter } from 'react-router-dom';

test('test', () => {
    render(
        <MemoryRouter>
            <RegistrationPage />
        </MemoryRouter>
    );
});
