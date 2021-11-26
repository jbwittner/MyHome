import { PersonAddAltRounded } from '@mui/icons-material';
import { Avatar, Button, Checkbox, FormControlLabel, TextField, Typography } from '@mui/material';
import { Box } from '@mui/system';
import React from 'react';

export const RegistrationPage = () => {

    return (<Box
        sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center'
        }}
    >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <PersonAddAltRounded />
        </Avatar>
        <Typography component="h1" variant="h5">
            Registration
        </Typography>
        <Box sx={{ mt: 1, maxWidth: '500px' }}>
            <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
            />
            <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
            />
            <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
                Sign In
            </Button>
        </Box>
    </Box>)
}