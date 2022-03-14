import SideBar from "../components/SideBar";
import React from "react";
import ReportsTable from "../components/ReportsTable";

class Reports extends React.Component{

    componentDidMount() {
        document.title = "Mediscreen | Reports";
    }

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