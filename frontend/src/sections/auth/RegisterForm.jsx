// import React from "react";
import React, { useState } from "react";
import * as Yup from 'yup';
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import FormProvider from "../../components/hook-form/FormProvider";
import { Alert, Button, IconButton, InputAdornment, Stack } from "@mui/material";
import { RHFTextField } from "../../components/hook-form";
import { Eye, EyeSlash } from "phosphor-react";

const RegisterForm = () => {
  const [showPassword, setShowPassword] = useState(false);

  // @hookform/resolver - can set min/max length for password
  const RegisterSchema = Yup.object().shape({
    // firstName: Yup.string().required("First Name is required"),
    // lastName: Yup.string().required("Last Name is required"),
    phoneNumber: Yup.number().required("Phone number is required."),
    email: Yup.string()
      .required("Email is required")
      .email("Email must be a valid email address"),
    password: Yup.string().required("Password is required"),
    confirm_password: Yup.string().required("Please, rewrite the password for validation"),
  });

  const defaultValues = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirm_password:"",
  };

  const methods = useForm({
    resolver: yupResolver(RegisterSchema),
    defaultValues,
  });

  const {
    reset,
    setError,
    handleSubmit,
    formState: { errors, isSubmitting, isSubmitSuccessful },
  } = methods;

  const onSubmit = async (data) => {
    try {
      // submit data to backend
    } catch (error) {
      console.log(error);
      reset();
      setError("afterSubmit", {
        ...error,
        message: error.message,
      });
    }
  };
  return (
    <FormProvider methods={methods} onSubmit={handleSubmit(onSubmit)}>
      <Stack spacing={3}>
        {!!errors.afterSubmit && (
          <Alert severity="error">{errors.afterSubmit.message}</Alert>
        )}
        {/* <Stack direction={{ xs: "column", sm: "row" }} spacing={2}>
          <RHFTextField name="firstName" label="First Name" />
          <RHFTextField name="lastName" label="Last Name" />
        </Stack> */}
        <RHFTextField name="email" label="Email Address" />
        {/* <RHFTextField name="password" label="Password" type={showPassword ? "text" : "password"}
          InputProps={{
            endAdornment: (
              <InputAdornment>
                <IconButton
                  onClick={() => {
                    setShowPassword(!showPassword);
                  }}
                >
                  {showPassword ? <Eye /> : <EyeSlash />}
                </IconButton>
              </InputAdornment>
            ),
          }} />
        <RHFTextField name="confirm_password" label="Confirm your Password" type={showPassword ? "text" : "password"}
          InputProps={{
            endAdornment: (
              <InputAdornment>
                <IconButton
                  onClick={() => {
                    setShowPassword(!showPassword);
                  }}
                >
                  {showPassword ? <Eye /> : <EyeSlash />}
                </IconButton>
              </InputAdornment>
            ),
          }}/> */}

        <Button
                fullWidth
                color="inherit"
                size="large"
                type="submit"
                variant="contained"
                
                sx={{
                bgcolor: "text.primary",
                color: "common.white",
                "&hover": { bgcolor: "text.primary", color: "common.white" },
                }}
            >
                Create Account

            </Button>
        
      </Stack>

    </FormProvider>
  );
};

export default RegisterForm;
