const nanoid = require("nanoid");
const User = require("./models/User");

const register = async (_, input) => {

    const {firstName, lastName, email, password} = input;
    if(firstName || lastName || email || password) {
        return "Field's require!";
    }

    try {
        const user = new User({
            id: nanoid(),
            firstName,
            lastName,
            email,
            password
        });

        await user.save();
        return "User registered!";
    } catch(Error) {
        return "Error is found";
    }
}

module.exports = {
    Query: {},
    Mutation: {
        register
    }
};