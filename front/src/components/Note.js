import {Container, Link, Paper} from "@mui/material";
import {Button, Col, Row} from "react-bootstrap";
import React from "react";

class Note extends React.Component{

    render() {
        const{note} = this.props;
        return(
            <Container className="mt-5">
                <Paper className="p-2 m-2">
                    <Row className="my-4">
                        <Col>
                            <Link href={"/note?id="+note.id}>
                                <button className="btn-lg btn-primary">See full</button>
                            </Link>
                        </Col>
                    </Row>
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
export default Note;