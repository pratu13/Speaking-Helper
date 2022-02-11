import React,{useEffect} from 'react';
import './App.css';
import Routing from './Routing';

function App() {
  useEffect(()=>{
    async function getSpeeches(){
        const response = await fetch('/test');
        console.log(response);
    }

    getSpeeches();
  },[])

  return (
    <div className="App bg-body">
      <link href='https://fonts.googleapis.com/css?family=Sen' rel='stylesheet'/>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/> 
      <header className="App-header">
		    <Routing/>
      </header>
    </div>
  );
}

export default App;