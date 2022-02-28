import React from 'react';
import Header from '../components/Header'
import {Container} from "react-bootstrap";

class Patients extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            patients: [],
            rows:[],
            reload : false
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
               console.log(this.state.patients);
            })
            .catch(function(err){
                alert(err)
            });
    }

    render(){
        const  {isLoaded, patients } = this.state;

        const columns = [
            { field: 'id', headerName: 'ID', width: 70 },
            { field: 'givenName', headerName: 'Given name', width: 130 },
            { field: 'familyName', headerName: 'Family name', width: 130 },
            { field: 'birthdate', headerName: 'Birthdate', width: 130 },
            { field: 'sex', headerName: 'Gender', width: 130 },
            { field: 'address', headerName: 'Address', width: 130 },
            { field: 'phoneNumber', headerName: 'Phone number', width: 130 }
        ]

        if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return (
                <React.Fragment>
                    <Header/>
                </React.Fragment>
            )
        }
    }
}
export default Patients;