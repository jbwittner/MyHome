import { TextField, Theme } from '@mui/material';
import { SxProps } from '@mui/system';
import React from 'react';
import { Controller, FieldPath, FieldPathValue, FieldValues, UnpackNestedValue } from 'react-hook-form';

export interface testInput<TFieldValues extends FieldValues = FieldValues, TName extends FieldPath<TFieldValues> = FieldPath<TFieldValues>> {
    control: any
    label?: React.ReactNode
    autoComplete?: string
    error?: boolean
    fullWidth?: boolean
    sx?: SxProps<Theme>
    defaultValue?: UnpackNestedValue<FieldPathValue<TFieldValues, TName>>;
    name: TName;
}

export const TestCompo = (props: testInput) => {
    return (
        <Controller
            name={props.name}
            control={props.control}
            defaultValue={props.defaultValue}
            render={({ field }) => (
                <TextField
                    {...field}
                    fullWidth={props.fullWidth}
                    label={props.label}
                    autoComplete={props.autoComplete}
                    sx={props.sx}
                    error={props.error}
                />
            )}
        />
    );
};
