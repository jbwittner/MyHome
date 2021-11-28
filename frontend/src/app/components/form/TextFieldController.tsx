import { TextField } from '@mui/material';
import React from 'react';
import { Control, Controller, FieldPath, FieldPathValue, FieldValues, UnpackNestedValue } from 'react-hook-form';
import { SxProps, Theme } from '@mui/system';

interface ITextFieldControllerInputs<TFieldValues extends FieldValues = FieldValues, TName extends FieldPath<TFieldValues> = FieldPath<TFieldValues>> {
    name: any;
    control?: Control<TFieldValues>;
    defaultValue?: UnpackNestedValue<FieldPathValue<TFieldValues, TName>>;
    label?: string;
    sx?: SxProps<Theme>
    errors?: boolean,
    fullWidth?: boolean
}

export function TextFieldController(input: ITextFieldControllerInputs) {
    return (
        <Controller
            name={input.name}
            control={input.control}
            defaultValue={input.defaultValue}
            render={({ field }) => (
                <TextField
                    {...field}
                    fullWidth={input.fullWidth}
                    label={input.label}
                    autoComplete={input.name}
                    sx={input.sx}
                    error={input.errors}
                />
            )}
        />
    );
};
