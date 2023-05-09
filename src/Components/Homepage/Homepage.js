import React from "react";
import "./Homepage.css"

import Logo from "../../assets/icon/logo_hub.ico";
import HomeImage from "../../assets/images/fon_home.jpg";

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
                        <button className="btn_primary">Sign up with Google</button>
                        <button className="btn_primary">Sign up with GitHub</button>
                        <button className="btn_secondary">Sign up with email or phone</button>
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
            <footer>
                <ul className="footer_list">
                    <li className="footer_list_item">
                        <a href="/">About</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Help Center</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Terms of Service</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Privacy Policy</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Cookie Policy</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Accessibility</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Ads Info</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Terms of Service</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Blog</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Status</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Careers</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Brand Resources</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Terms of Service</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Developers</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Directory</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Advertising</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Marketing</a>
                    </li>
                    <li className="footer_list_item">
                        <a href="/">Content Hub for Business</a>
                    </li>
                </ul>
                <p>
                    {" "}
                    <a href="/">Setting</a>
                    <span>&copy; Content Hub</span>
                </p>
            </footer>
        </div>
    )
}

export default Homepage;