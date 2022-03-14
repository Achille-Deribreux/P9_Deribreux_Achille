import React from 'react';
import '../assets/css/sb-admin-2.css'
import '../assets/vendor/fontawesome-free/css/all.min.css'
import SideBar from "../components/SideBar";
import HomeContent from "../components/home/HomeContent";

class Home extends React.Component{

    componentDidMount() {
        document.title = "Mediscreen | Home";
    }

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