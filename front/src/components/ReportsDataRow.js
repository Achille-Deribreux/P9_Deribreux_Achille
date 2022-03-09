import {Row} from "react-bootstrap";
import React from "react";
import {Container} from "@mui/material";

class ReportsDataRow extends React.Component{

    render() {
        return(
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
                                        <div className="h5 mb-0 font-weight-bold text-gray-800">X Patients</div>
                                    </div>
                                    <div className="col-auto">
                                        <i className="fas fa-calendar fa-2x text-gray-300"/>
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
                                        <div className="h5 mb-0 font-weight-bold text-gray-800">X Patients</div>
                                    </div>
                                    <div className="col-auto">
                                        <i className="fas fa-dollar-sign fa-2x text-gray-300"/>
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
                                        <div className="h5 mb-0 font-weight-bold text-gray-800">X Patients</div>
                                    </div>
                                    <div className="col-auto">
                                        <i className="fas fa-clipboard-list fa-2x text-gray-300"/>
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
                                        <div className="h5 mb-0 font-weight-bold text-gray-800">X Patients</div>
                                    </div>
                                    <div className="col-auto">
                                        <i className="fas fa-comments fa-2x text-gray-300"/>
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
export default ReportsDataRow;