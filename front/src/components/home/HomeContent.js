import {Container} from "@mui/material";
import React from "react";
import ReportsDataRow from "../ReportsDataRow";
import {Col, Row} from "react-bootstrap";
import ReportsIllustration from "./ReportsIllustration";
import NotesIllustration from "./NotesIllustration";
import PatientsIllustration from "./PatientsIllustration";

class HomeContent extends React.Component{

    render() {
        return(
            <Container>
                <ReportsDataRow />
                <Row>
                    <Col>
                        <img src={require('../../mediscreen-logo.png')} width="200" alt="cam"/>
                        TO BE COMPLETED
                    </Col>
                </Row>
                <Row>
                    <Col xs={4}>
                        <ReportsIllustration />
                    </Col>
                    <Col xs={4}>
                        <NotesIllustration />
                    </Col>
                    <Col xs={4}>
                        <PatientsIllustration />
                    </Col>
                </Row>
            </Container>
        )
    }
}
export default HomeContent;