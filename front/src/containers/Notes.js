import React from "react";
import SideBar from "../components/SideBar";
import AllNotes from "../components/AllNotes";


class Notes extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            notes: []
        };
    }

    componentDidMount() {
        this.getData();
    }

    getData() {
        fetch("http://localhost:8082/patHistory/getAll")
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded: true,
                    notes: results
                });
            })
            .catch(function (err) {
                alert(err)
            });
    }

    render() {
        const {isLoaded, notes} = this.state;

        if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return (
            <div id="wrapper">
                <SideBar />
                <AllNotes notes={notes} />
            </div>
            )
        }
    }
}
export default Notes;