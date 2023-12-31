import { Link as RouterLink } from "react-router-dom";
import { Link, Stack, Typography } from "@mui/material";
import React from "react";
import LoginForm from "../../sections/auth/LoginForm";

function Login() {
  return (
    <>
      <Stack spacing={2} sx={{ mb: 5, position: "relative" }}>
        <Typography variant="h3">Login</Typography>

        <Stack direction="row" spacing={0.5}>
          <Typography variant="body2">New User?</Typography>

          <Link
            to="/auth/register"
            component={RouterLink}
            variant="subtitle2"
            sx={{
              color: "rgba(119, 151, 227, 1)",
            }}
          >
            Create an account
          </Link>
        </Stack>
        <LoginForm />
      </Stack>
    </>
  );
}

export default Login;
