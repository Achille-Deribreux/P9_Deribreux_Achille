import React from "react";
import {Button, Link, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

class PatientNotesTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            notes: []
        };
    }

    componentDidMount() {
        this.getData();
    }

    getPatientId() {
        return new URL(document.location).searchParams.get('id');
    }

    getDate(d){
        let date = new Date(d[0],d[1],d[2]);
        return date.toLocaleDateString("fr")
    }

    getData() {
        fetch("http://localhost:8082/patHistory/getAllNotesByPatientId?patientId=" + this.getPatientId())
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
            return(
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="center">Patient Id</TableCell>
                                <TableCell align="center">Date</TableCell>
                                <TableCell align="center">Note</TableCell>
                                <TableCell align="center">See full note</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {notes.map((note) => (
                                <TableRow
                                    key={note.id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                                    <TableCell align="center">{note.patientId}</TableCell>
                                    <TableCell align="center">{this.getDate(note.creationDateTime)}</TableCell>
                                    <TableCell align="center">{note.comment}</TableCell>
                                    <TableCell align="center">
                                        <Link href={"/note?id="+note.id}>
                                            <Button variant="contained">See full</Button>
                                        </Link>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )
        }
    }
}
export default PatientNotesTable;