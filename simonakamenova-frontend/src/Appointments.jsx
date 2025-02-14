import React, { useState, useEffect } from 'react';
import './css/Appointments.css';

const Appointments = () => {
    const [appointments, setAppointments] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetch('http://localhost:8888/api/appointments')
            .then(res => {
                if(!res.ok) throw new Error("Грешка при зареждане на appointment-и");
                return res.json();
            })
            .then(data => setAppointments(data))
            .catch(err => setError(err.message));
    }, []);

    return (
        <div className="appointments-container">
            <h2>Всички Запазени Часове</h2>
            {error && <p className="error">{error}</p>}
            {appointments.length === 0 ? (
                <p>Няма намерени запазени часове.</p>
            ) : (
                <table className="appointments-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Име</th>
                        <th>Телефон</th>
                        <th>Дата и час</th>
                        <th>Вид услуга</th>
                        <th>Бележки</th>
                    </tr>
                    </thead>
                    <tbody>
                    {appointments.map(app => (
                        <tr key={app.id}>
                            <td>{app.id}</td>
                            <td>{app.customerName}</td>
                            <td>{app.customerPhone}</td>
                            <td>{new Date(app.appointmentDate).toLocaleString()}</td>
                            <td>{app.treatmentType === 'manicure' ? 'Маникюр' : 'Педикюр'}</td>
                            <td>{app.notes}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default Appointments;
