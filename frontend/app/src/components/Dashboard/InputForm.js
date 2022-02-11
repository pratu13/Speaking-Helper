import React,{useState} from 'react';

function InputForm(props){
    const[textFile, setTextFile] = useState();
	const[textArea, setTextArea] = useState();
    const[speechFile, setSpeechFile] = useState();	
	const API = "https://speech-helper-backend.herokuapp.com"; 

    function handleErrors(response) {
		if (!response.ok) {
			throw Error(response.statusText);
		}
		return response;
	}
	
	const generateReport = async() => {
        props.updateLoading(true);
        const formData = new FormData();
        formData.append("files",textFile);
        formData.append("files",speechFile);
        console.log(props.userId)
		fetch(`/createSpeech?userId=${props.userId}`, {
            method: "POST",
            body: formData, 
            headers: {
            'Access-Control-Allow-Origin':'*',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS'
                }	
            }
        )
		.then(handleErrors)
		.then(async response => {
            const data = await response.json();
            console.log(data)
            props.reloadHistory();
            props.updateLoading(false);
           
		})
		.catch(error => console.log(error) );
	}
    
    const handleSubmit = async(e) => {
        e.preventDefault();
        if (!props.userLoggedIn) {
            props.updateLoading(true);
            const formData = new FormData();
            formData.append("files",textFile);
            formData.append("files",speechFile);
            const response = await fetch(`/createSpeechWelcomePage`, {method: "post",body: formData, headers: {'Access-Control-Allow-Origin':'*'}});
            const json = await response.json();
            props.changeReport();
            props.update(json.FillerRatio,json.SpeechRate, json.Sentiment);
            props.updateLoading(false);
        }
        if (props.userLoggedIn){
            await generateReport();
            
        }
    }
    
    return(
        <form className="input-form" onSubmit={e => { handleSubmit(e) }}>
			<div>
			{(() => {
                    if (textFile == null){
                        return (
                            <div>
                                <textarea id="story" name="story" rows="5" cols="33" onChange={e => setTextArea(e.target.value)}> Type your text ... </textarea>
                            </div> 
                        );
                    }
			    }
            )()}
			</div>
			<div>
			{(() => {
                    if(textFile == null && textArea == null){
                        return(
                            <p>or</p>
                        );
                    }
			    }
			)()}
			</div>
            <div>
            {(() => {
                if (textArea == null) {
                    return (
                        <div>
                            <input type="file" name="file" id="file" className="inputfile" onChange={e=> setTextFile(e.target.files[0])} required/>
                            <label for="file" className="uploadButton">
                            {(() => {
                                if (textFile != null) return "Uploaded";
                                else return "Upload a text file";
                                }
                            )()}
                            </label>
                        </div>
                    );
                }
            }
            )()}
            </div>
            <br/>
            <div>
            {(() => {
                    return (
                        <div>
                            <input type="file" name="file1" id="file1" className="inputfile" onChange={e=> setSpeechFile(e.target.files[0])} required/>
                            <label for="file1" className="uploadButton">
                            {(() => {
                                if (speechFile != null) return "Uploaded";
                                else return "Upload an audio file";
                                }
                            )()}</label>
                            {(() => {
                                if (speechFile == null && props.userLoggedIn) return (
                                    <div>
                                        <p>or</p>
                                         <label className="uploadButton" onClick={(e) => props.showRecordAudioModal(true)}>Record Live Audio</label>
                                    </div>
                                ) 
                                }
                            )()}
                        </div>
                    );
            }
            )()}
            </div>
            <br/>
            <input className="theme-btn generate-btn"  type='submit' value='Generate'/>
    </form>
    );
}

export default InputForm;