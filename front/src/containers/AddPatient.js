import React from "react";
import SideBar from "../components/SideBar";
import AddPatientForm from "../components/AddPatientForm";

class AddPatient extends React.Component{

    componentDidMount() {
        document.title = "Mediscreen | Add Patient";
    }

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