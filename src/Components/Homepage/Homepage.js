import React from "react";
import "./Homepage.css"

import Logo from "../../assets/icon/logo_hub.ico";
import HomeImage from "../../assets/images/fon_home.jpg";

const Homepage = () => {
    return (
        <div className="home">
            <div className="home_left">
                <img src={HomeImage} alt="fon_home" />
            </div>
            <div className="home_right">
                <img src={Logo} alt="logo hub" />
                <h1>Happening now</h1>
            </div>
        </div>
    )
}

export default Homepage;