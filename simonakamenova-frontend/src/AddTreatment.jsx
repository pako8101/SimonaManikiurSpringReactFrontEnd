import React, { useState } from 'react';
import './css/AddTreatment.css'; // Можете да създадете отделен CSS файл

const AddTreatment = () => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        price: '',
        category: 'manicure',
        imageUrl: ''
    });
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = e => {
        e.preventDefault();
        // Основна клиентска валидация
        if (!formData.name || !formData.description || !formData.price || !formData.category) {
            setError("Моля, попълнете всички задължителни полета.");
            return;
        }
        fetch('http://localhost:8888/api/treatments', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
            .then(res => {
                if (!res.ok) throw new Error('Грешка при добавяне на услуга.');
                return res.json();
            })
            .then(data => {
                setMessage("Услугата беше добавена успешно!");
                setError('');
                setFormData({
                    name: '',
                    description: '',
                    price: '',
                    category: 'manicure',
                    imageUrl: ''
                });
            })
            .catch(err => setError(err.message));
    };

    return (
        <div className="add-treatment-container">
            <h2>Добави нова услуга</h2>
            {error && <p className="error">{error}</p>}
            {message && <p className="success">{message}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Име</label>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Описание</label>
                    <textarea name="description" value={formData.description} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Цена</label>
                    <input type="number" name="price" value={formData.price} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Категория</label>
                    <select name="category" value={formData.category} onChange={handleChange}>
                        <option value="manicure">Маникюр</option>
                        <option value="pedicure">Педикюр</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>URL на снимка</label>
                    <input type="text" name="imageUrl" value={formData.imageUrl} onChange={handleChange} />
                </div>
                <button type="submit">Добави услуга</button>
            </form>
        </div>
    );
};

export default AddTreatment;
