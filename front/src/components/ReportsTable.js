import {Button, Container, Link} from "@mui/material";
import {Col, Row} from "react-bootstrap";
import React from "react";

class ReportsTable extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            isLoaded2: false,
            patients: [],
            assessmentMap:{},
            searchTerm:''
        };
    }

    handleSearchTerm = (e) => {
        this.setState({
            searchTerm:e.target.value
        })
    }

    componentDidMount() {
        this.getData();
    }

    getData(){
        fetch("http://localhost:8081/patient/getAll")
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded: true,
                    patients: results
                });
            })
            .catch(function(err){
                alert(err)
            });

        fetch("http://localhost:8080/getAllAssessments")
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded2: true,
                    assessmentMap: results
                });
            })
            .catch(function(err){
                alert(err)
            });
    }

    getRisk(id){
        if(this.state.assessmentMap[id]==="None"){
            return <p className="btn-success">{this.state.assessmentMap[id]}</p>
        }
        else if(this.state.assessmentMap[id]==="In danger"){
            return <p className="btn-warning">{this.state.assessmentMap[id]}</p>
        }
        else if(this.state.assessmentMap[id]==="Early onset"){
            return <p className="btn-danger">{this.state.assessmentMap[id]}</p>
        }
        else if(this.state.assessmentMap[id]==="Borderline"){
            return <p className="btn-primary">{this.state.assessmentMap[id]}</p>
        }

    }

    render() {
        const  {isLoaded,isLoaded2, patients,assessmentMap } = this.state;
        if (!isLoaded && !isLoaded2) {
            return <div>Chargement…</div>;
        } else {
            return(
                <Container>
                    <Row className="align-items-center">
                        <Col xs={1}>
                            <Link href={"/"}>
                                <button className="btn btn-secondary btn-circle btn-lg">
                                    <i className="fas fa-arrow-left" />
                                </button>
                            </Link>
                        </Col>
                        <Col xs={9}>
                            <div className="input-group m-4">
                                <span className="input-group-text">Search patient :</span>
                                <input type="text" aria-label="Last name" className="form-control" value={this.state.searchTerm} onChange={this.handleSearchTerm}/>
                            </div>
                        </Col>
                    </Row>
                    <Row>
                        <div className="card-body">
                            <div className="table-responsive">
                                <table className="table table-bordered" id="dataTable" width="100%" cellSpacing="0">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Risk</th>
                                        <th>Profile</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {patients
                                        .filter((patient)=>{
                                            if(patient.given.toLowerCase().includes(this.state.searchTerm.toLowerCase()) || patient.family.toLowerCase().includes(this.state.searchTerm.toLowerCase())){
                                                return patient;
                                            }
                                        })
                                        .map((patient) => (
                                        <tr key={patient.id}>
                                            <td align="center">{patient.given + " " + patient.family}</td>
                                            <td align="center">{this.getRisk(patient.id)}</td>
                                            <td align="center">
                                                <Link href={"/profile?id=" + patient.id}>
                                                    <Button variant="contained">See profile</Button>
                                                </Link>
                                            </td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </Row>
                </Container>
            )
        }
    }
}
export default ReportsTable;