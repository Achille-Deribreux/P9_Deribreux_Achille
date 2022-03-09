import React from "react";
import {Link} from "@mui/material";

class NotesIllustration extends React.Component{

    render() {
        return(
            <div className="card shadow mb-4">
                <div className="card-header py-3">
                    <h6 className="m-0 font-weight-bold text-primary">Check Notes</h6>
                </div>
                <div className="card-body">
                    <div className="text-center my-3">
                        <img src={require("../../assets/home_logos/prescription.png")} alt="notes logo" width="30%" />
                    </div>
                    <Link href="/notes">Check notes &rarr;</Link>
                </div>
            </div>
        )
    }
}
export default NotesIllustration;