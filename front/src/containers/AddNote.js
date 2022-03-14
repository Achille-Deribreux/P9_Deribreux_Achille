import React from "react";
import SideBar from "../components/SideBar";
import AddNoteForm from "../components/AddNoteForm";

class AddNote extends React.Component{

    componentDidMount() {
        document.title = "Mediscreen | Add Note";
    }

    render() {
        return(
            <div id="wrapper">
                <SideBar />
                <AddNoteForm />
            </div>
        )
    }
}
export default AddNote;