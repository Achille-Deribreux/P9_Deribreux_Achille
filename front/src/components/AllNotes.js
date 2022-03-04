import React from "react";
import {Container} from "react-bootstrap";
import FullNote from "./FullNote";
import Note from "./Note";

class AllNotes extends React.Component{

    render() {
        const{notes} = this.props
        return(
            <Container>
                {notes.map((note)=>(
                    <Note note={note} key={note.id} />
                    ))}
            </Container>
        )
    }
}
export default AllNotes;