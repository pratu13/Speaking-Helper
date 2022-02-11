import React from "react";

function WordButton(props) {

    return (
        <div className="pronounciation-button-container">
            <div className="pronounciation-button-info-container">
                <div className="pronounciation-button-text">{props.word}</div>
            </div>
        </div>
        
    );
}

export default WordButton;