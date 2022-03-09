import React from 'react';
import {Container, Link} from "@mui/material";
import {Col, Form, Row} from "react-bootstrap";

class EditNoteForm extends React.Component{

    constructor(props, context) {
        super(props, context);
        this.state = {
            isLoaded: false,
            comment: '',
            patientId:'',
            isLoaded2:false,
            patients:''
        };
    }

    componentDidMount() {
        this.getData();
    }

    getNoteId() {
        return new URL(document.location).searchParams.get('id');
    }

    getData() {
        fetch("http://localhost:8081/patient/getAll")
            .then(response => response.json())
            .then((patients) => {
                this.setState({
                    patients:patients,
                    isLoaded2: true
                });
            })
            .catch(function (err) {
                alert(err)
            });
        fetch("http://localhost:8082/patHistory/getById?noteId="+this.getNoteId())
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    comment: results.comment,
                    patientId:results.patientId,
                    isLoaded: true
                });
            })
            .catch(function (err) {
                alert(err)
            });
    }

    handleNoteComment = (e) => {
        this.setState({
            comment: e.target.value
        })
    }

    handlePatientId = (e) => {
        this.setState({
            patientId: e.target.value
        })
    }

    handleSubmit = (e) => {
        const data = {
            "id": this.getNoteId(),
            "patId": this.state.patientId,
            "notes":this.state.comment
        }

        fetch("http://localhost:8082/patHistory/update", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json' ,
            },
            body: JSON.stringify(data)
        })
            .then((res) => {
                window.location="./notes"
            })
            .catch((err)=>console.log(err));
    }

    render() {
        const {isLoaded, isLoaded2, patients} = this.state;
        if (!isLoaded && !isLoaded2) {
            return <div>Chargement…</div>;
        } else {
            return (
                <Container>
                    <Row className="mt-3">
                        <Col xs={2}>
                            <Link href={"/notes"}>
                                <button className="btn btn-secondary btn-circle btn-lg">
                                    <i className="fas fa-arrow-left" />
                                </button>
                            </Link>
                        </Col>
                    </Row>
                    <Row className="m-5">
                        <Col>
                            <div className="input-group m-4">
                                <span className="input-group-text">Patient : </span>
                                <Form.Select value={this.state.patientId} onChange={this.handlePatientId}>
                                    {patients.map((patient) => (
                                        <option key={patient.id} value={patient.id}>{patient.id}</option>
                                    ))}
                                </Form.Select>
                            </div>
                            <div className="input-group m-4">
                                <span className="input-group-text">Note : </span>
                                <textarea className="form-control" id="exampleFormControlTextarea1" rows="3"
                                          value={this.state.comment} onChange={this.handleNoteComment}/>
                            </div>
                            <button type="submit" className="btn-lg btn-primary" onClick={this.handleSubmit}>Save Note
                            </button>
                        </Col>
                    </Row>
                </Container>
            );
        }
    }
}
export default EditNoteForm;