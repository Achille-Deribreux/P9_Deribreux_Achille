import {Link} from "@mui/material";
import React from "react";
import '../assets/css/sb-admin-2.css'
import '../assets/vendor/fontawesome-free/css/all.min.css'

class SideBar extends React.Component{
    render() {
        return(
            <ul className="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <Link href="/">
                    <div className="sidebar-brand-text my-5 ">
                        <img src={require('../mediscreen-logo.png')} width="200" alt="cam"/>
                    </div>
                </Link>
                <li className="nav-item my-2">
                    <Link className="nav-link" href="/patients">
                        <i className="fas fa-user"/>
                        <span><b> Patients</b></span>
                    </Link>
                </li>
                <li className="nav-item  my-2">
                    <Link className="nav-link" href="/notes">
                        <i className="fas fa-fw fa-notes-medical"/>
                        <span><b> Notes</b></span>
                    </Link>
                </li>
                <li className="nav-item  my-2">
                    <Link className="nav-link" href="/reports">
                        <i className="fas fa-fw fa-book-medical"/>
                        <span><b> Reports</b></span>
                    </Link>
                </li>
            </ul>
        )
    }
}
export default SideBar;