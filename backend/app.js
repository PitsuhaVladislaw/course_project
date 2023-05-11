require('dotenv').config();

const fs = require("fs");
const express = require("express");
const cors = require("cors");
const helmet = require("helmet");
const logger = require("morgan");
const { ApolloServer, gql } = require('apollo-server-express');
const app = express();

const port = process.env.PORT || 3001;

app.use(express.urlencoded({extended: true}));
app.use(express.json());

const corsOption = {
    origin: "*",
    methods: "GET, HEAD, PUT, PATCH, POST, DELETE",
    credential: true,
    exposeHeaders: ["X-auth-token"],
}

app.listen(port, () => {
    console.log(`Servar work at ${port}`)
});


app.use(cors(corsOption));

const typeDefs = gql(
    fs.readFileSync("./schema.graphql", {encoding: "utf-8"})
);

const resolvers = require("./resolvers"); 

const apolloServer = new ApolloServer({typeDefs, resolvers});
apolloServer.applyMiddleware({app, path: "/grapgql"});
