import TreatmentList from './TreatmentList';
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import Register from './Register';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    // Проверка дали има token в localStorage
    useEffect(() => {
        const token = localStorage.getItem('token');
        setIsAuthenticated(!!token);
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
    };

    return (
        <Router>
            <div className="app-container">
                <header className="header">
                    <h1>Симона Каменова</h1>
                    <h2>Маникюр и Педикюр</h2>
                    <main>
                        <TreatmentList/>
                    </main>
                    <nav className="navbar">
                        <Link to="/">Начало</Link>
                        {!isAuthenticated && <Link to="/login">Вход</Link>}
                        {!isAuthenticated && <Link to="/register">Регистрация</Link>}
                        {isAuthenticated && <button onClick={handleLogout}>Изход</button>}
                    </nav>
                </header>
                <main>
                <Routes>
                        <Route
                            path="/"
                            element={isAuthenticated ? <Home /> : <Navigate to="/login" replace />}
                        />
                        <Route
                            path="/login"
                            element={isAuthenticated ? <Navigate to="/" replace /> : <Login onLogin={() => setIsAuthenticated(true)} />}
                        />
                        <Route
                            path="/register"
                            element={isAuthenticated ? <Navigate to="/" replace /> : <Register />}
                        />
                    </Routes>
                </main>
                <footer className="footer">
                    <p>&copy; {new Date().getFullYear()} Симона Каменова. Всички права запазени.</p>
                </footer>
            </div>
        </Router>
    );
}

export default App;




