import React from "react";

function FillerCard(props) {
    return (
        <div className="filler-info-container">
            <div className="filler-text-container">
                <div className="filler-percent-text-container">
                    <div className={props.fillerCount > 30 ? "text-red filler-percent-text" : "text-green filler-percent-text"}>{props.fillerCount}</div>
                    <div className={props.fillerCount > 30 ? "text-red filler-percent" : "text-green filler-percent"}>%</div>
                </div>
                <div className="filler-text-message">
                    speech is 
                    <div className={props.fillerCount > 30 ? "text-red" : "text-green"}>filler</div> 
                </div>
            </div>
        </div>
    );
}

export default FillerCard;