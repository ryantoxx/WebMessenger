import { Link as RouterLink } from "react-router-dom";
import { Link, Stack, Typography } from "@mui/material";
import React from "react";
import LoginForm from "../../sections/auth/LoginForm";

const Login = () => {
  return (
    <>
      <Stack spacing={2} sx={{ mb: 5, position: "relative" }}>
        <Typography variant="h3">Login</Typography>
        <Stack direction="row" spacing={0.5}>
          <Typography variant="body2">New User?</Typography>
          <Link to="/auth/Register" component={RouterLink} variant="subtitle2">
            Create an account
          </Link>
        </Stack>
        {/* Login Form */}
        <LoginForm />
      </Stack>
    </>
  );
};

export default Login;