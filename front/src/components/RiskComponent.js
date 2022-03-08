import React from "react";

class RiskComponent extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            assessmentMap:{},
        };
    }

    componentDidMount() {
        this.getData();
    }

    getData(){
        fetch("http://localhost:8080/getAllAssessments")
            .then(response => response.json())
            .then((results) => {
                this.setState({
                    isLoaded: true,
                    assessmentMap: results
                });
            })
            .catch(function(err){
                alert(err)
            });
    }

    getRisk(id){
        if(this.state.assessmentMap[id]==="None"){
            return <p className="btn-success">{this.state.assessmentMap[id]}</p>
        }
        else if(this.state.assessmentMap[id]==="In danger"){
            return <p className="btn-warning">{this.state.assessmentMap[id]}</p>
        }
        else if(this.state.assessmentMap[id]==="Early onset"){
            return <p className="btn-danger">{this.state.assessmentMap[id]}</p>
        }
        else if(this.state.assessmentMap[id]==="Borderline"){
            return <p className="btn-primary">{this.state.assessmentMap[id]}</p>
        }

    }

    render() {
        const  {isLoaded} = this.state;
        const {patient} = this.props;
        if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return this.getRisk(patient.id);
        }
    }
}
export default RiskComponent;