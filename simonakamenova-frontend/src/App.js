import React from 'react';
import TreatmentList from './components/TreatmentList';

function App() {
    return (
        <div className="App">
            <header className="header">
                <h1>Симона Каменова</h1>
                <h2>Маникюр и Педикюр</h2>
            </header>
            <main>
                <TreatmentList />
            </main>
            <footer className="footer">
                <p>&copy; {new Date().getFullYear()} Симона Каменова. Всички права запазени.</p>
            </footer>
        </div>
    );
}

export default App;
