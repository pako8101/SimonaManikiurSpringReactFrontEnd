import React, { useState } from 'react';
import ReCAPTCHA from 'react-google-recaptcha';

function Register() {
    const [formData, setFormData] = useState({
        username: '',
        fullName: '',
        email: '',
        password: '',
        recaptchaToken: ''
    });
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleRecaptchaChange = (value) => {
        setFormData({ ...formData, recaptchaToken: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');
        setSuccess('');

        fetch('http://localhost:8888/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                return response.text();
            })
            .then(data => {
                console.log('Успешна регистрация:', data);
                setSuccess('Регистрацията е успешна! Моля, влезте.');
            })
            .catch(err => {
                console.error(err);
                setError(err.message);
            })
            .finally(() => setLoading(false));
    };

    return (
        <div className="auth-container">
            <h2>Симона маникюр и педикюр</h2>
            <h3>Регистрация </h3>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Потребителско име</label>
                    <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Първо и второ Име</label>
                    <input
                        type="text"
                        name="fullName"
                        value={formData.fullName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Имейл</label>
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Парола</label>
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <ReCAPTCHA
                        sitekey="6LdKEdYqAAAAAD-B56A9DQ8v35X4oYTnrC4JqEOc"
                        onChange={handleRecaptchaChange}
                    />
                </div>
                {error && <p className="error">{error}</p>}
                {success && <p className="success">{success}</p>}
                <button type="submit" disabled={loading}>
                    {loading ? 'Регистрация...' : 'Регистрация'}
                </button>
            </form>
        </div>
    );
}

export default Register;
