import React from 'react';
import '../assets/css/sb-admin-2.css'
import {Link} from "@mui/material";
import '../assets/vendor/fontawesome-free/css/all.min.css'
import SideBar from "../components/SideBar";
import Reports from "./Reports";
import ReportsDataRow from "../components/ReportsDataRow";
import HomeContent from "../components/home/HomeContent";

class Home extends React.Component{

    render(){
        return(
            <div id="wrapper">
                <SideBar />
                <HomeContent />
            </div>
        )
    }
}
export default Home;