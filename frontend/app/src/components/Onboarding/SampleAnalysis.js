import React from 'react';
import FillerCard from "../Dashboard/SummaryPage/FillerCard";
import PaceCard from "../Dashboard/SummaryPage/PaceCard";
import { Animated } from 'react-animated-css';
import SentimentCard from '../Dashboard/SummaryPage/SentimentCard';


function Analysis(props) {
    return (
        <Animated animationIn="bounceInRight" animationOut="bounceOutLeft">  
        <div>
            {/* <p className="analysis-title">Analysis</p> */}
            <div className="summary-container-child-pace-filler-row">
                   <FillerCard className="" fillerCount={props.fillerCount}/>
                   <PaceCard className="" pace = {props.pace}/>
            </div>
            <SentimentCard sentiment = {props.sentiment}/>
        </div> 
        </Animated>
    );

}

function SampleAnalysis(props) {

    return (
        <div>
             {(()=>{
                if (props.showReport) return  <Analysis fillerCount = {props.fillerCount} pace = {props.pace} sentiment = {props.sentiment}/>
                else return <div className="analysis-item-empty">Your report will be shown here</div>
                }
            )()}
        </div>
    );
}

export default SampleAnalysis;