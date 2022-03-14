import {Container, Link, Paper} from "@mui/material";
import {Col, Row} from "react-bootstrap";
import React from "react";

class FullNote extends React.Component{

    deleteNote(){
        fetch("http://localhost:8082/patHistory/delete?noteId="+ new URL(document.location).searchParams.get('id'), {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json' ,
            }
        })
            .then(() => {
                window.location="./notes"
            })
            .catch(function (err) {
                alert(err)
            });
    }

    render() {
        const{note} = this.props;
        return(
            <Container>
                <Row className="mt-3">
                    <Col xs={2}>
                        <Link href={"/notes"}>
                            <button className="btn btn-secondary btn-circle btn-lg">
                                <i className="fas fa-arrow-left" />
                            </button>
                        </Link>
                    </Col>
                    <Col>

                    </Col>
                    <Col xs={2}>
                        <button className="btn-lg btn-danger btn-circle mx-1" onClick={this.deleteNote}>
                            <i className="fas fa-trash" />
                        </button>
                        <Link href={"/editNote?id="+note.id}>
                            <button className="btn-lg btn-primary btn-circle mx-1">
                                <i className="fas fa-pen" />
                            </button>
                        </Link>
                    </Col>
                </Row>
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