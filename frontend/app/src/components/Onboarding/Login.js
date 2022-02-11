import React,{useState} from 'react';
import "./Onboarding.css";
import {Animated} from "react-animated-css";
import { useNavigate } from "react-router-dom";


function Login() {
    const [show, setShow] = useState(false);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [wrongEmailInput, setWrongInput] = useState(false);
    const [wrongPasswordInput, setWrongPasswordInput] = useState(false);
    const [wrongInputError, setWrongInputError] = useState("");
    const [nameError, setNameInputError] = useState("");
    const [wrongNameInput, setNameInput] = useState(false);

    function showSignUpFields(){
        setShow(!show);
        setEmail(""); 
        setPassword("");
        setName("");
    }

    function checkValidEmail() {
        const regex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        if(!email || regex.test(email) === false){
            setWrongInputError("Please Enter valid Email");
            setWrongInput(true);
            return false;
        }
        setWrongInput(false);
        return true;
    } 

    const navigate = useNavigate();

    function handleErrors(response) {
		if (!response.ok) {
			throw Error(response.statusText);
		}
		return response;
	}
	
	const handleRoute = async() =>{
        if (show) {
            if (isInputValid()) {
                var details = name.split(' ');
                fetch(`/add_user?firstName=${details[0]}&lastName=${ details[1]}&username=${details[0]+ details[1]}&password=${password}&email=${email}`,  {
                    method: 'POST',
                    headers: {
                      'Accept': 'application/json',
                      'Content-Type': 'application/json',
                    }
                })
                .then(handleErrors)
                .then(response => {
                    setShow(false);
                })
                .catch(error => console.log(error) );
                setEmail("");
                setPassword("");
                setName("");
            }
        }
        else {
            if (isInputValid()) {
                fetch(`/email?email=${email}`, {
                    method: 'GET'
                })
                .then(handleErrors)
                .then(async response => {
                    const data = await response.json();
                    if (data.password !== password) {
                        setWrongPasswordInput(true);
                        setWrongInputError("Wrong Password, Please try again");
                        setPassword("");
                    } else {
                        setEmail(""); 
                        setPassword("");
                        navigate(`/dashboard`, {
                            state: {
                                userID: data.userId,
                                name: data.firstName
                            }
                        });
                    }
                })
                .catch(error => console.log(error) );
            }
        }
    }

    function isInputValid() {
        if (checkValidEmail()) {
            if (show) {
                if (!name) {
                    setNameInput(true);
                    setNameInputError("Please enter your name");
                    return false;
                } else {
                    setNameInput(false);
                }
            }
            if (!password) {
                setWrongPasswordInput(true);
                setWrongInputError("Please enter a password");
                return false;
            } else if (password.length <=4) {
                setWrongPasswordInput(true);
                setWrongInputError("Password must be of 8 or more characters");
                return false;
            } else {
                setWrongPasswordInput(false);
                return true;
            }
        } else {
            setWrongPasswordInput(false);
            return false;
        }
    }

    return (
       <div className="loginCard">
            {
               !show && 
               <Animated animationIn="fadeInUp" animationOut="fadeInDown" isVisible={show}>  
                <h1>Welcome,</h1>
               </Animated> 
            }
           <div className="login-items-container">
           {
                show &&  
                    <Animated animationIn="fadeInUp" animationOut="fadeInDown" isVisible={show}>     
                    <div className="name-input">
                        <label for="name"><b>Your Name</b></label>
                        {
                         wrongNameInput && <label className="wrong-text">{nameError}</label>
                        }
                        <input type="text" placeholder="" name="name" value={name} onChange={e=> setName(e.target.value)} required></input>
                    </div>  
                    </Animated> 
            }
            <div className="email-input">
                <label for="email"><b>Your email</b></label>
                {
                    wrongEmailInput && <label className="wrong-text">{wrongInputError}</label>
                }
                <input type="text" placeholder="" name="email" value={email} onChange={e=> setEmail(e.target.value)} required></input>
            </div>   
            <div className="password-input">
                <label for="password"><b>Your Password</b></label>
                {
                    wrongPasswordInput && <label className="wrong-text">{wrongInputError}</label>
                }
            <input type="password" placeholder="" name="password" value={password} onChange={e=> setPassword(e.target.value)} required></input>
            </div>
            <button className="theme-btn onboarding-btn" onClick={handleRoute}>
                {
                show && "Sign Up"
                }
                {
                !show && "Login"
                }
                </button> 
           </div>
           <div className="sign-up-option">
                { show &&  "Already a user?"}
                { !show &&  "Not a user?"}
                
                { !show && <a className="sign-up" onClick={showSignUpFields}> Create Account</a>}
                { show && <a className="sign-up" onClick={showSignUpFields}> Login</a>}
           </div>
       </div> 
    );
}

export default Login;