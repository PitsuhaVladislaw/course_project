import React from "react";
import "./Widgets.css";

import { TwitterTimelineEmbed } from "react-twitter-embed";

const Widgets = () => {
    return(
        <div className="widgets">
            <TwitterTimelineEmbed
                sourceType="profile"
                screenName="saurabhnemade"
                options={{height: 600}}
            />
        </div>
    )
}

export default Widgets;