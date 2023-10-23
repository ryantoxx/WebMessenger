import { Link as RouterLink } from "react-router-dom";
import { Link, Stack, Typography } from "@mui/material";
import React from "react";
import { CaretLeft } from "phosphor-react";
import ConfirmedForm from "../../sections/auth/ConfirmedForm";

const ConfirmedPage = () => {
  return (
    <>
      <Stack spacing={2} sx={{ mb: 5, position: "relative" }}>
        <Typography variant="h3" paragraph>
          One more step to set your account
        </Typography>

        {/* Confirmed Form */}

        <Link
          component={RouterLink}
          to="/auth/login"
          color="inherit"
          variant="subtitle2"
          sx={{ mt: 3, mx: "auto", alignItems: "center" }}
        >
          <CaretLeft />
          Return to Sign in Page
        </Link>
      </Stack>
    </>
  );
};

export default ConfirmedPage;
