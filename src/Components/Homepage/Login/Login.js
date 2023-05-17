import React, { useState } from "react";
import "./Login.css";

const UserForm = (props) => {
    
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();

    let jsonData = {
        username: username,
        password: password
    }

    function onChange1(e) {
        setUsername(e.target.value);
    }

    function onChange2(e) {
        setPassword(e.target.value);
    }
    /*
    useEffect(() => {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ user:  username, password: password })
        };
        fetch('http://localhost:5000/auth/login', requestOptions)
            .then(response => response.json());
    }, []);
    */
    function handleSubmit(e) {
        e.preventDefault();
        fetch("http://localhost:8080/users/", {
            method: "post",
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(jsonData),
        }).then(response => response.json());
    }
    return (
        <form className="user_block" onSubmit={handleSubmit}>
            <p>
                <input type="text" placeholder="Username" value={username} onChange={onChange1}/>
                <input type="text" placeholder="Password" value={password} onChange={onChange2} />
            </p>
            <input className="btn_secondary" type="submit" value="Submit" />
        </form>
    );
}

export default UserForm;