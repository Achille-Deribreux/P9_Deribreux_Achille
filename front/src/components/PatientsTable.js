import React from "react";
import {Button, Link, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

class PatientsTable extends React.Component{
    render(){
        const {patients} = this.props;
        return(
        <div className="card-body">
            <div className="table-responsive">
                <table className="table table-bordered" id="dataTable" width="100%" cellSpacing="0">
                    <thead>
                    <tr>
                        <th>Given name</th>
                        <th>Family name</th>
                        <th>Birthdate</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Phone number</th>
                        <th>Profile</th>
                    </tr>
                    </thead>
                    <tbody>
                    {patients.map((patient) => (
                        <tr key={patient.id}>
                            <td align="center">{patient.given}</td>
                            <td align="center">{patient.family}</td>
                            <td align="center">{patient.dob}</td>
                            <td align="center">{patient.sex}</td>
                            <td align="center">{patient.address}</td>
                            <td align="center">{patient.phone}</td>
                            <td align="center">
                                <Link href={"/profile?id="+patient.id}>
                                    <Button variant="contained">See profile</Button>
                                </Link>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
        )
    }
}
export default PatientsTable;