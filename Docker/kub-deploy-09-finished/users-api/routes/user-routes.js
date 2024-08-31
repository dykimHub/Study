const express = require("express");

const userActions = require("../controllers/user-actions");

const router = express.Router();

// users-app.js에서 app.use("/users", userRoutes); 설정하면 /users경로를 userRoutes로 보냄

router.post("/signup", userActions.createUser);

router.post("/login", userActions.verifyUser);

router.get("/logs", userActions.getLogs);

module.exports = router;
