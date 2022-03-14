import React from "react";
import {
    Button,
    Container,
    Link,
} from "@mui/material";
import {Col, Row} from "react-bootstrap";

class PatientsTable extends React.Component{

    constructor(props, context) {
        super(props, context);
        this.state = {
            searchTerm: ''
        };
    }

    handleSearchTerm = (e) => {
        this.setState({
            searchTerm:e.target.value
        })
    }

    render(){
        const {patients} = this.props;
        return(
            <Container>
                <Row className="align-items-center">
                    <Col xs={1}>
                        <Link href={"/"}>
                            <button className="btn btn-secondary btn-circle btn-lg">
                                <i className="fas fa-arrow-left" />
                            </button>
                        </Link>
                    </Col>
                    <Col xs={9}>
                        <div className="input-group m-4">
                            <span className="input-group-text">Search patient :</span>
                            <input type="text" aria-label="Last name" className="form-control" value={this.state.searchTerm} onChange={this.handleSearchTerm}/>
                        </div>
                    </Col>
                    <Col>
                        <Link href={"/addPatient"}>
                            <button className="btn btn-primary btn-circle btn-lg">
                                <i className="fas fa-plus" />
                            </button>
                        </Link>
                    </Col>
                </Row>

                <Row>
                    <div className="card-body">
                        <div className="table-responsive">
                            <table className="table table-bordered" id="dataTable" width="100%" cellSpacing="0">
                                <thead>
                                <tr>
                                    <th>Given name</th>
                                    <th>Family name</th>
                                    <th>Birthdate</th>
                                    <th>Gender</th>
                                    <th>Address</th>
                                    <th>Phone number</th>
                                    <th>Profile</th>
                                </tr>
                                </thead>
                                <tbody>
                                {patients
                                    .filter((patient)=>{
                                        if(patient.given.toLowerCase().includes(this.state.searchTerm.toLowerCase()) || patient.family.toLowerCase().includes(this.state.searchTerm.toLowerCase())){
                                            return patient;
                                        }
                                    })
                                    .map((patient) => (
                                    <tr key={patient.id}>
                                        <td align="center">{patient.given}</td>
                                        <td align="center">{patient.family}</td>
                                        <td align="center">{patient.dob}</td>
                                        <td align="center">{patient.sex}</td>
                                        <td align="center">{patient.address}</td>
                                        <td align="center">{patient.phone}</td>
                                        <td align="center">
                                            <Link href={"/profile?id="+patient.id}>
                                                <Button variant="contained">See profile</Button>
                                            </Link>
                                        </td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </Row>
            </Container>
        )
    }
}
export default PatientsTable;