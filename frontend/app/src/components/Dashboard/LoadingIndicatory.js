import React from "react";

function LoadingIndicator() {
    return (
        <div className="modal-main-container">
            <div className="audio-record-bg-container">
                <div className="loading-container">
                    <div className="audio-record-title"> Generating your report. Please Wait! </div>
                    <Loading/>
                </div>
            </div>
        </div>
    );
}

function Loading() {
    return (
        <div class="loading-spinner">
                <div class="bounce1"></div>
                <div class="bounce2"></div>
        </div>
    );

}
export default LoadingIndicator;