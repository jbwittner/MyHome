import { createTheme } from '@mui/material';

const fontType = [
    '-apple-system',
    'BlinkMacSystemFont',
    '"Segoe UI"',
    'Roboto',
    '"Helvetica Neue"',
    'Arial',
    'sans-serif',
    '"Apple Color Emoji"',
    '"Segoe UI Emoji"',
    '"Segoe UI Symbol"',
    'Risque'
].join(',');

export const AppTheme = createTheme({
    typography: {
        fontFamily: fontType
    },
    palette: {
        mode: 'light',
        primary: {
            main: '#2d526d'
        },
        secondary: {
            main: '#ec407a'
        }
    },
    shape: {
        borderRadius: 20
    }
});
