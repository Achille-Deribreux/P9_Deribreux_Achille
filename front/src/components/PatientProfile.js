import React from "react";
import {Col, Row} from "react-bootstrap";
import {Button, Container, Typography} from "@mui/material";
import PatientNotesTable from "./PatientNotesTable";
import PatientsProfileData from "./PatientProfileData";
import '../assets/vendor/fontawesome-free/css/all.min.css'

class PatientsProfile extends React.Component {



    render() {
        const {patient} = this.props;
        return(
            <React.Fragment>
                <Container>
                    <Row>

                    </Row>
                    <Row className="m-5">
                        <Col xs={2}>
                            <button href="#" className="btn btn-danger btn-circle btn-lg">
                                <i className="fas fa-trash"></i>
                            </button>
                        </Col>
                        <Col>
                            <Typography variant="h4" component="h2" color={"black"} fontWeight={"bold"}>
                                {patient.given + patient.family}
                            </Typography>
                        </Col>
                        <Col xs={2}>
                            <button className="btn btn-primary btn-circle btn-lg">
                                <i className="fas fa-pen"></i>
                            </button>
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
                            <Button variant="contained" color="success" className="mx-1">Add Note</Button>
                        </Col>
                    </Row>
                    <Row className="m-5">
                        <PatientNotesTable />
                    </Row>
                </Container>
            </React.Fragment>
        )
    }
}
export default PatientsProfile;