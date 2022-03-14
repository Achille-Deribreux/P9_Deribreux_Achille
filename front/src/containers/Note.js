import React from "react";
import FullNote from "../components/FullNote";
import SideBar from "../components/SideBar";


class Note extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            note: []
        };
    }

    componentDidMount() {
        this.getData();
        document.title = "Mediscreen | Note";
    }

    getNoteId() {
        return new URL(document.location).searchParams.get('id');
    }

    getData() {
        fetch("http://localhost:8082/patHistory/getById?noteId="+this.getNoteId())
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded: true,
                    note: results
                });
            })
            .catch(function (err) {
                alert(err)
            });
    }


    render() {
        const {isLoaded, note} = this.state;

        if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return (
                <div id="wrapper">
                    <SideBar />
                    <FullNote note={note} />
                </div>
            )
        }

    }
}
export default Note;