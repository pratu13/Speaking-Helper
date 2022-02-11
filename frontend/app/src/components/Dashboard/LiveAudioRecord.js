import React, { useEffect, useState } from "react";


function LiveAudioRecord(props) {

    const [seconds, setSeconds] = useState(3);

    useEffect(() => {
        if (seconds > 0) {
          setTimeout(() => setSeconds(seconds - 1), 1000);
        }
    });

    const stop = () => {
        //save the audio and trigger the generate button event
        props.showRecordAudioModal(false);
    }

    return (
        <div className="modal-main-container">
            <div className="audio-record-bg-container"  onClick ={(e) => props.showRecordAudioModal(false)}/>
            <div className="audio-record-container">
                <div className="audio-record-title">
                {(() => {
                    if (seconds !== 0) return "you can speak in"
                    else return "speak now"
                }
                )()}
                </div>
                {(() => {
                    if (seconds !== 0) return (<div className="audio-record-seconds">{seconds}</div>);
                    else return (
                    <div>
                    <Record/>
                    <button className="theme-btn generate-btn" onClick={stop}>Stop Recording</button>
                    </div>
                        );

                    }
                )()}
            </div>
        </div> 
    );



};

function Record() {
    return (
        <div class="loading-spinner">
                <div class="bounce1"></div>
                <div class="bounce2"></div>
        </div>
    );

}

export default LiveAudioRecord;