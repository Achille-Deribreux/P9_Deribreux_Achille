import {Container, Link, Paper} from "@mui/material";
import {Button, Col, Row} from "react-bootstrap";
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
            <Container className="mt-5">
                <Paper className="p-2 m-2">
                    <Row className="my-4">
                        <Col>
                            <button className="btn-lg btn-danger" onClick={this.deleteNote}>Delete note</button>
                        </Col>
                        <Col>
                            <Link href={"/editNote?id="+note.id}>
                                <button className="btn-lg btn-primary">Edit note</button>
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
export default FullNote;