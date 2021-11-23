import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
    const toto = () => {
        console.log('qaaaaa');
    };

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    Edit <code>src/App.tsx</code> and save to reload.
                </p>
                P
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
                <button onClick={toto}>aaaa</button>
            </header>
        </div>
    );
}

export default App;
