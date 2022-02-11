import React , {useState} from "react";
import { useNavigate } from "react-router-dom";

function HistoryReportItem(props) {

    const navigate = useNavigate();
    const [yProgress, setY] = useState([])
    const API = "https://speech-helper-backend.herokuapp.com"
	
    const handleRoute = () => {
        getFeedback();
    }

    function handleErrors(response) {
		if (!response.ok) {
			throw Error(response.statusText);
		}
		return response;
	}

    const getFeedback = async() =>  {
        fetch(`/report_speech_id?speechId=${props.speechId}`, {
            method: "GET"
        })
        .then(handleErrors)
        .then(async response => await response.json())
        .then(data => {
            props.updateProgress(data.score)
            console.log(yProgress)
            navigate(`/summary`, {
                state: {
                    userID: props.userId,
                    name: props.name,
                    data: data
                }
            });
        })
		.catch(error => console.log(error) );
    }

    return (
        <div className="report-item-container">
            <div className="report-item">
                <div className="report-item-child">
                    <div className="report-item-left">
                        <div className="number-overlay">{props.num}</div>
                        <div className="history-speech-text-container">
                            <div className="history-speech-title">Speech {props.speechTitle}</div>
                            <div className="history-speech-subtitle">Recorded: {props.speechDate}</div>
                        </div>
                    </div>
                    <button className="theme-btn summary-btn" onClick={handleRoute}>Go to Summary</button>
                </div>
                {/* <div className="separator"></div> */}
            </div>
        </div>
    );
} 

function HistoryReport(props) {

    return (
        <div className= "report-list-container">
            <p className="report-title">History</p>
            {(() => {

                if (Object.keys(props.historySpeeches).length === 0) {
                    return (
                        <div className="report-item-container-empty">
                          <div className="report-item-empty">Looks like you are new here, tap the generate button. All your reports will be shown here.</div>
                        </div> 
                    );
                }
                else {
                    return (
                        <div className="report-items-container">
                        {   
                            Object.keys(props.historySpeeches).map((key, index) => ( 
                                <HistoryReportItem speechId = {key} num = {index+1} speechTitle = {index+1} speechDate = {props.historySpeeches[key]} name = {props.name} userId = {props.userId} updateProgress = {props.updateProgress}/>  
                            ))
                        }
                        </div>
                    );
                }
            }
            )()}   
        </div>
    );
}

export default HistoryReport;