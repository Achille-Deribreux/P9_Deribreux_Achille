import React from "react";
import SideBar from "../components/SideBar";
import AddPatientForm from "../components/AddPatientForm";

class AddPatient extends React.Component{

    render() {
        return(
            <div id="wrapper">
                <SideBar />
                <AddPatientForm />
            </div>
        )
    }
}
export default AddPatient;