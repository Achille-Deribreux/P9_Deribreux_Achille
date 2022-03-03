import React from "react";
import SideBar from "../components/SideBar";
import PatientsProfileForm from "../components/PatientProfileForm";

class EditProfile extends React.Component{

    render() {
        return(
        <div id="wrapper">
            <SideBar />
            <PatientsProfileForm />
        </div>
        )
    }
}
export default EditProfile;