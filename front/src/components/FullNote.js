import {Container, Paper} from "@mui/material";
import {Col, Row} from "react-bootstrap";
import React from "react";

class FullNote extends React.Component{

    render() {
        const{note} = this.props;
        return(
            <Container className="mt-5">
                <Paper className="p-2 m-2">
                    <Row>
                        <Col>
                            <p><b>Note Id : </b> {note.id}</p>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <p><b>Patient Id : </b> {note.patientId}</p>
                        </Col>
                        <Col>
                            <p><b>Date : </b> {note.creationDateTime}</p>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <p><b>Note : </b></p>
                            <p>{note.comment}</p>
                        </Col>
                    </Row>
                </Paper>
            </Container>
        )
    }
}
export default FullNote;