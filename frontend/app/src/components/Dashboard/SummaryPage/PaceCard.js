import React from "react";
import Fastpace from "../Assets/fastpace.png"
import Slowpace from "../Assets/slowPace.png"
import Normalpace from "../Assets/normalPace.png"

function PaceCard(props) {
    return (
        <div className="pace-container">
            <div className="pace-info-container">
                <div className="pace-info-child-container">
                    <img className={props.pace > 170 ? "pace-info-transform pace-info-image" : "pace-info-image"} src={(() => {
                        if (props.pace > 170) return Fastpace;
                        else if (props.pace < 110 )return Slowpace;
                        else return  Normalpace;
                        }
                    )()} alt="header image"/>
                    <div className={(() => {
                        if (props.pace > 170) return "text-red pace-info-text";
                        else if (props.pace < 110 )return "text-red pace-info-text";
                        else return  "text-green pace-info-text";
                        }
                    )()}>{props.pace}</div>
                    <div className="pace-info-child-container1">
                        <div className={(() => {
                        if (props.pace > 170) return "text-red pace-info-subtext";
                        else if (props.pace < 110 )return "text-red pace-info-subtext";
                        else return  "text-green pace-info-subtext";
                        }
                    )()}>wpm</div>
                    </div>
                </div>
                <div className="pace-info-message">
                    {(() => {
                        if (props.pace > 170)return "your pace was too fast";
                        else if (props.pace < 110 )return "your pace was too slow";
                        else return  "your pace was normal";
                        }
                    )()}
                </div>
            </div>
        </div>
    );
}

export default PaceCard;