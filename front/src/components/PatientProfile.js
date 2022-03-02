import React from "react";
import {Col, Form, Row} from "react-bootstrap";
import {Avatar, Button, Container, Typography} from "@mui/material";
import PatientsProfileForm from "./PatientProfileForm";
import PatientNotesTable from "./PatientNotesTable";
import PatientsProfileData from "./PatientProfileData";


class PatientsProfile extends React.Component {



    render() {
        const {patient} = this.props;
        return(
            <React.Fragment>
                <Container>
                    <Row className="m-5">
                        <Col>
                            <Typography variant="h4" component="h2">
                                {patient.given + patient.family}
                            </Typography>
                        </Col>
                        <Col>
                            <Button variant="contained" color="primary" className="mx-1">Edit Patient</Button>
                            <Button variant="contained" color="error" className="mx-1">Delete Patient</Button>
                        </Col>
                    </Row>
                    <Row className="m-5">
                        <h4>See all patient's notes :</h4>
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