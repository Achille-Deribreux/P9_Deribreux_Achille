import {Col, Form, Row} from "react-bootstrap";
import {Container, Link} from "@mui/material";
import React from "react";


class AddNoteForm extends React.Component{

    constructor(props, context) {
        super(props, context);
        this.state = {
            isLoaded: false,
            patients:'',
            patientId: '',
            note: ''
        }
    }

    componentDidMount() {
        fetch("http://localhost:8081/patient/getAll")
            .then(response => response.json())
            .then((patients) => {
                this.setState({
                    isLoaded: true,
                    patients:patients
                });
            })
            .catch(function (err) {
                alert(err)
            });
    }

    handleSubmit = () => {
        const data = {
            "patId": this.state.patientId,
            "notes":this.state.note
        }

        fetch("http://localhost:8082/patHistory/add", {
            method: 'POST',
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

    handlePatient = (e) => {
        this.setState({
            patientId: e.target.value
        })
    }

    handleNote = (e) => {
        this.setState({
            note: e.target.value
        })
    }

    render() {
        const {isLoaded, patients} = this.state;
        if (!isLoaded) {
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
                                <Form.Select value={this.state.patientId} onChange={this.handlePatient}>
                                    {patients.map((patient) =>(
                                        <option value={patient.id}>{patient.given+" "+patient.family}</option>
                                        ))}
                                </Form.Select>
                            </div>
                            <div className="input-group m-4">
                                <span className="input-group-text">Note : </span>
                                <textarea className="form-control" id="exampleFormControlTextarea1" rows="3"
                                          value={this.state.note} onChange={this.handleNote}/>
                            </div>
                            <button type="submit" className="btn-lg btn-primary" onClick={this.handleSubmit}>Save Note</button>
                        </Col>
                    </Row>
                </Container>
            )
        }
    }
}
export default AddNoteForm;