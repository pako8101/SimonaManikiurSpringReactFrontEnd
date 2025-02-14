import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './Manicure.css';

const Manicure = () => {
    const [treatments, setTreatments] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8888/api/treatments/manicure')
            .then(response => response.json())
            .then(data => setTreatments(data))
            .catch(error => console.error('Грешка при зареждане на маникюр услугите:', error));
    }, []);

    return (
        <div className="manicure-container">
            <h2>Видове Маникюр</h2>
            <div className="manicure-grid">
                {treatments.map(treatment => (
                    <div key={treatment.id} className="manicure-card">
                        <img
                            src={treatment.imageUrl || 'https://via.placeholder.com/300x200?text=Маникюр'}
                            alt={treatment.name}
                            className="manicure-image"
                        />
                        <div className="manicure-content">
                            <h3>{treatment.name}</h3>
                            <p>{treatment.description}</p>
                            <p className="price">{treatment.price} лв.</p>
                            <Link to={`/treatment/${treatment.id}`} className="details-link">
                                Виж повече
                            </Link>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Manicure;
