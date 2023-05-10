import React from "react";
import "./ContentBox.css";

import { Avatar } from "@mui/material";
import {
    ImageOutlined, 
    GifBoxOutlined, 
    PollOutlined, 
    SentimentSatisfiedAltOutlined,
    CalendarTodayOutlined,
    LocationOnOutlined,
} from "@mui/icons-material"

const ContentBox = () => {
    return(
        <div className="contentbox">
            <form className="contentbox_form">
                <Avatar src="https://shapka-youtube.ru/wp-content/uploads/2021/03/mrachnaya-avatarka-dlya-parney.jpg" className="contentbox-avatar" />
                <div className="contentbox-field">
                    <div className="contentbox-input">
                        <input type="text" placeholder="What's new?" />
                    </div>
                    <div className="contentbox-input">
                        <div className="contentbox-icons">
                            <ImageOutlined className="contentbox-icon" />
                            <GifBoxOutlined className="contentbox-icon" />
                            <PollOutlined className="contentbox-icon" />
                            <SentimentSatisfiedAltOutlined className="contentbox-icon" />
                            <CalendarTodayOutlined className="contentbox-icon" />
                            <LocationOnOutlined className="contentbox-icon" />
                        </div>
                        <button>POST</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default ContentBox;