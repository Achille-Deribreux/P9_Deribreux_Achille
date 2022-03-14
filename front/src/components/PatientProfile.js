import React from "react";
import {Col, Row} from "react-bootstrap";
import {Button, Container, Link, Typography} from "@mui/material";
import PatientNotesTable from "./PatientNotesTable";
import PatientsProfileData from "./PatientProfileData";
import '../assets/vendor/fontawesome-free/css/all.min.css'
import RiskComponent from "./RiskComponent";


class PatientsProfile extends React.Component {

    deletePatient(){
        fetch("http://localhost:8081/patient/delete?id="+ new URL(document.location).searchParams.get('id'), {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json' ,
            }
        })
            .then(() => {
                window.location="./patients"
            })
            .catch(function (err) {
                alert(err)
            });
    }

    render() {
        const {patient} = this.props;
        return(
            <Container>
                <Row className="mt-3 align-items-center">
                    <Col xs={3}>
                        <Link href={"/patients"}>
                            <button className="btn btn-secondary btn-circle btn-lg">
                                <i className="fas fa-arrow-left" />
                            </button>
                        </Link>
                    </Col>
                    <Col xs={6}>
                        <RiskComponent patient={patient}/>
                    </Col>
                    <Col xs={3}>
                        <button onClick={this.deletePatient} className="btn btn-danger btn-circle btn-lg mx-1">
                            <i className="fas fa-trash" />
                        </button>
                        <Link href={"/editProfile?id="+patient.id}>
                            <button className="btn btn-primary btn-circle btn-lg mx-1">
                                <i className="fas fa-pen" />
                            </button>
                        </Link>
                    </Col>
                </Row>
                <Row className="m-5">
                    <Col>
                        <Typography variant="h4" component="h2" color={"black"} fontWeight={"bold"}>
                            {patient.given + patient.family}
                        </Typography>
                    </Col>
                </Row>
                <Row className="m-5">
                    <PatientsProfileData patient={patient} />
                </Row>
                <Row className="m-3">
                    <Col xs={9}>
                        <h4>See all patient's notes :</h4>
                    </Col>
                    <Col xs={3}>
                        <Link href={"/addNote?id="+patient.id}>
                            <Button variant="contained" color="primary" className="mx-1">Add Note</Button>
                        </Link>
                    </Col>
                </Row>
                <Row className="m-5">
                    <PatientNotesTable />
                </Row>
            </Container>
        )
    }
}
export default PatientsProfile;