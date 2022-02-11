import React from "react";
import temp from "./Assets/temp.png"
import progress from "./Assets/progress.png"
import help from "./Assets/help.png"
import { useNavigate } from "react-router-dom";

function Header(props) {

  const navigate = useNavigate();
	
	  const handleRoute = () =>{
		console.log("This needs to actually check login info before routing. Otherwise display an error message");
		navigate('/login')
    }

    return(
        <header>
          <div className = "nav-bar-container">
                    <div className="profile-info" onClick={() => props.reset(false)}>
                        <div className = "header-image">
                            <img src={temp} alt="header-image" className="avatar"/>
                        </div>
                        <div className="profile">
                            <p class="name">{props.name}</p>
                            <p class="occupation">Student</p>
                        </div>
                    </div>
                    <nav>
                      {
                        !props.showHelp && 
                        // <Animated animationIn="fadeInUp" animationOut="fadeInDown" isVisible={!props.showHelp}>  
                            <div className="header-btn" onClick={() => props.changeHelp(true)}>
                              <div className="header-btn-title">Help</div>
                              <img src ={help} alt="header-image" className="header-btn-image"/>
                            </div>
                        //  </Animated>
                      } 
                      {
                        !props.showProgress && 
                        // <Animated animationIn="fadeInUp" animationOut="fadeInDown" isVisible={!props.showProgress}>  
                          <div className="header-btn" onClick={() => props.changeProgress(true)}> 
                            <div className="header-btn-title">My Progress</div>
                            <img src ={progress} alt="header-image" className="header-btn-image"/>
                          </div>
                         /* </Animated>   */
                      }
                      <button className="theme-btn" onClick={handleRoute} >Logout</button>
                      
                    </nav>
                      
          </div>
		    </header>
    )
}

export default Header;