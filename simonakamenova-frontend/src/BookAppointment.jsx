import React, { useState } from 'react';
import './BookAppointment.css'; // Отделен CSS файл

const BookAppointment = () => {
    const [formData, setFormData] = useState({
        customerName: '',
        customerPhone: '',
        appointmentDate: '',
        treatmentType: 'manicure',
        notes: ''
    });
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = e => {
        e.preventDefault();
        // Валидация: всички задължителни полета
        if (!formData.customerName || !formData.customerPhone || !formData.appointmentDate || !formData.treatmentType) {
            setError("Моля, попълнете всички задължителни полета.");
            return;
        }
        fetch('http://localhost:8888/api/appointments', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
            .then(res => {
                if (!res.ok) return res.text().then(text => { throw new Error(text); });
                return res.json();
            })
            .then(data => {
                setMessage("Вашият час беше запазен успешно!");
                setError('');
                setFormData({
                    customerName: '',
                    customerPhone: '',
                    appointmentDate: '',
                    treatmentType: 'manicure',
                    notes: ''
                });
            })
            .catch(err => setError(err.message));
    };

    return (
        <div className="book-appointment-container">
            <h2>Запази час</h2>
            {error && <p className="error">{error}</p>}
            {message && <p className="success">{message}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Име</label>
                    <input type="text" name="customerName" value={formData.customerName} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Телефон</label>
                    <input type="text" name="customerPhone" value={formData.customerPhone} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Дата и час</label>
                    <input type="datetime-local" name="appointmentDate" value={formData.appointmentDate} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Вид услуга</label>
                    <select name="treatmentType" value={formData.treatmentType} onChange={handleChange}>
                        <option value="manicure">Маникюр</option>
                        <option value="pedicure">Педикюр</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Бележки</label>
                    <textarea name="notes" value={formData.notes} onChange={handleChange}></textarea>
                </div>
                <button type="submit">Запази час</button>
            </form>
        </div>
    );
};

export default BookAppointment;
