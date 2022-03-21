import {Container, Link, Paper} from "@mui/material";
import {Col, Row} from "react-bootstrap";
import React from "react";

class Note extends React.Component{

    getDate(d){
        return new Date(d[0],d[1],d[2])
    }

    render() {
        const{note} = this.props;
        return(
            <Container className="mt-5">
                <Paper className="p-4 m-2">
                    <Row className="mt-3">
                        <Col>
                            <Link href={"/note?id="+note.id}>
                                <button className="btn-lg btn-primary btn-circle"><i className="fas fa-eye" /></button>
                            </Link>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <p><b>Patient Id : </b> {note.patientId}</p>
                        </Col>
                        <Col>
                            <p><b>Date : </b> {this.getDate(note.creationDateTime)}</p>
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