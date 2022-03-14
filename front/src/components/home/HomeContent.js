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
                <Row className="my-5">
                    <Col className="my-5">
                        <h1 className="my-5"> Welcome to the awesome Mediscreen app ! </h1>
                    </Col>
                </Row>
                <Row className="my-5">
                    <Col className="my-5">
                        <h2 className="my-5"> Check patients, notes or reports ! </h2>
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