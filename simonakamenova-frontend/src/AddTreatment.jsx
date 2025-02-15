import React, { useState } from 'react';
import './css/AddTreatment.css';

const AddTreatment = () => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        price: '',
        category: 'manicure',
        imageUrl: ''  // Ще се сетне след качване на файл
    });
    const [uploadMessage, setUploadMessage] = useState('');
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    // Обработка на текстовите полета
    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    // Обработка на избора на файл
    const handleFileChange = e => {
        const file = e.target.files[0];
        if (!file) return;
        const data = new FormData();
        data.append("file", file);

        // Изпращане към бекенда за качване на файл
        fetch('http://localhost:8888/api/treatments/upload', {
            method: 'POST',
            body: data
        })
            .then(res => {
                if (!res.ok) throw new Error("Грешка при качване на снимка.");
                return res.text(); // Очакваме URL като текстов отговор
            })
            .then(url => {
                setFormData({ ...formData, imageUrl: url });
                setUploadMessage("Снимката беше качена успешно!");
            })
            .catch(err => {
                console.error(err);
                setError("Грешка при качване на снимката.");
            });
    };

    // Обработка на формата за добавяне на Treatment
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
                if (!res.ok) throw new Error("Грешка при добавяне на услугата.");
                return res.json();
            })
            .then(data => {
                setMessage("Услугата беше добавена успешно!");
                setError('');
                // Изчистване на формата
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
            {uploadMessage && <p className="success">{uploadMessage}</p>}
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
                    <label>Качи снимка</label>
                    <input type="file" onChange={handleFileChange} accept="image/*" />
                    {formData.imageUrl && (
                        <img
                            src={formData.imageUrl}
                            alt="Treatment"
                            style={{ width: "200px", marginTop: "10px" }}
                        />
                    )}
                </div>
                <button type="submit">Добави услуга</button>
            </form>
        </div>
    );
};

export default AddTreatment;


// import React, { useState } from 'react';
// import './css/AddTreatment.css'; // Можете да създадете отделен CSS файл
//
// const AddTreatment = () => {
//     const [formData, setFormData] = useState({
//         name: '',
//         description: '',
//         price: '',
//         category: 'manicure',
//         imageUrl: ''
//     });
//     const [message, setMessage] = useState('');
//     const [error, setError] = useState('');
//
//     const handleChange = e => {
//         setFormData({ ...formData, [e.target.name]: e.target.value });
//     };
//
//     const handleSubmit = e => {
//         e.preventDefault();
//         // Основна клиентска валидация
//         if (!formData.name || !formData.description || !formData.price || !formData.category) {
//             setError("Моля, попълнете всички задължителни полета.");
//             return;
//         }
//         fetch('http://localhost:8888/api/treatments', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify(formData)
//         })
//             .then(res => {
//                 if (!res.ok) throw new Error('Грешка при добавяне на услуга.');
//                 return res.json();
//             })
//             .then(data => {
//                 setMessage("Услугата беше добавена успешно!");
//                 setError('');
//                 setFormData({
//                     name: '',
//                     description: '',
//                     price: '',
//                     category: 'manicure',
//                     imageUrl: ''
//                 });
//             })
//             .catch(err => setError(err.message));
//     };
//
//     return (
//         <div className="add-treatment-container">
//             <h2>Добави нова услуга</h2>
//             {error && <p className="error">{error}</p>}
//             {message && <p className="success">{message}</p>}
//             <form onSubmit={handleSubmit}>
//                 <div className="form-group">
//                     <label>Име</label>
//                     <input type="text" name="name" value={formData.name} onChange={handleChange} required />
//                 </div>
//                 <div className="form-group">
//                     <label>Описание</label>
//                     <textarea name="description" value={formData.description} onChange={handleChange} required />
//                 </div>
//                 <div className="form-group">
//                     <label>Цена</label>
//                     <input type="number" name="price" value={formData.price} onChange={handleChange} required />
//                 </div>
//                 <div className="form-group">
//                     <label>Категория</label>
//                     <select name="category" value={formData.category} onChange={handleChange}>
//                         <option value="manicure">Маникюр</option>
//                         <option value="pedicure">Педикюр</option>
//                     </select>
//                 </div>
//                 <div className="form-group">
//                     <label>URL на снимка</label>
//                     <input type="text" name="imageUrl" value={formData.imageUrl} onChange={handleChange} />
//                 </div>
//                 <button type="submit">Добави услуга</button>
//             </form>
//         </div>
//     );
// };
//
// export default AddTreatment;
