

import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import Register from './Register';
import Manicure from './Manicure';
import Pedicure from './Pedicure';
import AddTreatment from './AddTreatment';
import EditTreatment from './EditTreatment';
import BookAppointment from './BookAppointment';
import Appointments from './Appointments';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

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
                    <nav className="navbar">
                        <Link to="/">Начало</Link>
                        <Link to="/manicure">Маникюр</Link>
                        <Link to="/pedicure">Педикюр</Link>
                        <Link to="/book-appointment">Запази час</Link>
                        <Link to="/appointments">Запазени часове</Link>
                        {!isAuthenticated && <Link to="/add-treatment">Добави услуга</Link>}
                        {!isAuthenticated && <Link to="/login">Вход</Link>}
                        {!isAuthenticated && <Link to="/register">Регистрация</Link>}
                        {isAuthenticated && <button onClick={handleLogout}>Изход</button>}
                    </nav>
                </header>
                <main>
                    <Routes>
                        <Route path="/" element={isAuthenticated ? <Home /> : <Navigate to="/login" replace />} />
                        <Route path="/login" element={isAuthenticated ? <Navigate to="/" replace /> : <Login onLogin={() => setIsAuthenticated(true)} />} />
                        <Route path="/register" element={isAuthenticated ? <Navigate to="/" replace /> : <Register />} />
                        <Route path="/manicure" element={<Manicure />} />
                        <Route path="/pedicure" element={<Pedicure />} />
                        <Route path="/add-treatment" element={!isAuthenticated ? <AddTreatment /> : <Navigate to="/login" replace />} />
                        <Route path="/edit-treatment/:id" element={!isAuthenticated ? <EditTreatment /> : <Navigate to="/login" replace />} />
                        <Route path="/book-appointment" element={<BookAppointment />} />
                        <Route path="/appointments" element={<Appointments />} />
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




//
// import React, { useState, useEffect } from 'react';
// import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
// import Home from './Home';
// import Login from './Login';
// import Register from './Register';
// import Manicure from './Manicure';
// import Pedicure from './Pedicure';
// import AddTreatment from './AddTreatment';
// import BookAppointment from './BookAppointment';
//
// function App() {
//     const [isAuthenticated, setIsAuthenticated] = useState(false);
//
//     useEffect(() => {
//         const token = localStorage.getItem('token');
//         setIsAuthenticated(!!token);
//     }, []);
//
//     const handleLogout = () => {
//         localStorage.removeItem('token');
//         setIsAuthenticated(false);
//     };
//
//     return (
//         <Router>
//             <div className="app-container">
//                 <header className="header">
//                     <h1>Симона Каменова</h1>
//                     <h2>Маникюр и Педикюр</h2>
//                     <nav className="navbar">
//                         <Link to="/">Начало</Link>
//                         <Link to="/manicure">Маникюр</Link>
//                         <Link to="/pedicure">Педикюр</Link>
//                         <Link to="/book-appointment">Запази час</Link>
//                         {!isAuthenticated && <Link to="/add-treatment">Добави услуга</Link>}
//                         {!isAuthenticated && <Link to="/login">Вход</Link>}
//                         {!isAuthenticated && <Link to="/register">Регистрация</Link>}
//                         {isAuthenticated && <button onClick={handleLogout}>Изход</button>}
//                     </nav>
//                 </header>
//                 <main>
//                     <Routes>
//                         <Route path="/" element={isAuthenticated ? <Home /> : <Navigate to="/login" replace />} />
//                         <Route path="/login" element={isAuthenticated ? <Navigate to="/" replace /> : <Login onLogin={() => setIsAuthenticated(true)} />} />
//                         <Route path="/register" element={isAuthenticated ? <Navigate to="/" replace /> : <Register />} />
//                         <Route path="/manicure" element={<Manicure />} />
//                         <Route path="/pedicure" element={<Pedicure />} />
//                         <Route path="/add-treatment" element={<AddTreatment />} />
//                         {/*<Route path="/add-treatment" element={isAuthenticated ? <AddTreatment /> : <Navigate to="/login" replace />} />*/}
//                         <Route path="/book-appointment" element={<BookAppointment />} />
//                     </Routes>
//                 </main>
//                 <footer className="footer">
//                     <p>&copy; {new Date().getFullYear()} Симона Каменова. Всички права запазени.</p>
//                 </footer>
//             </div>
//         </Router>
//     );
// }
//
// export default App;
//
