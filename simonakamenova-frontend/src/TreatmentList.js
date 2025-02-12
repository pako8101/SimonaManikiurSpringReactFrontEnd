import React, { useState, useEffect } from 'react';
import TreatmentCard from './TreatmentCard';

const TreatmentList = () => {
    const [treatments, setTreatments] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/treatments')
            .then(response => response.json())
            .then(data => setTreatments(data))
            .catch(error => console.error('Грешка при зареждане на услугите:', error));
    }, []);

    return (
        <div className="treatment-list">
            {treatments.map(treatment => (
                <TreatmentCard key={treatment.id} treatment={treatment} />
            ))}
        </div>
    );
};

export default TreatmentList;
