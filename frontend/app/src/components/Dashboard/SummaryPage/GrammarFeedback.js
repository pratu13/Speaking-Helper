import React from "react";
import GrammarImage from "../Assets/grammar.png"
import GrammarFeedbackItem from "./GrammarFeedBackItem";
import Correct from "../Assets/correct.png"

function GrammarFeedback(props) {
    return (
        <div className="grammar-feed-container">
            <div className="grammar-feed-flex">
                <div className="grammar-feed-header">
                    <div className="grammar-feed-header-text">
                        {(() => {
                            if (Object.keys(props.incorrect_correct).length === 0) return "There were no grammar or spelling mistakes! Good job!";
                            else return "These are the spelling mistakes that we found";
                        }
                        )()}
                        </div>
                    <img className="card-header-image" src=
                     {(() => {
                        if (Object.keys(props.incorrect_correct).length === 0) return Correct;
                        else return GrammarImage;
                    }
                    )()} alt ="Header Image"/>
                </div>
                <div className="grammar-items-container">
                    {
                        Object.keys(props.incorrect_correct).map((key, index) => ( 
                            <GrammarFeedbackItem correct = {props.incorrect_correct[key]} incorrect = {key}/> 
                        ))
                    }
                </div>
                <div>
                    {(() => {
                        if (Object.keys(props.incorrect_correct).length > 3) {
                            return (
                            <div className="view-all-btn-frame" onClick={(e) => {props.didTapViewAll(!props.showAllGrammar)}}>
                                <div className="view-all-btn">
                                {(() => {
                                    if (!props.showAllGrammar) return "View All"
                                    else return "View Less"
                                }
                                )()}
                                </div>
                            </div>  
                            );
                        }
                    }
                    )()}
                </div> 
            </div>
        </div>


    );
}

export default GrammarFeedback;