import React from "react";
import "./SidebarOption.css";

const SidebarOption = (props) => {

    const { active, Icon, text } = props;
    
    return(
        <div className={`sidebar-option ${active ? "sidebar-option_active" : ""}`}>
            <Icon className="sidebar-option_icon" />
            <h2>{text}</h2>
        </div>
    )
}

export default SidebarOption;