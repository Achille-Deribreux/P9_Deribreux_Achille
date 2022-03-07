import React from 'react';
import PatientsTable from "../components/PatientsTable";
import SideBar from "../components/SideBar";

class Patients extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            patients: []
        };
    }

    componentDidMount() {
        this.getData();
    }

    getData(){
        fetch("http://localhost:8081/patient/getAll")
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded: true,
                    patients: results
                });
            })
            .catch(function(err){
                alert(err)
            });
    }

    render(){
        const  {isLoaded, patients } = this.state;

        if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return (
                <div id="wrapper">
                    <SideBar />
                    <PatientsTable patients={patients}/>
                </div>
            )
        }
    }
}
export default Patients;