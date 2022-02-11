import React from "react";
import { useNavigate } from "react-router-dom";
import './Welcome.css';

function WelcomeHeader() {
	
	const navigate = useNavigate();
	
	  const handleRoute = () =>{
		console.log("hi") 
		navigate('/login')
  }
    return(
    <header>
			<div className = "nav-bar-container">
          <div className="profile-info">
            <div className="welcome-page-title-text-container">
                <div className="welcome-page-title">Welcome to Speech Helper</div>
                <div className="welcome-page-subtitle">This page contains a demo of speech analysis. For details and tracking over time
                <br/>
                Login or Signup now!!</div>
            </div>
           
          </div>
          <nav>
				    <button className="theme-btn" onClick={handleRoute}>Log In</button>
            <a className="secondary-theme-btn" onClick={handleRoute}>Sign up</a>
          </nav>
			</div>
		</header>
    )
}

export default WelcomeHeader;