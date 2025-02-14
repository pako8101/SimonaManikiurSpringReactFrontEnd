
import React from 'react';
import './css/Home.css';

function Home() {
    return (
        <div className="home-container">
            {/* Hero секция с фонова картинка и анимиран текст */}
            <section className="hero-section">
                <div className="hero-overlay">
                    <h1>Симона Каменова</h1>
                    <h2>Маникюр и Педикюр</h2>
                    <p>Открийте красотата във всеки детайл!</p>
                    <a href="/book-appointment" className="cta-button">
                        Запази час
                    </a>
                </div>
            </section>

            {/* Секция за представяне на услугите */}
            <section className="services-section">
                <div className="service">
                    <img
                        src="https://via.placeholder.com/400x300?text=Маникюр"
                        alt="Маникюр"
                    />
                    <h3>Маникюр</h3>
                    <p>Професионален маникюр, който подчертава вашата индивидуалност.</p>
                    <a href="/manicure" className="service-link">Научете повече</a>
                </div>
                <div className="service">
                    <img
                        src="https://via.placeholder.com/400x300?text=Педикюр"
                        alt="Педикюр"
                    />
                    <h3>Педикюр</h3>
                    <p>Луксозен педикюр за крака, които заслужават внимание.</p>
                    <a href="/pedicure" className="service-link">Научете повече</a>
                </div>
            </section>

            {/* Секция "За нас" */}
            <section className="about-section">
                <h2>За Симона Каменова</h2>
                <p>
                    Симона Каменова предлага висококачествени услуги в областта на
                    маникюра и педикюра с внимание към детайла и индивидуален подход към всеки клиент.
                    Абсолютно без компромис в качеството на материалите!
                    Открийте своя нов стил и красота!
                </p>
            </section>
        </div>
    );
}

export default Home;



// import React, { useState, useEffect } from 'react';
// import TreatmentCard from './TreatmentCard';
//
// function Home() {
//     const [treatments, setTreatments] = useState([]);
//
//     useEffect(() => {
//         fetch('http://localhost:8888/api/treatments')
//             .then(response => response.json())
//             .then(data => setTreatments(data))
//             .catch(error => console.error('Грешка при зареждане на услугите:', error));
//     }, []);
//
//     return (
//         <div className="treatment-list">
//             {treatments.map(treatment => (
//                 <TreatmentCard key={treatment.id} treatment={treatment} />
//             ))}
//         </div>
//     );
// }
//
// export default Home;
