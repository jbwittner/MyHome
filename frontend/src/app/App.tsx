import { CssBaseline, ThemeProvider } from '@mui/material';
import React, { useState } from 'react';
import { LoginContext } from './context/Context';
import { MainRouter } from './router/Router';
import { AppTheme } from './theme/Theme';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

    return (
        <div className="App">
            <header className="App-header">
                <ThemeProvider theme={AppTheme}>
                    <CssBaseline />
                    <LoginContext.Provider
                        value={{
                            isAuthenticated,
                            setIsAuthenticated
                        }}
                    >
                        <LoginContext.Consumer>{() => <MainRouter />}</LoginContext.Consumer>
                    </LoginContext.Provider>
                </ThemeProvider>
            </header>
        </div>
    );
}

export default App;
