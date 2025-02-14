
import React from 'react';
import { Link } from 'react-router-dom';

const TreatmentCard = ({ treatment, onDelete, isAuthenticated }) => {

    const handleDelete = () => {
        if(window.confirm("Сигурни ли сте, че искате да изтриете тази услуга?")){
            fetch(`http://localhost:8888/api/treatments/${treatment.id}`, {
                method: 'DELETE'
            })
                .then(res => {
                    if(!res.ok) throw new Error("Грешка при изтриване");
                    return res.text();
                })
                .then(message => {
                    alert(message);
                    onDelete(); // callback за презареждане на данните
                })
                .catch(err => alert(err.message));
        }
    };

    return (
        <div className="treatment-card">
            <img src={treatment.imageUrl || 'https://via.placeholder.com/300x200?text=Treatment'} alt={treatment.name} className="treatment-image" />
            <div className="treatment-content">
                <h3>{treatment.name}</h3>
                <p>{treatment.description}</p>
                <p className="price">{treatment.price} лв.</p>
                <p className="category">
                    {treatment.category === 'manicure' ? 'Маникюр' : 'Педикюр'}
                </p>
                <Link to={`/treatment/${treatment.id}`} className="details-link">
                    Виж повече
                </Link>
                {isAuthenticated && (
                    <div className="admin-actions">
                        <Link to={`/edit-treatment/${treatment.id}`} className="edit-link">Edit</Link>
                        <button onClick={handleDelete} className="delete-button">Delete</button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default TreatmentCard;


// import React from 'react';
//
// const TreatmentCard = ({ treatment }) => {
//     return (
//         <div className="treatment-card">
//             <img src={treatment.imageUrl} alt={treatment.name} className="treatment-image" />
//             <div className="treatment-content">
//                 <h3>{treatment.name}</h3>
//                 <p>{treatment.description}</p>
//                 <p className="price">{treatment.price} лв.</p>
//                 <p className="category">
//                     {treatment.category === 'manicure' ? 'Маникюр' : 'Педикюр'}
//                 </p>
//             </div>
//         </div>
//     );
// };
//
// export default TreatmentCard;
