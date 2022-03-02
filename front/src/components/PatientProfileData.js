import React from "react";
import {Col, Form, Row} from "react-bootstrap";
import {Paper} from "@mui/material";


class PatientsProfileData extends React.Component {


    constructor(props, context) {
        super(props, context);
    }


    render() {
        const {patient} = this.props;
        return(
            <Paper className="p-2 mb-5">
                <Row className="mx-3">
                    <Col>
                        <p className="mt-3 h5">Gender : {patient.sex}</p>
                    </Col>
                    <Col>
                        <p className="mt-3 h5">Birthdate : {patient.dob}</p>
                    </Col>
                </Row>
                <Row className="m-3">
                    <p className="mt-3 h5">Address : {patient.address}</p>
                </Row>
                <Row className="m-3">
                    <p className="mt-3 h5">Phone : {patient.phone}</p>
                </Row>
            </Paper>
        )
    }
}
export default PatientsProfileData;