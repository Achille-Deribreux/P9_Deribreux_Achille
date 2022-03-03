import React from "react";
import {Container} from "react-bootstrap";
import FullNote from "./FullNote";

class AllNotes extends React.Component{

    render() {
        const{notes} = this.props
        return(
            <Container>
                {notes.map((note)=>(
                    <FullNote note={note} />
                    ))}
            </Container>
        )
    }
}
export default AllNotes;