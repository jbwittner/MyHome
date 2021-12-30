import {
    Checkbox,
    CheckboxPropsColorOverrides,
    FormControlLabel,
    TextField,
    Theme
} from '@mui/material';
import { SxProps } from '@mui/system';
import { OverridableStringUnion } from '@mui/types';
import React from 'react';
import {
    Controller,
    FieldPath,
    FieldPathValue,
    FieldValues,
    UnpackNestedValue
} from 'react-hook-form';

interface IControllerInput<
    TFieldValues extends FieldValues = FieldValues,
    TName extends FieldPath<TFieldValues> = FieldPath<TFieldValues>
> {
    control: any;
    label?: React.ReactNode;
    error?: boolean;
    sx?: SxProps<Theme>;
    defaultValue?: UnpackNestedValue<FieldPathValue<TFieldValues, TName>>;
    name: TName;
}

export interface ITextFieldControllerInput extends IControllerInput {
    autoComplete?: string;
    fullWidth?: boolean;
    required?: boolean;
    type?: React.InputHTMLAttributes<unknown>['type'];
}

export interface ICheckboxControllerInput extends IControllerInput {
    color?: OverridableStringUnion<
        'primary' | 'secondary' | 'error' | 'info' | 'success' | 'warning' | 'default',
        CheckboxPropsColorOverrides
    >;
    label: string | number | React.ReactElement;
    defaultValue: boolean;
}

export const TextFieldController = (props: ITextFieldControllerInput) => {
    return (
        <Controller
            name={props.name}
            control={props.control}
            defaultValue={props.defaultValue}
            render={({ field }) => (
                <TextField
                    {...field}
                    fullWidth={props.fullWidth}
                    required={props.required}
                    label={props.label}
                    autoComplete={props.autoComplete}
                    type={props.type}
                    sx={props.sx}
                    error={props.error}
                />
            )}
        />
    );
};

export const CheckboxController = (props: ICheckboxControllerInput) => {
    return (
        <Controller
            name={props.name}
            control={props.control}
            defaultValue={props.defaultValue}
            render={({ field }) => (
                <FormControlLabel
                    control={
                        <Checkbox
                            {...field}
                            color={props.color}
                            defaultChecked={props.defaultValue}
                        />
                    }
                    label={props.label}
                />
            )}
        />
    );
};
