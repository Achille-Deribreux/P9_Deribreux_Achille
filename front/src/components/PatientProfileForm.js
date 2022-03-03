import React from "react";
import {Col, Form, Row} from "react-bootstrap";
import {Avatar, Button, Container, Typography} from "@mui/material";


class PatientsProfileForm extends React.Component {


    constructor(props, context) {
        super(props, context);
        this.state = {
            error: null,
            isLoaded: false,
            id:'',
            given:'',
            family:'',
            dob:'',
            sex:'',
            address:'',
            phone:''
        };
    }

    getPatientId(){
        return new URL(document.location).searchParams.get('id');
    }

    componentDidMount() {
        fetch("http://localhost:8081/patient/getById?id=" +this.getPatientId())
            .then(response => response.json())
            .then((patient) => {
                this.setState({
                    isLoaded: true,
                    id:patient.id,
                    given : patient.given,
                    family:patient.family,
                    dob:patient.dob,
                    sex:patient.sex,
                    address:patient.address,
                    phone:patient.phone,
                });
            })
            .catch(function (err) {
                alert(err)
            });
    }

    handleGiven = (e) => {
       this.setState({
           given: e.target.value
       })
    }

    handleFamily = (e) => {
        this.setState({
            family: e.target.value
        })
    }

    handleAddress = (e) => {
        this.setState({
            address: e.target.value
        })
    }

    handlePhone = (e) => {
        this.setState({
            phone: e.target.value
        })
    }

    handleSex = (e) => {
        this.setState({
            sex: e.target.value
        })
        console.log(this.state.sex)
    }

    handledob = (e) => {
        this.setState({
            dob: e.target.value
        })
    }

    handleSubmit = (e) => {
        //const data = JSON.stringify(this.state);

        const data = {
            "id" : this.state.id,
            "given": this.state.given,
            "family": this.state.family,
            "dob": this.state.dob,
            "sex": this.state.sex,
            "address": this.state.address,
            "phone": this.state.phone
        };
        console.log(data);
        fetch("http://localhost:8081/patient/update", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json' ,
                'Authorization': localStorage.getItem('token')
            },
            body: JSON.stringify(data)
        })
            .then((res) => {
                console.log(res);
            })
            .catch((err)=>console.log(err));
    }

    render() {
        const {patient} = this.props;
        return(
            <Container>
                <Row className="m-5">
                    <Col>
                        <div className="input-group m-4">
                            <span className="input-group-text">First and last name</span>
                            <input type="text" aria-label="First name" className="form-control" value={this.state.given} onChange={this.handleGiven}/>
                            <input type="text" aria-label="Last name" className="form-control" value={this.state.family} onChange={this.handleFamily}/>
                        </div>

                        <div className="input-group m-4">
                            <span className="input-group-text">Date of birth</span>
                            <input type="date" aria-label="Last name" className="form-control" value={this.state.dob} onChange={this.handledob}/>
                        </div>
                        <div className="input-group m-4">
                            <span className="input-group-text">Gender</span>
                            <Form.Select value={this.state.sex} onChange={this.handleSex}>
                                <option value="F">Female</option>
                                <option value="M">Male</option>
                            </Form.Select>
                        </div>
                        <div className="input-group m-4">
                            <span className="input-group-text">Address</span>
                            <input type="text" aria-label="Last name" className="form-control" value={this.state.address} onChange={this.handleAddress}/>
                        </div>
                        <div className="input-group m-4">
                            <span className="input-group-text">Phone Number</span>
                            <input type="text" aria-label="Last name" className="form-control" value={this.state.phone} onChange={this.handlePhone}/>
                        </div>
                        <button type="submit" className="btn-lg btn-primary" onClick={this.handleSubmit}>Edit Patient</button>
                    </Col>
                </Row>
            </Container>
        )
    }
}
export default PatientsProfileForm;