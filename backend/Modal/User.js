const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const UserSchema = new Schema ({
    id: {
        type: String,
        register: true,
        index: true,
        unique: true,
    },
    firstName: {
        type: String,
        default: "",
    },
    lastName: {
        
    },
    email: {
        type: String,
        default: "",
    },
    password: {
        type: String,
        default: "",
    }
});

module.exports = mongoose.model("User", UserSchema);