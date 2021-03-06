import React from "react";
import PatientsProfile from "../components/PatientProfile";
import '../assets/css/sb-admin-2.css'
import SideBar from "../components/SideBar";

class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            patient: []
        };
    }

    componentDidMount() {
        this.getData();
        document.title = "Mediscreen | Patient";
    }

    getPatientid() {
        return new URL(document.location).searchParams.get('id');
    }

    getData() {
        fetch("http://localhost:8081/patient/getById?id=" + this.getPatientid())
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded: true,
                    patient: results
                });
            })
            .catch(function (err) {
                alert(err)
            });
    }

    render() {
        const {isLoaded, patient} = this.state;
        if (!isLoaded) {
            return <div>Chargementâ€¦</div>;
        } else {
            return (
                    <div id="wrapper">
                        <SideBar />
                        <PatientsProfile patient={patient}/>
                    </div>
            )
        }
    }
}
export default Profile;