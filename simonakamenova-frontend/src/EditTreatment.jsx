import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './css/EditTreatment.css';

const EditTreatment = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [treatment, setTreatment] = useState({
        name: '',
        description: '',
        price: '',
        category: '',
        imageUrl: ''
    });
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    useEffect(() => {
        fetch(`http://localhost:8888/api/treatments/${id}`)
            .then(res => {
                if(!res.ok) throw new Error("Не може да се зареди услугата");
                return res.json();
            })
            .then(data => setTreatment(data))
            .catch(err => setError(err.message));
    }, [id]);

    const handleChange = (e) => {
        setTreatment({ ...treatment, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch(`http://localhost:8888/api/treatments/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(treatment)
        })
            .then(res => {
                if(!res.ok) return res.text().then(text => { throw new Error(text) });
                return res.json();
            })
            .then(data => {
                setMessage("Услугата е обновена успешно!");
                setTimeout(() => {
                    navigate('/');
                }, 2000);
            })
            .catch(err => setError(err.message));
    };

    return (
        <div className="edit-treatment-container">
            <h2>Редактиране на услуга</h2>
            {error && <p className="error">{error}</p>}
            {message && <p className="success">{message}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Име</label>
                    <input type="text" name="name" value={treatment.name} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Описание</label>
                    <textarea name="description" value={treatment.description} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Цена</label>
                    <input type="number" name="price" value={treatment.price} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Категория</label>
                    <select name="category" value={treatment.category} onChange={handleChange}>
                        <option value="manicure">Маникюр</option>
                        <option value="pedicure">Педикюр</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>URL на снимка</label>
                    <input type="text" name="imageUrl" value={treatment.imageUrl} onChange={handleChange} />
                </div>
                <button type="submit">Обнови услуга</button>
            </form>
        </div>
    );
};

export default EditTreatment;
