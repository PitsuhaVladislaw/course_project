import React from "react";
import "./Homepage.css";

import Logo from "../../assets/icon/logo_hub.ico";
import HomeImage from "../../assets/images/fon_home.jpg";
import UserForm from "./Login/Login";

const Homepage = () => {
    return (
        <div className="homepage">
            <div className="homepage_top">
                <div className="homepage_top-left">
                    <img src={HomeImage} alt="fon_home" />
                </div>
                <div className="homepage_top-right">
                    <img src={Logo} alt="logo hub" />
                    <h1>Welcome to Content Hub</h1>
                    <h2>Join us</h2>
                    <div className="homepage_top-right--auto_btns">
                        
                        <UserForm />

                        <button className="btn_primary">Sign up with Google</button>
                        <button className="btn_primary">Sign up with GitHub</button>
                        <p>
                            By signing up, you agree to the <a href="/">Terms of Service</a> and {""}
                            <a href="/">Privacy Policy</a>, including {""}
                            <a href="/">Cookies Use</a>.
                        </p>
                    </div>
                    <div className="homepage_top-right--auto_btns">
                        <h3>If you have a account</h3>
                        <button className="btn_primary">Sign in</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Homepage;