import React,{useEffect, useState} from 'react';
import './CSS/Dashboard.css';
import FamousSpeeches from './FamousSpeeches';
import Header from './Header'
import HistoryReport from './HistorySpeech';
import InputForm from './InputForm';
import ReadText from './ReadText';
import {Animated} from "react-animated-css";
import ProgressChart from './ProgressChart';
import LiveAudioRecord from './LiveAudioRecord';
import { useLocation } from 'react-router-dom';
import LoadingIndicator from './LoadingIndicatory';
import EmptyProgress from './EmptyProgress';


function Dashboard(){
	const location = useLocation();
	const { userID, name } = location.state;
	const [showHelp, setShow] = useState(false);
	const [showProgress, setProgress] = useState(false);
	const [speechText, setSpeech] = useState("Click the Play icon, the transcript for the speech will be shown here") 
	const [showRecordLiveModal, setRecordLiveModal] = useState(false);
	const [isloading, setLoading] = useState(false);
	const [historySpeeches, setHistorySpeeches] = useState({});
	const [progressData, setProgressData] = useState([])
	const months = ["Speech 1", "Speech 2", "Speech 3", "Jun", "Jul", "Aug", "Sep", "Aug", "Sep", "Oct", "Nov", "Dec"]
	const [yProgress, setY] = useState([])

	const updateSpeech = (newSpeech) => {
		setSpeech(newSpeech)
	}

	const updateProgress = (y) => {
		const temp = [...yProgress]
		temp.push(y)
		console.log(temp)
		setY(temp);
		console.log(yProgress)
		getProgressData()
	}

	const updateLoading = (show) => {
		setLoading(show)
	}
	
	const reloadHistory = () => {
		console.log("Reload history");
		getHistoryReport()
	} 

	useEffect(() => getHistoryReport(), []);

	const showRecordAudioModal = (show) => {
		setRecordLiveModal(show);
	}

	const App = () => {
  
		const [showModal, setShowModal] = useState(false);
	  
		const buttonClicked = (show) => {
		  setShowModal(show);
		}
		
		return (
		  <div>
			<div className="App">
			  <h1>Let's Make Modals!</h1>
			  <Instructions/>
			  <ModalButton onClick={() => buttonClicked(true)} />
			  <Modal show = {showModal} buttonClicked={buttonClicked} />
			</div> 
		  </div> 
		)
	}

	const changeHelp = (show) => {
		setShow(show);
		setProgress(!show);
	}

	const reset = (show) => {
		setProgress(show);
		setShow(show);
	}

	const changeProgress = (show) => {
	    setProgress(show);
		setShow(!show);
	}

	function handleErrors(response) {
		if (!response.ok) {
			throw Error(response.statusText);
		}
		return response;
	}
	
	const getHistoryReport = async() => {
		fetch(`/speech_user_id?userId=${userID}`, {
			method: 'GET'
		})
		.then(handleErrors)
		.then(async response => {
			const speeches = await response.json();
			setHistorySpeeches(speeches)
			
		})
		.catch(error => console.log(error) );
	}

	const getProgressData = () => {
		// get this Y from backend
		yProgress.forEach((score, index) => {
			progressData.push({label: months[index], x: index, y: score})
		});
		console.log(progressData)
		setProgressData(progressData)
	}

	return(
		<div> 
			{ showRecordLiveModal && <LiveAudioRecord showRecordAudioModal = {showRecordAudioModal}/> }
			{ isloading && <LoadingIndicator/> }
			<Header name = {name} changeHelp={changeHelp} showHelp={showHelp} changeProgress = {changeProgress}  showProgress = {showProgress} reset = {reset}/> 
			<div className="mainContainer">
				
				<div className= "bgCard">
					<div className="dashboard-container">
						{
							!showProgress && !showHelp && 
							<div className="dashboard-container-child">
								<InputForm userId = {userID} reloadHistory = {reloadHistory} showRecordLiveModal = {showRecordLiveModal} showRecordAudioModal = {showRecordAudioModal} userLoggedIn = {true} updateLoading = {updateLoading}/>	
								<HistoryReport name = {name} userId ={userID} historySpeeches = {historySpeeches} updateProgress = {updateProgress}/>
								
							</div>
						}
						{
							!showProgress && showHelp && 
							<div className="dashboard-container-child">
								<Animated animationIn="fadeOut" animationOut="fadeIn" isVisible={!showHelp}>  
									<ReadText text = {speechText}/>
								</Animated> 
								{/* <Animated animationIn="fadeOut" animationOut="fadeIn" isVisible={!showHelp}>   */}
									<FamousSpeeches updateSpeech = {updateSpeech}/>
								{/* </Animated>  */}
							</div>
						}
						{
						  showProgress &&
							<Animated animationIn="fadeOut" animationOut="fadeIn" isVisible={!showProgress}>  
								<div className="progress-main-container">
									{(() => {
											if (Object.keys(historySpeeches).length === 0) {
												return  (
													<div className='empty-progress-container'>
														<EmptyProgress/>
													</div>
												); 
											} else {
												return (
													<div>
														<div className="progress-chart-header">
															<div className="progress-chart-title">Progress Chart (Overall Score vs Time)</div>
															<div className="progress-chart-subtitle">This chart shows your progress overtime. With this you can keep track of how well are you doing each month</div>
														</div>
														<div className="progress-container">
															<ProgressChart data={progressData} width = {500} height= {300} horizontalGuides={5} precision={0} color="#5744AB"/>
														</div>
													</div>
												);

											}
									})()}
								</div>	
							</Animated> 
						}
					</div>
				</div>
			</div>
		</div>
	);
}

export default Dashboard;