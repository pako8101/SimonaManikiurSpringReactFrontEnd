import React from 'react';

const TreatmentCard = ({ treatment }) => {
    return (
        <div className="treatment-card">
            <img src={treatment.imageUrl} alt={treatment.name} className="treatment-image" />
            <div className="treatment-content">
                <h3>{treatment.name}</h3>
                <p>{treatment.description}</p>
                <p className="price">{treatment.price} лв.</p>
                <p className="category">
                    {treatment.category === 'manicure' ? 'Маникюр' : 'Педикюр'}
                </p>
            </div>
        </div>
    );
};

export default TreatmentCard;
