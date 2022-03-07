import React from "react";
import {Col, Container, Row} from "react-bootstrap";
import FullNote from "./FullNote";
import Note from "./Note";
import {Link} from "@mui/material";

class AllNotes extends React.Component{

    constructor(props, context) {
        super(props, context);
        this.state = {
            searchTerm: ''
        };
    }

    handleSearchTerm = (e) => {
        this.setState({
            searchTerm:e.target.value
        })
    }

    render() {
        const{notes} = this.props
        return(
            <Container>
                <Row className="align-items-center">
                    <Col xs={10}>
                        <div className="input-group m-4">
                            <span className="input-group-text">Search note :</span>
                            <input type="text" aria-label="Last name" className="form-control" value={this.state.searchTerm} onChange={this.handleSearchTerm}/>
                        </div>
                    </Col>
                    <Col>
                        <Link href={"/addNote"}>
                            <button className="btn btn-primary btn-circle btn-lg">
                                <i className="fas fa-plus" />
                            </button>
                        </Link>
                    </Col>
                </Row>
                {notes
                    .filter((note)=>{
                        if(note.comment.toLowerCase().includes(this.state.searchTerm.toLowerCase())){
                            return note;
                        }
                    })
                    .map((note)=>(
                    <Note note={note} key={note.id} />
                    ))}
            </Container>
        )
    }
}
export default AllNotes;