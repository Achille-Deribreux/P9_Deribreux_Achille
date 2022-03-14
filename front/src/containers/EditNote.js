import React from 'react';
import SideBar from "../components/SideBar";
import EditNoteForm from "../components/EditNoteForm";

class EditNote extends React.Component{

    componentDidMount() {
        document.title = "Mediscreen | Edit Note";
    }

    render() {
        return(
            <div id="wrapper">
                <SideBar />
                <EditNoteForm />
            </div>
        )
    }
}
export default EditNote;