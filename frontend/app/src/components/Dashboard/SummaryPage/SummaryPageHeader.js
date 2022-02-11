import React from "react";
import { useNavigate } from "react-router";
import BackButton from "../Assets/backButton.png"

function SummaryPageHeader(props) {

    const navigate = useNavigate();
    const handleRoute = () => {
        navigate(`/dashboard`, {
            state: {
                userID: props.userID,
                name: props.name
            }
        });
    }

    return (
        <div className="summary-page-bg-header">
            <div className="summary-page-header-left">
                <img src = {BackButton} className="back-button-img" onClick={handleRoute} alt="Go Back"/>
                <div className="summary-page-header-text-container">
                    <div className="summary-page-header-title">Hello {props.name}</div>
                    <div className="summary-page-header-subtitle">We have the following suggestions for you: </div>
                </div>
            </div>
            <div className="summary-page-header-overall-score">
                <div className="summary-page-header-overall-score-left">Your overall score</div>
                <div className="summary-page-header-overall-score-right">
                    <div className={props.score < 60 ? 'summary-page-header-overall-score-right-score-red' : "summary-page-header-overall-score-right-score-green"} >{props.score}</div>
                    <div className="summary-page-header-overall-score-right-overall">/100</div>
                </div>       
            </div>
        </div>
    );
}

export default SummaryPageHeader;