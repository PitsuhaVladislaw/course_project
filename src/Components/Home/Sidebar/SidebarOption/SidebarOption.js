import React from "react";
import "./SidebarOption.css";

const SidebarOption = (props) => {

    const { text, icon, active } = props;
    
    return(
        <div className={`siderbar-option ${active && "sidebar-option_active"}`}>
            SidebarOption
        </div>
    )
}

export default SidebarOption;