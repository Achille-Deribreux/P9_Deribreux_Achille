import {Row} from "react-bootstrap";
import React from "react";
import {Container} from "@mui/material";

class ReportsDataRow extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            assessmentMap:{},
            noneCounter:0,
            dangerCounter:0,
            earlyCounter:0,
            borderlineCounter:0
        };
    }

    componentDidMount() {
        this.getData();
    }

    getData(){
        fetch("http://localhost:8080/getAllAssessments")
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    assessmentMap: results
                });
                this.countRisks()
            })
            .catch(function(err){
                alert(err)
            });
    }

    countRisks(){
        let noneCounter = 0;
        let dangerCounter = 0;
        let earlyCounter = 0;
        let borderlineCounter = 0;

        for(const key in this.state.assessmentMap){
            if(this.state.assessmentMap[key]==="None"){
                noneCounter++;
            }
            else if(this.state.assessmentMap[key]==="In danger"){
                dangerCounter++;
            }
            else if(this.state.assessmentMap[key]==="Early onset"){
                earlyCounter++;
            }
            else if(this.state.assessmentMap[key]==="Borderline"){
                borderlineCounter++;
            }
        }
        this.setState({
            noneCounter : noneCounter,
            dangerCounter : dangerCounter,
            earlyCounter : earlyCounter,
            borderlineCounter : borderlineCounter,
            isLoaded: true
        });
    }

    render() {
        const  {isLoaded } = this.state;
        if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return (
                <Container className="mt-4">
                    <Row>
                        <div className="col-xl-3 col-md-6 mb-4">
                            <div className="card border-left-success shadow h-100 py-2">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2">
                                            <div className="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Risk none :
                                            </div>
                                            <div
                                                className="h5 mb-0 font-weight-bold text-gray-800">{this.state.noneCounter} Patients
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="col-xl-3 col-md-6 mb-4">
                            <div className="card border-left-primary shadow h-100 py-2">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2">
                                            <div className="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Risk Borderline :
                                            </div>
                                            <div
                                                className="h5 mb-0 font-weight-bold text-gray-800">{this.state.borderlineCounter} Patients
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div className="col-xl-3 col-md-6 mb-4">
                            <div className="card border-left-warning shadow h-100 py-2">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2">
                                            <div className="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                Risk In Danger :
                                            </div>
                                            <div
                                                className="h5 mb-0 font-weight-bold text-gray-800">{this.state.dangerCounter} Patients
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div className="col-xl-3 col-md-6 mb-4">
                            <div className="card border-left-danger shadow h-100 py-2">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2">
                                            <div className="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                                Risk Early onset :
                                            </div>
                                            <div
                                                className="h5 mb-0 font-weight-bold text-gray-800">{this.state.earlyCounter} Patients
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </Row>
                </Container>
            )
        }
    }
}
export default ReportsDataRow;