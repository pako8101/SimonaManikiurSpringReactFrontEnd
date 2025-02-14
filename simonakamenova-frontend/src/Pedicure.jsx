import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './css/Pedicure.css';

const Pedicure = () => {
    const [treatments, setTreatments] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8888/api/treatments/pedicure')
            .then(response => response.json())
            .then(data => setTreatments(data))
            .catch(error => console.error('Грешка при зареждане на педикюр услугите:', error));
    }, []);

    return (
        <div className="pedicure-container">
            <h2>Видове Педикюр</h2>
            <div className="pedicure-grid">
                {treatments.map(treatment => (
                    <div key={treatment.id} className="pedicure-card">
                        <img
                            src={treatment.imageUrl || 'https://via.placeholder.com/300x200?text=Педикюр'}
                            alt={treatment.name}
                            className="pedicure-image"
                        />
                        <div className="pedicure-content">
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

export default Pedicure;
