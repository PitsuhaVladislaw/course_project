import React from "react";
import "./Home.css";
import Sidebar from "./Sidebar/Sidebar";
import Feed from "./Feed/Feed";
import Widgets from "./Widgets/Widgets";

const Home = () => {
    alert("Project has't already finished");

    return(
        <div className="home">
            {/*Sidebar*/}
            <Sidebar />
            {/*Feed*/}
            <Feed />
            {/*Widgets*/}
            <Widgets />
        </div>
    )
}

export default Home;