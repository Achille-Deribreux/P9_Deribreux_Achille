import React from "react";
import {Col, Row, Toast} from "react-bootstrap";
import {Button, Container, Link, Typography} from "@mui/material";
import PatientNotesTable from "./PatientNotesTable";
import PatientsProfileData from "./PatientProfileData";
import '../assets/vendor/fontawesome-free/css/all.min.css'


class PatientsProfile extends React.Component {

    constructor(props, context) {
        super(props, context);
    }

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
                <Row>

                </Row>
                <Row className="m-5">
                    <Col xs={2}>
                        <button onClick={this.deletePatient} className="btn btn-danger btn-circle btn-lg">
                            <i className="fas fa-trash" />
                        </button>
                    </Col>
                    <Col>
                        <Typography variant="h4" component="h2" color={"black"} fontWeight={"bold"}>
                            {patient.given + patient.family}
                        </Typography>
                    </Col>
                    <Col xs={2}>
                        <Link href={"/editProfile?id="+patient.id}>
                            <button className="btn btn-primary btn-circle btn-lg">
                                <i className="fas fa-pen" />
                            </button>
                        </Link>
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
                        <Link href="/addNote">
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