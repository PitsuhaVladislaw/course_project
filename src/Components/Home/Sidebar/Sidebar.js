import React from 'react';
import SidebarOption from "./SidebarOption/SidebarOption";
import './Sidebar.css';

import { 
    Home,
    ListAlt,
    Search,
    PermIdentity,
    NotificationsNone,
    MoreHoriz,
    MailOutline,
    BookmarkBorder
} from '@mui/icons-material';
import { Button } from '@mui/material';
//import {Logo} from "https://cdn-icons-png.flaticon.com/512/2503/2503644.png";

const Sidebar = () => {
    return(
        <div className="sidebar">
            <h2 className='siderbar-logo'>Content <span>hub</span></h2>
            <SidebarOption active Icon={Home} text="Home"/>
            <SidebarOption Icon={Search} text="Explore"/>
            <SidebarOption Icon={NotificationsNone} text="Notifications"/>
            <SidebarOption Icon={MailOutline} text="Messages"/>
            <SidebarOption Icon={BookmarkBorder} text="Bookmarks"/>
            <SidebarOption Icon={ListAlt} text="Lists"/>
            <SidebarOption Icon={PermIdentity} text="Profile"/>
            <SidebarOption Icon={MoreHoriz} text="More"/>

            <Button className='sidebar_post' variant='outlined' fullWidth>
                POST
            </Button>
        </div>
    )
}

export default Sidebar;