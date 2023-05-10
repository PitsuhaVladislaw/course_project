require('dotenv').config();

const express = require("express");
const cors = require("cors");
const helmet = require("helmet");
const logger = require("morgan");
const app = express();

const post = process.env.PORT || 3001;

app.use(express.urlencoded({extended: true}));
app.use(express.json());

const corsOption = {
    origin: "*",
    methods: "GET, HEAD, PUT, PATCH, POST, DELETE",
    credential: true,
    exposeHeaders: ["X-auth-token"]
}

app.use(cors(corsOption));
app.listen(post, () => {
    console.log(`Servar work at ${post}`)
});