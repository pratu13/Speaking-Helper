import React from "react";

function GrammarFeedbackItem(props) {
    return (
        <div className="grammar-item">
            <div className="incorrect-grammar">{props.incorrect}</div>
            <div className="correct-grammar">{props.correct}</div>
        </div>
    );
} 

export default GrammarFeedbackItem;