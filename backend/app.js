require('dotenv').config();
const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");

const app = express();
const port = process.env.PORT || 3000

const domainsFromEnv = process.env.CORS_DOMAINS || ""

const whitelist = domainsFromEnv.split(",").map(item => item.trim())

const corsOptions = {
  origin: function (origin, callback) {
    if (!origin || whitelist.indexOf(origin) !== -1) {
      callback(null, true)
    } else {
      callback(new Error("Not allowed by CORS"))
    }
  },
  credentials: true,
}
app.use(cors(corsOptions))

app.use(express.urlencoded({extended: true}));
app.use(express.json());


const start = async () => {
    try {
        await mongoose.connect();
        app.listen(port, () => console.log(`Servar work at ${port}`));
    } catch (error) {
        alert(error);
    }
}

start();