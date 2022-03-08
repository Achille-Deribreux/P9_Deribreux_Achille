import SideBar from "../components/SideBar";
import PatientsTable from "../components/PatientsTable";
import React from "react";
import ReportsTable from "../components/ReportsTable";

class Reports extends React.Component{
    render() {
        return(
            <div id="wrapper">
                <SideBar />
                <ReportsTable />
            </div>
        )
    }
}
export default Reports;